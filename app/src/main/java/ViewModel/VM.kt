package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

enum class loginEstado {
    INACTIVO, ERROR, BLOQUEADO, EXITO
}

private const val MAX_INTENTOS = 3
private const val CORREO_CORRECTO = "paciente@vida.mx"
private const val PASSWORD_CORRECTA = "Vida2025"

data class LoginUiState(
    val textoCorreo: String = "",
    val textoContrasena: String = "",
    val recordarme: Boolean = false,
    val intentosFallidos: Int = 0,
    val estadoLogin: loginEstado = loginEstado.INACTIVO,
    val passwordVisibility: Boolean = false
) {
    val mensajeError: String
        get() = when (estadoLogin) {
            loginEstado.ERROR -> "Correo o contraseña incorrectos (intento $intentosFallidos de $MAX_INTENTOS)"
            loginEstado.BLOQUEADO -> "Demasiados intentos. Intente de nuevo más tarde."
            else -> ""
        }

    val puedeEntrar: Boolean
        get() = estadoLogin != loginEstado.BLOQUEADO
}

class VM : ViewModel()
{
    var uiState by mutableStateOf(LoginUiState())
        private set

    fun onCorreoChange(nuevoCorreo: String) {
        uiState = uiState.copy(textoCorreo = nuevoCorreo)
    }

    fun onContrasenaChange(nuevaContrasena: String) {
        uiState = uiState.copy(textoContrasena = nuevaContrasena)
    }

    fun onRecordarmeChange(marcado: Boolean) {
        uiState = uiState.copy(recordarme = marcado)
    }

    fun onPasswordVisibilityChange(visible: Boolean) {
        uiState = uiState.copy(passwordVisibility = visible)
    }

    fun onEntrarClick() {
        if (uiState.textoCorreo == CORREO_CORRECTO && uiState.textoContrasena == PASSWORD_CORRECTA) {
            uiState = uiState.copy(
                estadoLogin = loginEstado.EXITO,
                intentosFallidos = 0
            )
        } else {
            val nuevosIntentos = uiState.intentosFallidos + 1
            val nuevoEstado = if (nuevosIntentos >= MAX_INTENTOS) {
                loginEstado.BLOQUEADO
            } else {
                loginEstado.ERROR
            }
            uiState = uiState.copy(
                intentosFallidos = nuevosIntentos,
                estadoLogin = nuevoEstado
            )
        }
    }

    fun onLogout() {
        uiState = LoginUiState()
    }
}