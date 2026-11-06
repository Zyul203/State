package Screen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



data class ElementoColor(
    val texto: String,
    val colorFondo: Color,
    val colorTexto: Color
)

@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var colorInput by rememberSaveable { mutableStateOf("") }
    val listaDeColores= rememberSaveable() { mutableStateListOf<ElementoColor>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp),
    )
    {
        Spacer(modifier = Modifier.height(25.dp))

        OutlinedTextField(
            value = colorInput,
            onValueChange = { colorInput = it },
            label = { Text("Escribe un color") },
            modifier = Modifier.fillMaxWidth(),
        )

        Button(
            onClick = {
                if (colorInput.isNotBlank())
                {
                    val fondoProcesado = when (colorInput.trim().lowercase())
                    {
                        "rojo" -> Color.Red
                        "azul" -> Color.Blue
                        "verde" -> Color.Green
                        "amarillo" -> Color.Yellow
                        "rosa" -> Color.Magenta
                        "gris" -> Color.Gray
                        "naranja" -> Color(0xFFFFA500)
                        "morado" -> Color(0xFF800080)
                        "negro" -> Color.Black
                        "blanco" -> Color.White
                        else -> Color.White
                    }

                    val textoProcesado = if (fondoProcesado == Color.Black || fondoProcesado == Color(0xFF800080)) {
                        Color.White
                    } else {
                        Color.Black
                    }

                    // Añadimos el nuevo objeto estructurado a la lista
                    listaDeColores.add(
                        ElementoColor(
                            texto = colorInput,
                            colorFondo = fondoProcesado,
                            colorTexto = textoProcesado
                        )
                    )

                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar a la lista")
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 5.dp))

        // Lista que muestra los resultados
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(listaDeColores) { item ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp))
                        .background(item.colorFondo)
                        .padding(15.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Text(
                        text = item.texto,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = item.colorTexto
                    )
                }
            }
        }
    }
}