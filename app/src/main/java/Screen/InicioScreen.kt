package Screen

import Components.ProductoItem
import Components.ResumenCarrito
import ViewModel.VM
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.R

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    viewModel: VM = viewModel()
) {
    val morado = colorResource(id = R.color.purple_500)
    val state = viewModel.uiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(20.dp)
    ) {
        // Título
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Tienda Don Pepe",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            // Insignia con el total de artículos
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(morado, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${state.totalArticulos}",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Lista de productos
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            state.catalogo.forEachIndexed { index, producto ->
                ProductoItem(
                    producto = producto,
                    cantidad = state.cantidades[index],
                    onCantidadChange = { nuevaCantidad ->
                        viewModel.onCantidadChange(index, nuevaCantidad)
                    }
                )
                HorizontalDivider()
            }
        }

        if (state.pedidoConfirmado) {
            Text(
                text = "¡Pedido confirmado!",
                color = Color(0xFF2E7D32),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // Card de Resumen del pedido
        ResumenCarrito(
            articulos = state.totalArticulos,
            subtotal = state.subtotal,
            descuento = state.descuento,
            envio = state.costoEnvio,
            total = state.totalFinal
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Botones de acción inferiores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = viewModel::onVaciarClick,
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(text = "Vaciar", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            }

            Button(
                onClick = viewModel::onConfirmarPedidoClick,
                enabled = state.puedeConfirmar,
                modifier = Modifier
                    .weight(1.5f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor = morado)
            ) {
                Text(text = "Confirmar pedido", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

