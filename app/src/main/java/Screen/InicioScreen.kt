package Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("") }

    Column(modifier = modifier) {
        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text("Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text("Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Button(onClick = {
            val n1 = num1.toDoubleOrNull() ?: 0.0
            val n2 = num2.toDoubleOrNull() ?: 0.0
            val suma = n1 + n2
            resultado = if (suma % 1.0 == 0.0) suma.toLong().toString() else suma.toString()
        }) {
            Text("Sumar")
        }

        Text(text = "Resultado: $resultado")
    }
}