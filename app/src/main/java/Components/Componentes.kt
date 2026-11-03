package Components

import Screen.Producto
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Locale


// Componente Fila de Producto
@SuppressLint("DefaultLocale")
@Composable
fun ProductoItem(
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    )
    {
        Column {
            Text(
                text = producto.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = String.format("$%.2f c/u", producto.precio),
                fontSize = 13.sp,
            )
        }

        // Controles de cantidad
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedIconButton(
                onClick = { if (cantidad > 0) onCantidadChange(cantidad - 1) },
                enabled = cantidad > 0,
                modifier = Modifier.size(36.dp),
                shape = CircleShape
            ) {
                Text(text = "−", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }

            Text(
                text = "$cantidad",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            OutlinedIconButton(
                onClick = { if (cantidad < 10) onCantidadChange(cantidad + 1) },
                enabled = cantidad < 10,
                modifier = Modifier.size(36.dp),
                shape = CircleShape
            ) {
                Text(text = "+", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// 4. Componente Card de Resumen (Stateless)
@Composable
fun ResumenCarrito(
    articulos: Int,
    subtotal: Double,
    descuento: Double,
    envio: Double,
    total: Double
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF3EDF7)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Artículos", color = Color(0xFF49454F), fontSize = 14.sp)
                Text(text = "$articulos", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Subtotal", color = Color(0xFF49454F), fontSize = 14.sp)
                Text(
                    text = String.format(Locale.US, "$%.2f", subtotal),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Descuento 10 %", color = Color(0xFF49454F), fontSize = 14.sp)
                Text(
                    text = if (descuento > 0) String.format(Locale.US, "-$%.2f", descuento) else "$0.00",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Envío", color = Color(0xFF49454F), fontSize = 14.sp)
                Text(
                    text = when {
                        articulos == 0 -> "$0.00"
                        envio == 0.0 -> "GRATIS"
                        else -> String.format(Locale.US, "$%.2f", envio)
                    },
                    color = if (envio == 0.0 && articulos > 0) Color(0xFF2E7D32) else Color(0xFF1D1B20),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TOTAL",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                    color = Color(0xFF1D1B20)
                )
                Text(
                    text = String.format(Locale.US, "$%.2f", total),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = Color(0xFF65558F)
                )
            }
        }
    }
}
