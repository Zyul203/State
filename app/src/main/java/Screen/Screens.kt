package Screen

import Components.LoginScreen
import Components.PreguntaScreen
import Components.ResultadoScreen
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview


enum class Pantalla {
    INICIO,
    QUIZ,
    RESULTADO
}

data class Pregunta(
    val texto: String,
    val opciones: List<String>,
    val correcta: Int
)


val listaPreguntasQuiz = listOf(
    Pregunta("que tipo de dato conviene usar para guardar el valor 3.14?", listOf("Int", "Double", "String", "Boolean"), 1),
    Pregunta("cual es la extension de los archivos de codigo kotlin?", listOf(".java", ".kt", ".xml", ".js"), 1),
    Pregunta("que estructura se usa para repetir un bloque de codigo?", listOf("if-else", "for / while", "val / var", "fun"), 1),
    Pregunta("que palabra clave se usa para declarar una variable mutable en kotlin?", listOf("val", "var", "const", "static"), 1),
    Pregunta("que layout se usa para alinear elementos uno debajo del otro en compose?", listOf("Row", "Column", "Box", "Spacer"), 1)
)

@Composable
fun Screens(modifier: Modifier = Modifier) {
    var pantalla by rememberSaveable { mutableStateOf(Pantalla.INICIO) }
    var nombre by rememberSaveable { mutableStateOf("") }
    var indicePregunta by rememberSaveable { mutableStateOf(0) }
    var seleccionada by rememberSaveable { mutableStateOf<Int?>(null) }
    var respondida by rememberSaveable { mutableStateOf(false) }
    var puntaje by rememberSaveable { mutableStateOf(0) }

    val preguntaActual = listaPreguntasQuiz[indicePregunta]

    when (pantalla) {
        Pantalla.INICIO -> LoginScreen(
            nombre = nombre,
            onNombreChange = { nombre = it },
            onComenzar = {
                if (nombre.trim().length >= 2) {
                    pantalla = Pantalla.QUIZ
                }
            },
            modifier = modifier
        )
        Pantalla.QUIZ -> PreguntaScreen(
            pregunta = preguntaActual,
            numero = indicePregunta + 1,
            total = listaPreguntasQuiz.size,
            puntajeActual = puntaje,
            seleccionada = seleccionada,
            respondida = respondida,
            onSeleccionar = { seleccionada = it },
            onConfirmar = {
                if (seleccionada != null) {
                    respondida = true
                    if (seleccionada == preguntaActual.correcta) {
                        puntaje++
                    }
                }
            },
            onSiguiente = {
                if (indicePregunta < listaPreguntasQuiz.size - 1) {
                    indicePregunta++
                    seleccionada = null
                    respondida = false
                } else {
                    pantalla = Pantalla.RESULTADO
                }
            },
            modifier = modifier
        )
        Pantalla.RESULTADO -> ResultadoScreen(
            nombre = nombre,
            puntaje = puntaje,
            total = listaPreguntasQuiz.size,
            onReintentar = {
                indicePregunta = 0
                puntaje = 0
                seleccionada = null
                respondida = false
                pantalla = Pantalla.QUIZ
            },
            onCambiarJugador = {
                nombre = ""
                indicePregunta = 0
                puntaje = 0
                seleccionada = null
                respondida = false
                pantalla = Pantalla.INICIO
            },
            modifier = modifier
        )
    }
}


