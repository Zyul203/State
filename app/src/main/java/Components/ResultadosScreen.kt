package Components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultadoScreen(
    nombre: String,
    puntaje: Int,
    total: Int,
    onReintentar: () -> Unit,
    onCambiarJugador: () -> Unit,
    modifier: Modifier = Modifier
) {
    val mensaje = when (puntaje) {
        in 0..2 -> "sigue practicando"
        in 3..4 -> "buen trabajo"
        5 -> "perfecto"
        else -> ""
    }

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "bien $nombre! $puntaje de $total", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = mensaje, fontSize = 20.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF624BFF))
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onReintentar,
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Text("reintentar")
        }
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedButton(
            onClick = onCambiarJugador,
            modifier = Modifier.fillMaxWidth().height(48.dp)
        ) {
            Text("cambiar jugador")
        }
    }
}