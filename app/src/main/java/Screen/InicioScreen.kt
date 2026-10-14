package Screen

import Components.myCart
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue //AGREGAR MANUALMENTE
import androidx.compose.ui.unit.dp



//Buscar como pasar funciones como parametros. Como recuperar valores de cuadro de texto, una variable asignar a un elemento. Que es un viewModel
//Asociar una variable de estado a un cuadro de texto para manipularlo
//Aprender a pasar funciones con funciones de otros parametros
/*
Composable muestra algo grafico

fun sumar(a:() -> Unit, b:c () -> Unit)
{
a(); b();
}

 */


@Composable
fun InicioScreen(modifier: Modifier)
{
    var nombreEstado by rememberSaveable() {mutableStateOf("") }
    var ListaNombres by
}









//PATRON State Hoisting (hoist state)

@Composable
fun InicioScreeen{
//Juego del gato (variables de estado)
//Lista normal (lista con las rrrrr), lista normal, lista pasandole texto con un estado. Lista pintada. Estados superiores, pasando funcionalidades, calculadora
    Column(modifier = modifier)
    {
        Box(modifier = Modifier.height(200.dp).padding(15.dp).fillMaxWidth())
        Column(modifier = Modifier.fillMaxSize())
        {
            Spacer(modifier=Modifier.padding(10.dp))
            TextField(nombre, onValueChange = )
        }
    }

    LazyColumn(modifier = Modifier.weight(1f).fillMaxWidth()) {
        items(Lista )
    }

    //Listas normales la 4
}//Calculadora simple P6
//Poner uan caja de texto luego abajo un boton y luego carts con listas y una tachita y nombre
//Otro boton a la cart, borrrar (oara eiminar y atualizar)
//Acostumbrese a crear componentes que reciban datos y funciones
@Composable
fun  InicioScreen(modifier:Modifier) {
    Column(modifier = Modifier) {
        var x by rememberSaveable { mutableStateOf("añadir nombre") }
        val listaElementos = rememberSaveable{ mutableStateListOf("")}

        Column(modifier = Modifier.
        fillMaxWidth()
            .height(200.dp)) {

            TextField(value =x, onValueChange = {textonuevo -> x=textonuevo})
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick ={if(x.isNotEmpty()){
                listaElementos.add(x)}
            }) {Text("Agregar") }

        }
        LazyColumn(modifier = modifier
            .fillMaxWidth()
            .weight(1f)) {
            items(listaElementos) { nombre ->
                myCart(modifier = modifier, nombre)

            }
        }

    }
}