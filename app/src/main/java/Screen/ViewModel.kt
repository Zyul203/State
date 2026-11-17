package Screen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Definimos una clase de datos para agrupar el estado de la UI
data class AforoUiState(
    val personasSala: Int = 30,
    val visitasDelDia: Int = 68,
    val aforoMax: Int = 50
)

class AforoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AforoUiState())
    val uiState: StateFlow<AforoUiState> = _uiState.asStateFlow()

    // Acciones que antes estaban en la UI, ahora viven aquí
    fun entraPersona() {
        val currentState = _uiState.value
        if (currentState.personasSala < currentState.aforoMax) {
            _uiState.value = currentState.copy(
                personasSala = currentState.personasSala + 1,
                visitasDelDia = currentState.visitasDelDia + 1
            )
        }
    }

    fun salePersona() {
        val currentState = _uiState.value
        if (currentState.personasSala > 0) {
            _uiState.value = currentState.copy(
                personasSala = currentState.personasSala - 1
            )
        }
    }

    fun cerrarDia() {
        _uiState.value = _uiState.value.copy(
            personasSala = 0,
            visitasDelDia = 0
        )
    }
}