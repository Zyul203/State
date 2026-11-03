package Screen

import Components.ControlesLectura
import Components.VistaPrevia
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue //AGREGAR MANUALMENTE
import androidx.compose.runtime.setValue //AGREGAR MANUALMENTE
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.state.R



enum class Interlineado(val medida : Float)
{
    Compacto(1.0f),
    Normal(1.3f),
    Amplio(1.6f)
}

// 2. Pantalla Padre (Dueño del estado)
@Composable
fun LecturaScreen(modifier : Modifier = Modifier)
{
    val colorMorado = colorResource(id = R.color.purple_v1)

    // Estados requeridos en la pantalla padre
    var tamano by remember { mutableStateOf(18f) } // Valor por defecto 18 sp
    var interlineado by remember { mutableStateOf(Interlineado.Normal) } // Por defecto Normal
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

        // Controles de configuración
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

        // Vista previa en vivo
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
                interlineado = Interlineado.Normal
                modoOscuro = false
                usaSerif = false
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colorMorado  )
        ) {
            Text(text = "Restablecer", color = Color.White)
        }
    }
}


