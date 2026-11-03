package Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun InicioScreen(modifier: Modifier = Modifier)
{
    val listaDeLetras = rememberSaveable { mutableStateListOf<String>() }

    Column(
        modifier = Modifier.fillMaxSize().padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    )
    {
        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "Lista de \" R \"",
            fontSize = 25.sp, fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = { listaDeLetras.add("R") },
            modifier = Modifier.fillMaxWidth()
        )
        { Text("Agregar ' R '") }

        HorizontalDivider()

        // Muestra todas las 'R' añadidas hacia abajo
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        )
        {
            items(listaDeLetras) { letra ->
                Text(
                    text = letra,
                    fontSize = 22.sp, // Un poco más grande para que resalte
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }
        }
    }
}