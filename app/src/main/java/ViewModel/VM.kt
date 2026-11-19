package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class Producto( val nombre: String, val precio: Double)

data class CarritoUiState(
    val catalogo: List<Producto> = listOf(
        Producto("Tortillas 1 kg", 24.00),
        Producto("Leche 1 L", 27.50),
        Producto("Huevo 12 pz", 52.00),
        Producto("Frijol 1 kg", 38.00)
    ),
    val cantidades: List<Int> = List(4) { 0 })
{
    val totalArticulos: Int
        get() = cantidades.sum()

    val subtotal: Double
        get() = catalogo.indices.sumOf { i ->
            catalogo[i].precio * cantidades[i]
        }

    // 10% si subtotal >= $200
    val descuento: Double
        get() = if (subtotal >= 200.0) subtotal * 0.10 else 0.0

    val subtotalConDescuento: Double
        get() = subtotal - descuento

    // $0 sin artículos; GRATIS (0.0) si subtotal tras descuento >= $150; si no, $25.00
    val costoEnvio: Double
        get() = when {
            totalArticulos == 0 -> 0.0
            subtotalConDescuento >= 150.0 -> 0.0
            else -> 25.0
        }

    val totalFinal: Double
        get() = subtotalConDescuento + costoEnvio

    val puedeConfirmar: Boolean
        get() = totalArticulos > 0
}

class VM : ViewModel()
{
    var uiState by mutableStateOf(CarritoUiState())
        private set

    fun onCantidadChange(index: Int, nuevaCantidad: Int) {
        if (index !in uiState.cantidades.indices) return
        val cantidadAjustada = nuevaCantidad.coerceIn(0, 10)

        val nuevasCantidades = uiState.cantidades.toMutableList().apply {
            this[index] = cantidadAjustada
        }

        uiState = uiState.copy(
            cantidades = nuevasCantidades,
            pedidoConfirmado = false
        )
    }

    fun onVaciarClick() {
        uiState = uiState.copy(
            cantidades = List(uiState.catalogo.size) { 0 },
            pedidoConfirmado = false
        )
    }

}