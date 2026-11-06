package Screen

import Components.LoginScreen
import Components.PreguntaScreen
import Components.ResultadoScreen
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.runtime.saveable.rememberSaveable


enum class Pantalla
{
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
    Pregunta("¿Qué tipo de dato conviene usar para guardar el valor 3.14?", listOf("Int", "Double", "String", "Boolean"), 1),
    Pregunta("¿Qué estructura condicional se usa para tomar decisiones?", listOf("for", "print", "val", "if / else"), 3),
    Pregunta("¿Cómo se le llama a un bloque de código reutilizable que realiza una tarea específica?", listOf("Variable", "Función", "Comentario", "Bucle"), 1),
    Pregunta("¿Qué estructura se usa para repetir un bloque de código?", listOf("if-else", "val / var", "for / while", "fun"), 2),
    Pregunta("¿Qué símbolos se utilizan para escribir un comentario de una sola línea en Kotlin?", listOf("//", "/*", "##", "--"), 0),
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


