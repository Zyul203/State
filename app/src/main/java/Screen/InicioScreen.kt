package Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp


@Composable
fun InicioScreen(modifier: Modifier = Modifier)
{
    var num1 by rememberSaveable { mutableStateOf("") }
    var num2 by rememberSaveable { mutableStateOf("") }
    var resultado by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize()
        .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp))
    {
        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "Suma",
            fontSize = 25.sp, fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        TextField(
            value = num1,
            onValueChange = { num1 = it },
            label = { Text(text = "Número 1") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        TextField(
            value = num2,
            onValueChange = { num2 = it },
            label = { Text(text = "Número 2") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(25.dp))

        Button(onClick = {
            val n1 = num1.toDoubleOrNull() ?: 0.0
            val n2 = num2.toDoubleOrNull() ?: 0.0
            val suma = n1 + n2
            resultado = suma.toString()
        }, modifier = Modifier.width(150.dp).align(Alignment.CenterHorizontally)) {
            Text(text = "Sumar")
        }

        Text(text = "Resultado: $resultado")
    }
}