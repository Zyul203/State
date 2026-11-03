package Components

import Screen.Interlineado
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// 3. Composable de Controles
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControlesLectura(
    tamano: Float,
    onTamanoChange: (Float) -> Unit,
    interlineado: Interlineado,
    onInterlineadoChange: (Interlineado) -> Unit,
    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit,
    fuenteSerif: Boolean,
    onFuenteSerifChange: (Boolean) -> Unit
) {
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
                        onClick = { onInterlineadoChange(modo) }
                    )
                    Text(text = modo.name.lowercase().capitalize())
                }
            }
        }
    }
}
