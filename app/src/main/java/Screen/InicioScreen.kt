package Screen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class Producto(
    val nombre: String,
    val precio: Double
)


@Composable
fun InicioScreen(modifier : Modifier = Modifier)
{
    var montoInput by rememberSaveable { mutableStateOf("") }
    var opcionSeleccionada by rememberSaveable { mutableStateOf(0) }
    var personasCantidad by rememberSaveable { mutableStateOf(0) }


    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(20.dp), verticalArrangement = Arrangement.spacedBy(15.dp))
    {
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Cuenta dividida", fontSize = 25.sp)

        OutlinedTextField(
            value = montoInput,
            onValueChange = { input -> montoInput = input },
            label = { Text("Monto de la cuenta") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Propina", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp))
        {
            // Chip 0
            FilterChip(
                selected = opcionSeleccionada == 0,
                onClick = { opcionSeleccionada = 0 }, // Al hacer clic, cambia el estado a 0
                label = { Text("10%") }
            )
            // Chip 1
            FilterChip(
                selected = opcionSeleccionada == 1,
                onClick = { opcionSeleccionada = 1 }, // Al hacer clic, cambia el estado a 1
                label = { Text("20%") }
            )
            // Chip 2
            FilterChip(
                selected = opcionSeleccionada == 2,
                onClick = { opcionSeleccionada = 2 }, // Al hacer clic, cambia el estado a 2
                label = { Text("30%") }
            )
            // Chip 3
            FilterChip(
                selected = opcionSeleccionada == 3,
                onClick = { opcionSeleccionada = 3 }, // Al hacer clic, cambia el estado a 3
                label = { Text("Otro") }
            )
        }

        Row(modifier = Modifier)
        {
            Text(text = "Personas", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            OutlinedIconButton(
                onClick = { if (personasCantidad > 0) onCantidadChange(personasCantidad - 1) },
                enabled = personasCantidad > 0,
                modifier = Modifier.size(36.dp),
                shape = CircleShape
            ) {
                Text(text = "−", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Text(
                text = "$personasCantidad",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )

            OutlinedIconButton(
                onClick = { if (personasCantidad < 10) onCantidadChange(personasCantidad + 1) },
                enabled = personasCantidad < 10,
                modifier = Modifier.size(36.dp),
                shape = CircleShape
            ) {
                Text(text = "+", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        }


    }
}

