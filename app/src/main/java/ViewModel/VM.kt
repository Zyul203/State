package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

const val AFORO_MAX = 50

data class AforoUiState( val personasSala: Int = 30,  val visitasDelDia: Int = 68)
{
    val canSale: Boolean get() = personasSala > 0
    val canEntra: Boolean get() = personasSala < AFORO_MAX
}

class VM : ViewModel()
{
    var uiState by mutableStateOf(AforoUiState())

    fun onEntraClick()
    {
        if (uiState.personasSala < AFORO_MAX) {
            uiState = uiState.copy(
                personasSala = uiState.personasSala + 1,
                visitasDelDia = uiState.visitasDelDia + 1
            )
        }
    }

    fun onSaleClick()
    {
        if (uiState.personasSala > 0) {
            uiState = uiState.copy(
                personasSala = uiState.personasSala - 1
            )
        }
    }

    fun onCerrarDiaClick()
    {
        uiState = uiState.copy(
            personasSala = 0,
            visitasDelDia = 0
        )
    }
}