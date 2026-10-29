package Components

import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun AforoMainCard( personasSala: Int, aforoMax: Int, porcentaje: Float, colorIndicador: Color,
                   modifier: Modifier = Modifier)
{
    Card(
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$personasSala / $aforoMax",
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
}


// Fila de Botones: Sale y Entra
@Composable
fun AforoControlesRow(
    onSaleClick: () -> Unit,
    onEntraClick: () -> Unit,
    canSale: Boolean,
    canEntra: Boolean,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedButton(
            onClick = onSaleClick,
            enabled = canSale,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(28.dp)
        ) {
            Text(text = "− Sale", fontSize = 16.sp)
        }

        Button(
            onClick = onEntraClick,
            enabled = canEntra,
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4))
        ) {
            Text(text = "+ Entra", fontSize = 16.sp)
        }
    }
}



// Tarjeta de Visitas del Día
@Composable
fun VisitasCard(
    visitasDelDia: Int,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3EDF7)),
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "Visitas del día",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1D1B20)
                )
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
}



// Botón Cerrar Día
@Composable
fun CerrarDiaButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(27.dp),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFFB3261E))
    ) {
        Text(text = "Cerrar día", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}
