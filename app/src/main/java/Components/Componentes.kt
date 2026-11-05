package Components

import Screen.Producto
import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.R





@Composable
fun Propina(propinaSeleccionada: Int, OnPropinaSeleccionada: (Int) -> Unit, propinaSliderValor: Float, OnpropinaSliderValor: (Float) -> Unit)
{
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(15.dp))
    {
        // Chip 10%
        FilterChip(
            selected = propinaSeleccionada == 10,
            onClick = { OnPropinaSeleccionada(10) }, // Al hacer clic, cambia el estado a 0
            label = { Text("10%") }
        )
        // Chip 20%
        FilterChip(
            selected = propinaSeleccionada == 20,
            onClick = { OnPropinaSeleccionada(20) }, // Al hacer clic, cambia el estado a 1
            label = { Text("20%") }
        )
        // Chip 30%
        FilterChip(
            selected = propinaSeleccionada == 30,
            onClick = { OnPropinaSeleccionada(30) }, // Al hacer clic, cambia el estado a 2
            label = { Text("30%") }
        )
        // Chip Otro
        FilterChip(
            selected = propinaSeleccionada == 1,
            onClick = { OnPropinaSeleccionada(1) }, // Al hacer clic, cambia el estado a 3
            label = { Text("Otro") }
        )
    }

    if (propinaSeleccionada == 1) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text = "Personalizar propina: ${propinaSliderValor.toInt()}%", fontSize = 14.sp)
            Slider(
                value = propinaSliderValor,
                onValueChange = OnpropinaSliderValor,
                valueRange = 0f..99f,
            )
        }
    }
}


@Composable
fun Resumen(propinaSeleccionada: Int, propinaSliderValor: Float, subtotal: Float, total: Float, personasCantidad: Int, modifier: Modifier = Modifier)
{
    Box(modifier = Modifier.fillMaxWidth().height(250.dp)
        .background(color = Color.LightGray, shape = RoundedCornerShape(20.dp)))
    {
        Column(modifier = Modifier.fillMaxWidth().padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp))
        {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween)
            {
                if ()
                Text(text = "Propina (${propinaSeleccionada} %)")
                Text(text = "$ ${String.format("%.2f",subtotal)}")
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween)
            {
                Text(text = "Total con propina")
                Text(text = "$ ${String.format("%.2f",total)}")
            }
            HorizontalDivider()
            Text(text = "Cada quien paga", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Text(text = "$ ${String.format("%.2f", total)}", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center , fontWeight = FontWeight.ExtraBold, fontSize = 50.sp)
            Text(text = "entre $personasCantidad personas", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
            Text(text = "Sobrante por redondeo $0.00", modifier= Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        }
    }
}


 /*
@Composable
fun ControlesLectura(
    tamano: Float,
    onTamanoChange: (Float) -> Unit,
    interlineado: Interlineado,
    onInterlineadoChange: (Interlineado) -> Unit,
    modoOscuro: Boolean,
    onModoOscuroChange: (Boolean) -> Unit,
    usaSerif: Boolean,
    onUsaSerifChange: (Boolean) -> Unit
)
{
    val colorMorado = colorResource(id = R.color.purple_v1)
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(12.dp)) {

        // Slider de Tamaño de letra (14 a 32 sp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Tamaño de letra")
            Text(text = "${tamano.toInt()} sp", fontWeight = FontWeight.Bold)
        }
        Slider(
            value = tamano,
            onValueChange = onTamanoChange,
            valueRange = 14f..32f,
            colors = SliderDefaults.colors(
                thumbColor = colorMorado,              // Color de la bolita/pulgar
                activeTrackColor = colorMorado,        // Color de la pista recorrida
                inactiveTrackColor = Color.LightGray, // Color de la pista sin recorrer
            )
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "A", fontSize = 14.sp)
            Text(text = "A", fontSize = 22.sp)
        }

        // RadioButton para Interlineado (Compacto, Normal, Amplio)
        Text(text = "Interlineado")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Interlineado.values().forEach { modo ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = interlineado == modo,
                        onClick = { onInterlineadoChange(modo) },

                        )
                    Text(text = modo.name)
                }
            }
        }

        // Switch de Modo Oscuro
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Modo oscuro")
            Switch(
                checked = modoOscuro,
                onCheckedChange = onModoOscuroChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = colorMorado,      // Fondo cuando está encendido
                    checkedBorderColor = colorMorado,
                ))
        }

        // Serif y Sans
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Fuente")

            Spacer(modifier = Modifier.width(160.dp))

            FilterChip(
                selected = usaSerif,
                onClick = { onUsaSerifChange(true) },
                label = { Text("Serif") }
            )
            FilterChip(
                selected = !usaSerif,
                onClick = { onUsaSerifChange(false) },
                label = { Text("Sans") }
            )
        }
    }
}

// 4. Composable de Vista Previa
@Composable
fun VistaPrevia(
    texto: String,
    tamano: Float,
    interlineado: Interlineado,
    modoOscuro: Boolean,
    usaSerif: Boolean
)
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        Text(
            text = "VISTA PREVIA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Modo oscuro
        val colorCard = if (modoOscuro)
        {
            CardDefaults.cardColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        } else {
            CardDefaults.cardColors(
                containerColor = Color.White,
                contentColor = Color.Black
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = colorCard
        ) {
            Box(modifier = Modifier.padding(20.dp))
            {
                Text(
                    text = texto,
                    fontSize = tamano.sp,
                    lineHeight = (tamano * interlineado.medida).sp,
                    fontFamily = if (usaSerif) FontFamily.Serif else FontFamily.SansSerif
                )
            }
        }
    }
}




*/











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
            horizontalArrangement = Arrangement.spacedBy(10.dp)
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

// Card de Resumen
@SuppressLint("DefaultLocale")
@Composable
fun ResumenCarrito(
    articulos: Int,
    subtotal: Double,
    descuento: Double,
    envio: Double,
    total: Double
)
{
    val morado = colorResource(id = R.color.purple_500)

    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE5E4E5)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Artículos", fontSize = 14.sp)
                Text(text = "$articulos", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Subtotal", fontSize = 14.sp)
                Text(
                    text = String.format("$%.2f", subtotal),
                    fontWeight = FontWeight.Bold, fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Descuento 10 %", fontSize = 14.sp)
                Text(
                    text = if (descuento > 0) String.format("-$%.2f", descuento) else "$0.00",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Envío",fontSize = 14.sp)
                Text(
                    text = when {
                        articulos == 0 -> "$0.00"
                        envio == 0.0 -> "GRATIS"
                        else -> String.format("$%.2f", envio)
                    },
                    color = if (envio == 0.0 && articulos > 0) Color(0xFF2E7D32) else Color.Black,
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
                )
                Text(
                    text = String.format("$%.2f", total),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = morado
                )
            }
        }
    }
}
