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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.state.AFORO_MAX
import com.example.state.ui.theme.amarilloClaro
import com.example.state.ui.theme.amarilloFuerte
import com.example.state.ui.theme.gris
import com.example.state.ui.theme.rojoClaro
import com.example.state.ui.theme.verdeClaro
import com.example.state.ui.theme.verdeFuerte

@Composable
fun AforoMainCard( personasSala: Int, aforoMax: Int, modifier: Modifier = Modifier) //El Modifier se pone al final, si no se usa no afecta a la funcion
{

    //Valor del porcentaje para la barra de ocupacion
    val porcentaje = personasSala / AFORO_MAX.toFloat() //Variable Derivada. Puse uno float para que porcentaje sea float, se le pone val porque el valor se cambia indirectamente con la variable "personasSala"

    val colorBackground = when
    {
        porcentaje == 1f -> rojoClaro
        porcentaje >= 0.7f -> amarilloClaro
        else -> verdeClaro
    }

    // Determinación del color según la ocupación
    val colorIndicador: Color = when
    {
        porcentaje == 1f -> Color.Red // Rojo 100%
        porcentaje >= 0.7f -> amarilloFuerte // Amarillo 70% - 99%
        else -> verdeFuerte // Verde < 70%
    }



    Card( //Card: Viene siendo como un div para agrupar elementos
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = colorBackground),
        modifier = modifier.fillMaxWidth()
    )
    {
        Column(
            modifier = Modifier.padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally //Como los elementos se acomodan en vertical por el column, solo se necesita decirle cómo alinearlos a lo ancho
        )
        {
            Text(
                text = "$personasSala / $aforoMax",
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = colorIndicador
            )
            Text(
                text = "personas en la sala",
                fontSize = 14.sp,
                color = gris
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Barra de progreso
            LinearProgressIndicator(
                progress = { porcentaje },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = colorIndicador,
                trackColor = gris
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
