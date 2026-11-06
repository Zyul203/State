package Components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.Purple40

@Composable
fun LoginScreen(
    nombre: String, onNombreChange: (String) -> Unit,
    onComenzar: () -> Unit,
    modifier: Modifier = Modifier
)
{
    val esValido = nombre.trim().length >= 2

    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Text(text = "Quiz de Programación 🧑🏻‍💻", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = onComenzar,
            enabled = esValido,
            modifier = Modifier.fillMaxWidth().height(60.dp), colors = ButtonDefaults.buttonColors(containerColor = Purple40)
        )
        {
            Text(text = "Comenzar", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }
}

