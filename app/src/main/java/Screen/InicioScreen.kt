package Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var pantalla by rememberSaveable { mutableStateOf("") }
    var primerNumero by rememberSaveable { mutableStateOf("") }
    var operador by rememberSaveable { mutableStateOf("") }
    var reiniciarPantallaAlEscribir by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(20.dp))

        //pantalla del resultado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFE0E0E0))
                .padding(16.dp),
            contentAlignment = Alignment.CenterEnd
        ) {
            Text(
                text = pantalla.ifEmpty { "0" },
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.End,
                maxLines = 1
            )
        }


        val botones = listOf(
            listOf("C", "", "", "/"),
            listOf("7", "8", "9", "*"),
            listOf("4", "5", "6", "-"),
            listOf("1", "2", "3", "+"),
            listOf("0", ".", "", "=")
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            botones.forEach { fila ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    fila.forEach { texto ->
                        // Si el texto está vacío, creamos un espacio invisible para mantener la alineación
                        if (texto.isEmpty()) {
                            Spacer(modifier = Modifier.weight(1f))
                        } else {
                            Button(
                                onClick = {
                                    when (texto) {
                                        "C" -> {
                                            pantalla = ""
                                            primerNumero = ""
                                            operador = ""
                                            reiniciarPantallaAlEscribir = false
                                        }
                                        "+", "-", "*", "/" -> {
                                            if (pantalla.isNotEmpty() && pantalla != "Syntax Error") {
                                                primerNumero = pantalla
                                                operador = texto
                                                reiniciarPantallaAlEscribir = true
                                            }
                                        }
                                        "=" -> {
                                            if (primerNumero.isNotEmpty() && operador.isNotEmpty() && pantalla.isNotEmpty()) {
                                                val n1 = primerNumero.toDoubleOrNull() ?: 0.0
                                                val n2 = pantalla.toDoubleOrNull() ?: 0.0

                                                // Validación de división por cero
                                                if (operador == "/" && n2 == 0.0) {
                                                    pantalla = "Syntax Error"
                                                } else {
                                                    val resultado = when (operador) {
                                                        "+" -> n1 + n2
                                                        "-" -> n1 - n2
                                                        "*" -> n1 * n2
                                                        "/" -> n1 / n2
                                                        else -> 0.0
                                                    }

                                                    pantalla = resultado.toString()

                                                }
                                                // Reseteo
                                                primerNumero = ""
                                                operador = ""
                                                reiniciarPantallaAlEscribir = true
                                            }
                                        }
                                        else -> { // Al presionar números o el punto "."
                                            if (reiniciarPantallaAlEscribir || pantalla == "Syntax Error") {
                                                pantalla = texto
                                                reiniciarPantallaAlEscribir = false
                                            } else {
                                                pantalla += texto
                                            }
                                        }
                                    }
                                },
                                modifier = Modifier.weight(1f).height(70.dp),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = when (texto) {
                                        "C" -> Color(0xFFFF5252) //
                                        "=", "+", "-", "*", "/" -> Color(0xFFFF9800)
                                        else -> MaterialTheme.colorScheme.primary
                                    }
                                )
                            ) {
                                Text(text = texto, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                            }
                        }
                    }
                }
            }
        }
    }
}
