package Screen

import Components.ControlesLectura
import Components.VistaPrevia
import ViewModel.VM
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.R

enum class Interlineado(val medida: Float)
{
    Compacto(1.0f),
    Normal(1.3f),
    Amplio(1.6f)
}

@Composable
fun LecturaScreen(modifier: Modifier = Modifier, viewModel: VM = viewModel())
{
    val colorMorado = colorResource(id = R.color.purple_v1)
    val state = viewModel.uiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Título de la pantalla
        Text(
            text = "Ajustes de lectura",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // Controles de configuración
        ControlesLectura(
            tamano = state.tamano,
            onTamanoChange = viewModel::onTamanoChange,
            interlineado = state.interlineado,
            onInterlineadoChange = viewModel::onInterlineadoChange,
            modoOscuro = state.modoOscuro,
            onModoOscuroChange = viewModel::onModoOscuroChange,
            usaSerif = state.usaSerif,
            onUsaSerifChange = viewModel::onUsaSerifChange
        )

        // Vista previa en vivo
        VistaPrevia(
            texto = "Era una noche tranquila en el pueblo cuando las campanas comenzaron a sonar, y nadie supo por qué.",
            tamano = state.tamano,
            interlineado = state.interlineado,
            modoOscuro = state.modoOscuro,
            usaSerif = state.usaSerif
        )

        // Botón Restablecer
        Button(
            onClick = viewModel::onRestablecerClick,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorMorado)
        ) {
            Text(text = "Restablecer", color = Color.White)
        }
    }
}