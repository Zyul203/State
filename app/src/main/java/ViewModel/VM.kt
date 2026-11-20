package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class InscripcionUiState(
    val nombre: String = "",
    val correo: String = "",
    val edad: String = "",
    val taller: String = "Fotografía",
    val aceptaAviso: Boolean = false,
    val intentoEnviar: Boolean = false,
    val inscrito: Boolean = false
) {
    val errorNombre: String?
        get() = if (intentoEnviar && nombre.length < 3) {
            "El nombre debe tener al menos 3 caracteres"
        } else null

    val errorCorreo: String?
        get() = if (intentoEnviar && (!correo.contains("@") || !correo.substringAfter("@").contains("."))) {
            "Ingresa un correo válido"
        } else null

    private val edadInt: Int?
        get() = edad.toIntOrNull()

    val errorEdad: String?
        get() = if (intentoEnviar && (edadInt == null || edadInt !in 12..99)) {
            "La edad debe estar entre 12 y 99"
        } else null

    val errorAviso: String?
        get() = if (intentoEnviar && !aceptaAviso) {
            "Debes aceptar el aviso de privacidad"
        } else null

    val tieneErrores: Boolean
        get() = errorNombre != null || errorCorreo != null || errorEdad != null || errorAviso != null
}

class VM: ViewModel()
{
    var uiState by mutableStateOf(InscripcionUiState())
        private set

    fun onNombreChange(nuevoNombre: String) {
        uiState = uiState.copy(nombre = nuevoNombre)
    }

    fun onCorreoChange(nuevoCorreo: String) {
        uiState = uiState.copy(correo = nuevoCorreo)
    }

    fun onEdadChange(nuevaEdad: String) {
        if (nuevaEdad.all { char -> char.isDigit() }) {
            uiState = uiState.copy(edad = nuevaEdad)
        }
    }

    fun onTallerChange(nuevoTaller: String) {
        uiState = uiState.copy(taller = nuevoTaller)
    }

    fun onAceptaAvisoChange(acepta: Boolean) {
        uiState = uiState.copy(aceptaAviso = acepta)
    }

    fun onSubmit() {
        val estadoMarcado = uiState.copy(intentoEnviar = true)
        uiState = if (!estadoMarcado.tieneErrores) {
            estadoMarcado.copy(inscrito = true)
        } else {
            estadoMarcado
        }
    }

    fun onNueva() {
        uiState = InscripcionUiState()
    }
}