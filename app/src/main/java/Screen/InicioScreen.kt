package Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@Composable
fun InicioScreen(modifier : Modifier = Modifier)
{
    val tablero = remember { mutableStateListOf("", "", "", "", "", "", "", "", "") } //9 posiciones en blanco
    var turnoX by rememberSaveable { mutableStateOf(true) }  //Turno de X
    var mensajeEstado by rememberSaveable { mutableStateOf("Turno de: X") }
    var juegoTerminado by rememberSaveable { mutableStateOf(false) }

    // Función interna para verificar si alguien ganó
    fun verificarGanador()
    {
        // Combinaciones ganadoras posibles en el gato
        val lineasGanadoras = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Horizontales
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Verticales
            listOf(0, 4, 8), listOf(2, 4, 6)                  // Diagonales
        )

        for (linea in lineasGanadoras)
        {
            val a = tablero[linea[0]]
            val b = tablero[linea[1]]
            val c = tablero[linea[2]]

            if (a.isNotEmpty() && a == b && a == c)
            {
                mensajeEstado = "¡Ganador: $a!"
                juegoTerminado = true
                return
            }
        }

        // empate
        if (!tablero.contains(""))
        {
            mensajeEstado = "¡Es un Empate!"
            juegoTerminado = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Spacer(modifier = Modifier.height(20.dp))

        // Texto que indica el estado del juego actual
        Text(
            text = mensajeEstado,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        // Cuadrícula del Tablero (3 filas x 3 columnas)
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            for (fila in 0..2) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    for (columna in 0..2) {
                        val indice = fila * 3 + columna // Calcula la posición del 0 al 8

                        Button(
                            onClick = {
                                // Solo se puede marcar si la casilla está vacía y el juego no ha terminado
                                if (tablero[indice].isEmpty() && !juegoTerminado) {
                                    tablero[indice] = if (turnoX) "X" else "O"
                                    verificarGanador()

                                    // Si el juego continúa, cambiamos el turno
                                    if (!juegoTerminado) {
                                        turnoX = !turnoX
                                        mensajeEstado = "Turno de: ${if (turnoX) "X" else "O"}"
                                    }
                                }
                            },
                            modifier = Modifier.size(90.dp), // Tamaño cuadrado
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = when (tablero[indice]) {
                                    "X" -> Color(0xFF2196F3) // Azul para las X
                                    "O" -> Color(0xFFFF5722) // Naranja para las O
                                    else -> Color(0xFFE0E0E0) // Gris para las vacías
                                }
                            )
                        ) {
                            Text(
                                text = tablero[indice],
                                fontSize = 32.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Botón para reiniciar la partida
        Button(
            onClick = {
                for (i in 0..8) tablero[i] = ""
                turnoX = true
                mensajeEstado = "Turno de: X"
                juegoTerminado = false
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reiniciar Juego", fontSize = 18.sp)
        }
    }
}
