package com.example.state


import Components.AforoControlesRow
import Components.AforoMainCard
import Components.CerrarDiaButton
import Components.VisitasCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.amarilloFuerte
import com.example.state.ui.theme.verdeClaro
import com.example.state.ui.theme.verdeFuerte


const val AFORO_MAX = 50

@Composable
fun AforoScreen(modifier: Modifier = Modifier)
{
    var personasSala by remember { mutableStateOf(20) }
    var visitasDelDia by remember { mutableStateOf(68) }

    // Valores derivados
    val porcentaje = personasSala / AFORO_MAX.toFloat() //Puse uno float para que porcentaje sea float

    // Determinación del color según la ocupación
    val colorIndicador = when
    {
        porcentaje >= 1f -> Color.Red // Rojo 100%
        porcentaje >= 0.7f -> amarilloFuerte // Amarillo 70% - 99%
        else -> verdeFuerte // Verde < 70%
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.1f))

        Text(
            text = "Biblioteca Central",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D1B20),

        )
        Text(
            text = "Sala de estudio · capacidad $AFORO_MAX",
            fontSize = 14.sp,
            color = Color(0xFF49454F)
        )

        AforoMainCard(
            personasSala = personasSala,
            aforoMax = AFORO_MAX,
            porcentaje = porcentaje,
            colorIndicador = colorIndicador
        )

        // Fila de botones Entra / Sale
        AforoControlesRow(
            onSaleClick = { if (personasSala > 0) personasSala-- },
            onEntraClick = {
                if (personasSala < AFORO_MAX) {
                    personasSala++
                    visitasDelDia++
                }
            },
            canSale = personasSala > 0,
            canEntra = personasSala < AFORO_MAX
        )

        // Tarjeta de Visitas del Día
        VisitasCard(visitasDelDia = visitasDelDia)

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
        CerrarDiaButton(
            onClick = {
                personasSala = 0
                visitasDelDia = 0
            }
        )
    }
}

