package Screen

import Components.ControlesLectura
import Components.VistaPrevia
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.StateTheme
import androidx.compose.runtime.getValue //AGREGAR MANUALMENTE
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue //AGREGAR MANUALMENTE
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


enum class Interlineado(val medida : Float)
{
    COMPACTO(1.0f),
    NORMAL(1.3f),
    AMPLIO(1.6f)
}

// 2. Pantalla Padre (Dueño del estado)
@Composable
fun LecturaScreen(modifier : Modifier = Modifier) {
    // Estados requeridos en la pantalla padre
    var tamano by remember { mutableStateOf(18f) } // Valor por defecto 18 sp
    var interlineado by remember { mutableStateOf(Interlineado.NORMAL) } // Por defecto Normal
    var modoOscuro by remember { mutableStateOf(false) } // Por defecto claro
    var usaSerif by remember { mutableStateOf(false) } // Por defecto Sans (false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Título de la pantalla
        Text(
            text = "Ajustes de lectura",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        // Controles de configuración (Hijo 1)
        ControlesLectura(
            tamano = tamano,
            onTamanoChange = { tamano = it },
            interlineado = interlineado,
            onInterlineadoChange = { interlineado = it },
            modoOscuro = modoOscuro,
            onModoOscuroChange = { modoOscuro = it },
            usaSerif = usaSerif,
            onUsaSerifChange = { usaSerif = it }
        )

        // Vista previa en vivo (Hijo 2)
        VistaPrevia(
            texto = "Era una noche tranquila en el pueblo cuando las campanas comenzaron a sonar, y nadie supo por qué.",
            tamano = tamano,
            interlineado = interlineado,
            modoOscuro = modoOscuro,
            usaSerif = usaSerif
        )

        // Botón Restablecer
        Button(
            onClick = {
                tamano = 18f
                interlineado = Interlineado.NORMAL
                modoOscuro = false
                usaSerif = false
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Text(text = "Restablecer", color = MaterialTheme.colorScheme.primary)
        }
    }
}


