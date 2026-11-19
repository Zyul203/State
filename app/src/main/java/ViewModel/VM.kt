package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

const val AFORO_MAX = 50

data class AforoUiState(
    val personasSala: Int = 30,
    val visitasDelDia: Int = 68
) {
    val canSale: Boolean get() = personasSala > 0
    val canEntra: Boolean get() = personasSala < AFORO_MAX
}

class VM : ViewModel() {

    var uiState by mutableIntStateOf(30).let {
        // Mejor usemos mutableStateOf con AforoUiState para tener tdo en un solo objeto de estado:
        androidx.compose.runtime.mutableStateOf(AforoUiState())
    }
        private set

    fun onEntraClick() {
        if (uiState.personasSala < AFORO_MAX) {
            uiState = uiState.copy(
                personasSala = uiState.personasSala + 1,
                visitasDelDia = uiState.visitasDelDia + 1
            )
        }
    }

    fun onSaleClick() {
        if (uiState.personasSala > 0) {
            uiState = uiState.copy(
                personasSala = uiState.personasSala - 1
            )
        }
    }

    fun onCerrarDiaClick() {
        uiState = uiState.copy(
            personasSala = 0,
            visitasDelDia = 0
        )
    }
}