package Components

import Screen.Interlineado
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.R
import com.example.state.ui.theme.Purple40


// 3. Composable de Controles
@Composable
fun ControlesLectura(
    tamano: Float,
    onTamanoChange: (Float) -> Unit,
    interlineado: Interlineado,
    onInterlineadoChange: (Interlineado) -> Unit,
    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit,
    usaSerif: Boolean,
    onUsaSerifChange: (Boolean) -> Unit
)
{
    val colorMorado = colorResource(id = R.color.purple_v1)
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {

        // Slider de Tamaño de letra (14 a 32 sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Tamaño de letra")
            Text(text = "${tamano.toInt()} sp", fontWeight = FontWeight.Bold)
        }
        Slider(
            value = tamano,
            onValueChange = onTamanoChange,
            valueRange = 14f..32f,
            colors = SliderDefaults.colors(
                thumbColor = colorMorado,               // Color de la bolita/pulgar
                activeTrackColor = colorMorado,        // Color de la pista recorrida
                inactiveTrackColor = Color.LightGray, // Color de la pista sin recorrer
            )
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "A", fontSize = 14.sp)
            Text(text = "A", fontSize = 22.sp)
        }

        // RadioButton para Interlineado (Compacto, Normal, Amplio)
        Text(text = "Interlineado")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Interlineado.values().forEach { modo ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = interlineado == modo,
                        onClick = { onInterlineadoChange(modo) },
                    )
                    Text(text = modo.name)
                }
            }
        }

        // Switch de Modo Oscuro
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Modo oscuro")
            Switch(
                checked = modoOscuro,
                onCheckedChange = onModoOscuroChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = colorMorado,      // Fondo cuando está encendido
                    checkedBorderColor = colorMorado,
                    ))
        }

        // Serif y Sans
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Fuente")

            Spacer(modifier = Modifier.width(160.dp))

            FilterChip(
                selected = usaSerif,
                onClick = { onUsaSerifChange(true) },
                label = { Text("Serif") }
            )
            FilterChip(
                selected = !usaSerif,
                onClick = { onUsaSerifChange(false) },
                label = { Text("Sans") }
            )
        }
    }
}

// 4. Composable de Vista Previa
@Composable
fun VistaPrevia(
    texto: String,
    tamano: Float,
    interlineado: Interlineado,
    modoOscuro: Boolean,
    usaSerif: Boolean
)
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        Text(
            text = "VISTA PREVIA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Modo oscuro
        val colorCard = if (modoOscuro)
        {
            CardDefaults.cardColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        } else {
            CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = colorCard
        ) {
            Box(modifier = Modifier.padding(20.dp))
            {
                Text(
                    text = texto,
                    fontSize = tamano.sp,
                    lineHeight = (tamano * interlineado.medida).sp,
                    fontFamily = if (usaSerif) FontFamily.Serif else FontFamily.SansSerif
                )
            }
        }
    }
}