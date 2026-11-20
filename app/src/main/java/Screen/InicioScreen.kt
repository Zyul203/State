package Screen

import ViewModel.VM
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Inicio(
    modifier: Modifier = Modifier,
    viewModel: VM = viewModel()
) {
    val state = viewModel.uiState

    if (state.inscrito) {
        ConfirmacionScreen(
            nombre = state.nombre,
            correo = state.correo,
            edad = state.edad,
            taller = state.taller,
            onNueva = viewModel::onNueva,
            modifier = modifier
        )
    } else {
        InscripcionForm(
            nombre = state.nombre,
            onNombreChange = viewModel::onNombreChange,
            errorNombre = state.errorNombre,
            correo = state.correo,
            onCorreoChange = viewModel::onCorreoChange,
            errorCorreo = state.errorCorreo,
            edad = state.edad,
            onEdadChange = viewModel::onEdadChange,
            errorEdad = state.errorEdad,
            taller = state.taller,
            onTallerChange = viewModel::onTallerChange,
            aceptaAviso = state.aceptaAviso,
            onAceptaAvisoChange = viewModel::onAceptaAvisoChange,
            errorAviso = state.errorAviso,
            onSubmit = viewModel::onSubmit,
            modifier = modifier
        )
    }
}

@Composable
fun InscripcionForm(
    nombre: String, onNombreChange: (String) -> Unit, errorNombre: String?,
    correo: String, onCorreoChange: (String) -> Unit, errorCorreo: String?,
    edad: String, onEdadChange: (String) -> Unit, errorEdad: String?,
    taller: String, onTallerChange: (String) -> Unit,
    aceptaAviso: Boolean, onAceptaAvisoChange: (Boolean) -> Unit, errorAviso: String?,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }
    val talleresDisponibles = listOf("Pintura", "Fotografía", "Guitarra")

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Inscripción a talleres", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(
            text = "Casa del Lago · cupo limitado",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Campo Nombre Completo
        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre completo") },
            isError = errorNombre != null,
            supportingText = { if (errorNombre != null) Text(errorNombre) },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Correo
        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange,
            label = { Text("Correo") },
            isError = errorCorreo != null,
            supportingText = { if (errorCorreo != null) Text(errorCorreo) },
            modifier = Modifier.fillMaxWidth()
        )

        // Campo Edad (Solo números)
        OutlinedTextField(
            value = edad,
            onValueChange = onEdadChange,
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = errorEdad != null,
            supportingText = { if (errorEdad != null) Text(errorEdad) },
            modifier = Modifier.fillMaxWidth()
        )

        // Menú desplegable para el Taller (Sin trailingIcon)
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = taller,
                onValueChange = {},
                readOnly = true,
                label = { Text("Taller") },
                modifier = Modifier.fillMaxWidth()
            )
            // Superficie transparente encima para detectar el clic y abrir el menú
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = !expanded }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                talleresDisponibles.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            onTallerChange(item)
                            expanded = false
                        }
                    )
                }
            }
        }

        // Casilla de Aviso de Privacidad
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Checkbox(
                checked = aceptaAviso,
                onCheckedChange = onAceptaAvisoChange
            )
            Text(text = "Acepto el aviso de privacidad")
        }
        if (errorAviso != null) {
            Text(text = errorAviso, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón Inscribirme
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6750A4))
        ) {
            Text(text = "Inscribirme", fontSize = 16.sp)
        }

        Text(
            text = "Quedan 6 lugares en este taller",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ConfirmacionScreen(
    nombre: String,
    correo: String,
    edad: String,
    taller: String,
    onNueva: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "¡Inscripción Exitosa!", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF3EDF7))
        ) {
            Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text(text = "Resumen de datos:")
                Text(text = "• Nombre: $nombre")
                Text(text = "• Correo: $correo")
                Text(text = "• Edad: $edad")
                Text(text = "• Taller: $taller")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Nueva inscripción
        Button(
            onClick = onNueva,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text(text = "Nueva inscripción", fontSize = 16.sp)
        }
    }
}