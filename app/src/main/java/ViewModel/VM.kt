package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlin.math.ceil

data class CuentaUiState(
    val montoInput: String = "",
    val propinaSeleccionada: Int = 0,
    val personasCantidad: Int = 1,
    val redondearPersona: Boolean = false,
    val propinaSliderValor: Float = 0.0f
) {
    val montoInputDouble: Double
        get() = montoInput.toDoubleOrNull() ?: 0.0

    val propinaFinal: Double
        get() = if (propinaSeleccionada != 1) {
            montoInputDouble * (propinaSeleccionada / 100.0)
        } else {
            montoInputDouble * (propinaSliderValor / 100.0)
        }

    val subtotal: Double
        get() = montoInputDouble + propinaFinal

    private val totalSinRedondeo: Double
        get() = subtotal / personasCantidad

    val total: Double
        get() = if (redondearPersona) ceil(totalSinRedondeo) else totalSinRedondeo

    val sobrante: Double
        get() = if (redondearPersona) total - totalSinRedondeo else 0.0
}

class VM : ViewModel() {

    var uiState by mutableStateOf(CuentaUiState())
        private set

    fun onMontoChange(nuevoMonto: String) {
        uiState = uiState.copy(montoInput = nuevoMonto)
    }

    fun onPropinaSeleccionada(propina: Int) {
        uiState = uiState.copy(propinaSeleccionada = propina)
    }

    fun onPropinaSliderValorChange(valor: Float) {
        uiState = uiState.copy(propinaSliderValor = valor)
    }

    fun onDecrementarPersonas() {
        if (uiState.personasCantidad > 1) {
            uiState = uiState.copy(personasCantidad = uiState.personasCantidad - 1)
        }
    }

    fun onIncrementarPersonas() {
        if (uiState.personasCantidad < 20) {
            uiState = uiState.copy(personasCantidad = uiState.personasCantidad + 1)
        }
    }

    fun onRedondearChange(redondear: Boolean) {
        uiState = uiState.copy(redondearPersona = redondear)
    }
}