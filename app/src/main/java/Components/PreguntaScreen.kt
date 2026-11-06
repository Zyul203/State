package Components

import Screen.Pregunta
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.ColorBorde_correcto
import com.example.state.ui.theme.ColorBorde_incorrecto
import com.example.state.ui.theme.ColorFondo_correcto
import com.example.state.ui.theme.ColorFondo_incorrecto
import com.example.state.ui.theme.Purple40

@Composable
fun PreguntaScreen(
    pregunta: Pregunta, numero: Int, total: Int, puntajeActual: Int, seleccionada: Int?, respondida: Boolean,
    onSeleccionar: (Int) -> Unit, onConfirmar: () -> Unit, onSiguiente: () -> Unit,
    modifier: Modifier = Modifier
)
{
    val progreso = numero.toFloat() / total

    Column(
        modifier = modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    )
    {
        Column {
            Text(text = "Quiz de Programación", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(text = "Pregunta $numero de $total", fontWeight = FontWeight.SemiBold)
                Text(text = "Puntaje: $puntajeActual", fontWeight = FontWeight.SemiBold)
            }
            Spacer(modifier = Modifier.height(8.dp))

            LinearProgressIndicator(
                progress = { progreso },
                modifier = Modifier.fillMaxWidth().height(8.dp),
                color = Purple40,
                trackColor = Color.LightGray,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(text = pregunta.texto, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            pregunta.opciones.forEachIndexed { index, opcion ->
                val esSeleccionada = seleccionada == index
                val esCorrecta = index == pregunta.correcta

                val colorFondo = when {
                    respondida && esCorrecta -> ColorFondo_correcto
                    respondida && esSeleccionada && !esCorrecta -> ColorFondo_incorrecto
                    else -> Color.Transparent
                }
                val colorBorde = when {
                    respondida && esCorrecta -> ColorBorde_correcto
                    respondida && esSeleccionada && !esCorrecta -> ColorBorde_incorrecto
                    esSeleccionada -> Color(0xFF624BFF)
                    else -> Color.LightGray
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .border(width = 1.5.dp, color = colorBorde, shape = RoundedCornerShape(8.dp))
                        .background(colorFondo)
                        .clickable(enabled = !respondida) { onSeleccionar(index) }
                        .padding(12.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            RadioButton(
                                selected = esSeleccionada,
                                onClick = if (!respondida) { { onSeleccionar(index) } } else null
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = opcion, fontSize = 16.sp)
                        }
                        if (respondida) {
                            if (esCorrecta) {
                                Text(text = "✓", color = ColorBorde_correcto, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            } else if (esSeleccionada) {
                                Text(text = "x", color = ColorBorde_incorrecto, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            }
                        }
                    }
                }
            }

            if (respondida) {
                Spacer(modifier = Modifier.height(16.dp))
                val respuestaCorrectaTexto = pregunta.opciones[pregunta.correcta]
                val mensajeFeedback = if (seleccionada == pregunta.correcta) {
                    "Correcto!"
                } else {
                    "Incorrecto. la respuesta correcta es $respuestaCorrectaTexto."
                }
                val colorBox = if (seleccionada == pregunta.correcta) Color(0xFFD4EDDA) else Color(0xFFF8D7DA)
                val colorTexto = if (seleccionada == pregunta.correcta) Color(0xFF155724) else Color(0xFF721C24)

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorBox, shape = RoundedCornerShape(8.dp))
                        .padding(12.dp)
                ) {
                    Text(text = mensajeFeedback, color = colorTexto, fontWeight = FontWeight.Bold)
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = onConfirmar,
                enabled = seleccionada != null && !respondida,
                modifier = Modifier.weight(1f).height(48.dp), colors = ButtonDefaults.buttonColors(containerColor = Purple40)
            ) {
                Text(text = "Confirmar", fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = onSiguiente,
                enabled = respondida,
                modifier = Modifier.weight(1f).height(48.dp), colors = ButtonDefaults.buttonColors(containerColor = Purple40)
            ) {
                Text(if (numero == total) "Ver Resultados" else "Siguiente", fontWeight = FontWeight.Bold)
            }
        }
    }
}