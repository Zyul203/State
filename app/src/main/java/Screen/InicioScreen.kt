package Screen

import Components.ProductoItem
import Components.ResumenCarrito
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.R


data class Producto(
    val nombre: String,
    val precio: Double
)


@Composable
fun InicioScreen(modifier : Modifier = Modifier)
{
    val morado = colorResource(id = R.color.purple_500)

    // Catálogo de productos fijo
    val catalogo: List<Producto> = remember {
        listOf(
            Producto("Tortillas 1 kg", 24.00),
            Producto("Leche 1 L", 27.50),
            Producto("Huevo 12 pz", 52.00),
            Producto("Frijol 1 kg", 38.00)
        )
    }

    val cantidades = remember { mutableStateListOf(0, 0, 0, 0) }
    val totalArticulos = cantidades.sum()

    val subtotal = catalogo.indices.sumOf { i ->
        catalogo[i].precio * cantidades[i]
    }

    // Regla de descuento: 10% si subtotal >= $200
    val descuento = if (subtotal >= 200.0) subtotal * 0.10 else 0.0
    val subtotalConDescuento = subtotal - descuento

    // Regla de envío: $0 sin artículos; GRATIS (0.0) si subtotal tras descuento >= $150; si no, $25.00
    val costoEnvio = when {
        totalArticulos == 0 -> 0.0
        subtotalConDescuento >= 150.0 -> 0.0
        else -> 25.0
    }

    val totalFinal = subtotalConDescuento + costoEnvio

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(20.dp)
        ) {
            // Titulo
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Text(text = "Tienda Don Pepe",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

                // Insignia con el total de artículos9
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .background(morado, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$totalArticulos",
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
                catalogo.forEachIndexed{ index, producto ->
                    ProductoItem(
                        producto = producto,
                        cantidad = cantidades[index],
                        onCantidadChange = { nuevaCantidad -> cantidades[index] = nuevaCantidad }
                    )
                    HorizontalDivider()
                }
            }

            // Card de Resumen del pedido
            ResumenCarrito(
                articulos = totalArticulos,
                subtotal = subtotal,
                descuento = descuento,
                envio = costoEnvio,
                total = totalFinal
            )

            Spacer(modifier = Modifier.height(15.dp))

            // Botones de acción inferiores
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    onClick = {
                        for (i in cantidades.indices) {
                            cantidades[i] = 0
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(50.dp),
                    shape = RoundedCornerShape(25.dp),
                ) {
                    Text(text = "Vaciar", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                }

                Button(
                    onClick = {},
                    enabled = totalArticulos > 0,
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

