package ViewModel

import Screen.Interlineado
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class LecturaUiState(
    val tamano: Float = 18f,
    val interlineado: Interlineado = Interlineado.Normal,
    val modoOscuro: Boolean = false,
    val usaSerif: Boolean = false
)

class VM : ViewModel()
{
    var uiState by mutableStateOf(LecturaUiState())
        private set

    fun onTamanoChange(nuevoTamano: Float) {
        uiState = uiState.copy(tamano = nuevoTamano)
    }

    fun onInterlineadoChange(nuevoInterlineado: Interlineado) {
        uiState = uiState.copy(interlineado = nuevoInterlineado)
    }

    fun onModoOscuroChange(activado: Boolean) {
        uiState = uiState.copy(modoOscuro = activado)
    }

    fun onUsaSerifChange(usarSerif: Boolean) {
        uiState = uiState.copy(usaSerif = usarSerif)
    }

    fun onRestablecerClick() {
        uiState = LecturaUiState() // Vuelve exactamente a los valores por defecto
    }
}