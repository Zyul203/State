package Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp


enum class Interlineado(val medida : Float)
{
    COMPACTO(1.0f),
    NORMAL(1.3f),
    AMPLIO(1.6f)
}

@Composable
fun PantallaPrincipal(modifier : Modifier = Modifier)
{
    //ESTADOS
    var sc1_tamano by remember {mutableStateOf(18f)}
    var sc2_interlineado by remember { mutableStateOf(Interlineado.NORMAL) }
    var sc3_modoOscuro by remember { mutableStateOf(false) }
    var sc4_fuenteSerif by remember { mutableStateOf(false) }

}


