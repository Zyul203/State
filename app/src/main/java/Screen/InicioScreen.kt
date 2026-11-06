package Screen

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

@Composable
fun Inicio(modifier: Modifier = Modifier) {
    // 1. Estados principales (State Hoisting en la pantalla padre)
    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var taller by remember { mutableStateOf("Fotografía") }
    var aceptaAviso by remember { mutableStateOf(false) }

    var intentoEnviar by remember { mutableStateOf(false) }
    var inscrito by remember { mutableStateOf(false) }

    // 2. Validaciones derivadas (solo actúan si intentoEnviar es true)
    val errorNombre = if (intentoEnviar && nombre.length < 3) "El nombre debe tener al menos 3 caracteres" else null
    val errorCorreo = if (intentoEnviar && (!correo.contains("@") || !correo.substringAfter("@").contains("."))) "Ingresa un correo válido" else null

    val edadInt = edad.toIntOrNull()
    val errorEdad = if (intentoEnviar && (edadInt == null || edadInt !in 12..99)) "La edad debe estar entre 12 y 99" else null
    val errorAviso = if (intentoEnviar && !aceptaAviso) "Debes aceptar el aviso de privacidad" else null

    // 3. Alternar entre pantallas según el estado de inscripción
    if (inscrito) {
        ConfirmacionScreen(
            nombre = nombre,
            correo = correo,
            edad = edad,
            taller = taller,
            onNueva = {
                // Limpiar campos y regresar al formulario
                nombre = ""
                correo = ""
                edad = ""
                taller = "Fotografía"
                aceptaAviso = false
                intentoEnviar = false
                inscrito = false
            }
        )
    } else {
        InscripcionForm(
            nombre = nombre, onNombreChange = { nombre = it }, errorNombre = errorNombre,
            correo = correo, onCorreoChange = { correo = it }, errorCorreo = errorCorreo,
            edad = edad, onEdadChange = { if (it.all { char -> char.isDigit() }) edad = it }, errorEdad = errorEdad,
            taller = taller, onTallerChange = { taller = it },
            aceptaAviso = aceptaAviso, onAceptaAvisoChange = { aceptaAviso = it }, errorAviso = errorAviso,
            onSubmit = {
                intentoEnviar = true
                // Si no hay errores, procedemos a marcar como inscrito
                if (errorNombre == null && errorCorreo == null && errorEdad == null && errorAviso == null) {
                    inscrito = true
                }
            }
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
    onSubmit: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val talleresDisponibles = listOf("Pintura", "Fotografía", "Guitarra")

    Column(
        modifier = Modifier
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
    onNueva: () -> Unit
) {
    Column(
        modifier = Modifier
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