package ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

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
    Pregunta("¿Qué tipo de dato conviene usar para guardar el valor 3.14?", listOf("Int", "Double", "String", "Boolean"), 1),
    Pregunta("¿Qué estructura condicional se usa para tomar decisiones?", listOf("for", "print", "val", "if / else"), 3),
    Pregunta("¿Cómo se le llama a un bloque de código reutilizable que realiza una tarea específica?", listOf("Variable", "Función", "Comentario", "Bucle"), 1),
    Pregunta("¿Qué estructura se usa para repetir un bloque de código?", listOf("if-else", "val / var", "for / while", "fun"), 2),
    Pregunta("¿Qué símbolos se utilizan para escribir un comentario de una sola línea en Kotlin?", listOf("//", "/*", "##", "--"), 0),
)

data class QuizUiState(
    val pantalla: Pantalla = Pantalla.INICIO,
    val nombre: String = "",
    val indicePregunta: Int = 0,
    val seleccionada: Int? = null,
    val respondida: Boolean = false,
    val puntaje: Int = 0
) {
    val preguntaActual: Pregunta
        get() = listaPreguntasQuiz[indicePregunta]

    val numeroPregunta: Int
        get() = indicePregunta + 1

    val totalPreguntas: Int
        get() = listaPreguntasQuiz.size
}

class VM : ViewModel() {

    var uiState by mutableStateOf(QuizUiState())
        private set

    fun onNombreChange(nuevoNombre: String) {
        uiState = uiState.copy(nombre = nuevoNombre)
    }

    fun onComenzar() {
        if (uiState.nombre.trim().length >= 2) {
            uiState = uiState.copy(pantalla = Pantalla.QUIZ)
        }
    }

    fun onSeleccionar(opcionIndex: Int)
    {
        if (!uiState.respondida) {
            uiState = uiState.copy(seleccionada = opcionIndex)
        }
    }

    fun onConfirmar()
    {
        val seleccion = uiState.seleccionada
        if (seleccion != null && !uiState.respondida) {
            val esCorrecta = seleccion == uiState.preguntaActual.correcta
            uiState = uiState.copy(
                respondida = true,
                puntaje = if (esCorrecta) uiState.puntaje + 1 else uiState.puntaje
            )
        }
    }

    fun onSiguiente()
    {
        if (uiState.indicePregunta < listaPreguntasQuiz.size - 1) {
            uiState = uiState.copy(
                indicePregunta = uiState.indicePregunta + 1,
                seleccionada = null,
                respondida = false
            )
        } else {
            uiState = uiState.copy(pantalla = Pantalla.RESULTADO)
        }
    }

    fun onReintentar()
    {
        uiState = uiState.copy(
            indicePregunta = 0,
            puntaje = 0,
            seleccionada = null,
            respondida = false,
            pantalla = Pantalla.QUIZ
        )
    }

    fun onCambiarJugador() {
        uiState = QuizUiState(pantalla = Pantalla.INICIO)
    }
}