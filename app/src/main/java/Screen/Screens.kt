package Screen

import Components.LoginScreen
import Components.PreguntaScreen
import Components.ResultadoScreen
import ViewModel.Pantalla
import ViewModel.VM
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Screens(
    modifier: Modifier = Modifier,
    viewModel: VM = viewModel()
) {
    val state = viewModel.uiState

    when (state.pantalla) {
        Pantalla.INICIO -> LoginScreen(
            nombre = state.nombre,
            onNombreChange = viewModel::onNombreChange,
            onComenzar = viewModel::onComenzar,
            modifier = modifier
        )

        Pantalla.QUIZ -> PreguntaScreen(
            pregunta = state.preguntaActual,
            numero = state.numeroPregunta,
            total = state.totalPreguntas,
            puntajeActual = state.puntaje,
            seleccionada = state.seleccionada,
            respondida = state.respondida,
            onSeleccionar = viewModel::onSeleccionar,
            onConfirmar = viewModel::onConfirmar,
            onSiguiente = viewModel::onSiguiente,
            modifier = modifier
        )

        Pantalla.RESULTADO -> ResultadoScreen(
            nombre = state.nombre,
            puntaje = state.puntaje,
            total = state.totalPreguntas,
            onReintentar = viewModel::onReintentar,
            onCambiarJugador = viewModel::onCambiarJugador,
            modifier = modifier
        )
    }
}