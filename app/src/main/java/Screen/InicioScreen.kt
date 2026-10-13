package Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.StateTheme
import androidx.compose.runtime.getValue //AGREGAR MANUALMENTE
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue //AGREGAR MANUALMENTE
import androidx.compose.ui.unit.dp


@Composable
fun Inicio(modifier : Modifier, numero: Int, miClick:()-> Unit )
{
    var x by remember { mutableStateOf(0) } //rememberSaveable: Asi se guarda la variable al rotar el celular

    Column(modifier = modifier)
    {
        Text(numero.toString(), fontSize = 50.sp)
        Button(onClick =  miClick) {

            Text("Sumar")
        }
    }
}

@Composable
fun Ejemplo()
{
    var texto by remember {mutableStateOf("")}

    TextField(
        value = texto, onValueChange = {nuevoTexto -> texto = nuevoTexto}
    )
}

@Composable
fun IniScreen(modifier: Modifier)
{
    var x by rememberSaveable {mutableStateOf("0")}
    var y by rememberSaveable {mutableStateOf("0")}
    var c by rememberSaveable {mutableStateOf("0")}


    TextField(value = x, onValueChange = {nuevoTexto -> x = nuevoTexto})
    Spacer(modifier = Modifier.padding(30.dp))
    TextField(value = y, onValueChange = {nuevoTexto -> y = nuevoTexto})
    Spacer(modifier = Modifier.padding(30.dp))
    Button(onClick = {
        c=x+y
    }) { Text("Sumar") }
    Text(c, fontSize = 50.sp)
    Text(c, fontSize = 50.sp)

}


//Investigar por es posible poner it en vez de nuevo texto

@Preview
@Composable
fun Preview(){
    StateTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            var x by remember { mutableStateOf(0) }
            Inicio(modifier = Modifier.padding(innerPadding), x, {x += 1 })
        }

    }
}