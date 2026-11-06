package Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.Purple40
import com.example.state.ui.theme.grisClaro



@Composable
fun MontoTextField(
    montoInput: String,
    onMontoChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = montoInput,
        onValueChange = onMontoChange,
        label = { Text("Monto de la cuenta") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun Propina(propinaSeleccionada: Int, OnPropinaSeleccionada: (Int) -> Unit, propinaSliderValor: Float, OnpropinaSliderValor: (Float) -> Unit)
{
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp))
    {
        // Chip 10%
        FilterChip(
            selected = propinaSeleccionada == 10,
            onClick = { OnPropinaSeleccionada(10) },
            label = { Text("10%") }
        )
        // Chip 20%
        FilterChip(
            selected = propinaSeleccionada == 20,
            onClick = { OnPropinaSeleccionada(20) },
            label = { Text("20%") }
        )
        // Chip 30%
        FilterChip(
            selected = propinaSeleccionada == 30,
            onClick = { OnPropinaSeleccionada(30) },
            label = { Text("30%") }
        )
        // Chip Otro
        FilterChip(
            selected = propinaSeleccionada == 1,
            onClick = { OnPropinaSeleccionada(1) },
            label = { Text("Otro") }
        )
    }

    if (propinaSeleccionada == 1) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Personalizar propina: ${propinaSliderValor.toInt()}%", fontSize = 14.sp)
            Slider(
                value = propinaSliderValor,
                onValueChange = OnpropinaSliderValor,
                valueRange = 0f..30f,
            )
        }
    }
}

@Composable
fun RedondearSwitchRow(
    redondearPersona: Boolean,
    onRedondearChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Redondear por Persona", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Switch(
            checked = redondearPersona,
            onCheckedChange = onRedondearChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Purple40,
                checkedBorderColor = Color.White,
            )
        )
    }
}

@Composable
fun Resumen(propinaSeleccionada: Int, propinaSliderValor: Float, subtotal: Double, total: Double,
            personasCantidad: Int, propinaFinal : Double, sobrante : Double, redondearPersona: Boolean)
{
    Card (modifier = Modifier.fillMaxWidth().height(250.dp)
        .background(color = grisClaro, shape = RoundedCornerShape(20.dp)))
    {
        Column(modifier = Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp))
        {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween)
            {
                if (propinaSeleccionada != 1) Text(text = "Propina (${propinaSeleccionada} %)") else Text(text = "Propina (${propinaSliderValor.toInt()} %)")
                Text(text = "$ ${String.format("%.2f",propinaFinal)}")
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(text = "Total con propina")
                Text(text = "$ ${String.format("%.2f",subtotal)}")
            }
            HorizontalDivider()
            Text(text = "Cada quien paga", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Text(text = "$ ${String.format("%.2f", total)}", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center , fontWeight = FontWeight.ExtraBold, fontSize = 50.sp, color = Purple40)
            Text(text = "entre $personasCantidad personas", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            if (!redondearPersona) //Si es false
            {
                Text(text = "Sobrante por redondeo: $ 0.00", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            } else
            {
                Text(text = "Sobrante por redondeo: $ ${String.format("%.2f",sobrante)}", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            }
        }
    }
}
