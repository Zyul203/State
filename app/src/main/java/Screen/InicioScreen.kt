package com.example.state
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

const val AFORO_MAX = 50

@Composable
fun AforoScreen() {
    var actuales by remember { mutableStateOf(32) } // Valor inicial de ejemplo
    var visitasDelDia by remember { mutableStateOf(118) }

    // Valores derivados
    val porcentaje = actuales / AFORO_MAX.toFloat()

    // Determinación del color según las reglas de negocio
    val colorIndicador = when {
        porcentaje >= 1f -> Color.Red // Rojo 100%
        porcentaje >= 0.7f -> Color.Yellow // Amarillo 70% - 99%
        else -> Color.Green // Verde < 70%
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F6FC))
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título de la sección superior
        Text(
            text = "Biblioteca Central",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D1B20)
        )
        Text(
            text = "Sala de estudio · capacidad $AFORO_MAX",
            fontSize = 14.sp,
            color = Color(0xFF49454F)
        )

        // Tarjeta principal de Aforo
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "$actuales / $AFORO_MAX",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorIndicador
                )
                Text(
                    text = "personas en la sala",
                    fontSize = 14.sp,
                    color = Color(0xFF49454F)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Barra de progreso
                LinearProgressIndicator(
                    progress = { porcentaje },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = colorIndicador,
                    trackColor = Color(0xFFD1E7DD)
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "${(porcentaje * 100).toInt()} % de ocupación",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorIndicador
                )
                Text(
                    text = "Aforo disponible",
                    fontSize = 12.sp,
                    color = Color(0xFF49454F)
                )
            }
        }

        // Fila de Botones: Sale y Entra
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedButton(
                onClick = { if (actuales > 0) actuales-- },
                enabled = actuales > 0,
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(text = "− Sale", fontSize = 16.sp)
            }

            Button(
                onClick = {
                    if (actuales < AFORO_MAX) {
                        actuales++
                        visitasDelDia++
                    }
                },
                enabled = actuales < AFORO_MAX,
                modifier = Modifier.weight(1f).height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4))
            ) {
                Text(text = "+ Entra", fontSize = 16.sp)
            }
        }

        // Tarjeta de Visitas del Día
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3EDF7)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(text = "Visitas del día", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1D1B20))
                    Text(text = "Solo suma las entradas", fontSize = 12.sp, color = Color(0xFF49454F))
                }
                Text(
                    text = "$visitasDelDia",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D1B20)
                )
            }
        }

        // Leyenda de colores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "🟢 Verde < 70%", fontSize = 12.sp, color = Color.DarkGray)
            Text(text = "🟡 Amarillo 70–99%", fontSize = 12.sp, color = Color.DarkGray)
            Text(text = "🔴 Rojo 100%", fontSize = 12.sp, color = Color.DarkGray)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón Cerrar Día
        OutlinedButton(
            onClick = {
                actuales = 0
                visitasDelDia = 0
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            shape = RoundedCornerShape(27.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFB3261E))
        ) {
            Text(text = "Cerrar día", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AforoScreenPreview() {
    AforoScreen()
}