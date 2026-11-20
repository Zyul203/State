package Screen

import Components.camposOutlineText
import ViewModel.VM
import ViewModel.loginEstado
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.ui.theme.PurpleClaro
import com.example.state.ui.theme.PurpleFuerte

@Composable
fun InicioScreen(
    modifier: Modifier = Modifier,
    viewModel: VM = viewModel()
) {
    val state = viewModel.uiState

    when (state.estadoLogin) {
        loginEstado.EXITO -> {
            HomeScreen(
                email = state.textoCorreo,
                onLogout = viewModel::onLogout
            )
        }
        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(90.dp)
                        .background(PurpleClaro),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "+", fontSize = 50.sp, color = PurpleFuerte)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Clínica Vida", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Consulta tus citas medicas")

                // Seccion de OutlineTextField
                camposOutlineText(
                    Correo = state.textoCorreo,
                    onCorreoChange = viewModel::onCorreoChange,
                    Contraseña = state.textoContrasena,
                    onContraseñaChange = viewModel::onContrasenaChange,
                    recordarme = state.recordarme,
                    onRecordarmeChange = viewModel::onRecordarmeChange,
                    passwordVisibility = state.passwordVisibility,
                    onPasswordVisibilityChange = viewModel::onPasswordVisibilityChange
                )

                if (state.mensajeError.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = state.mensajeError, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Boton
                Button(
                    onClick = viewModel::onEntrarClick,
                    enabled = state.puedeEntrar,
                    modifier = Modifier.height(60.dp).fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PurpleFuerte,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Entrar", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "¿Olvidaste tu contraseña?", color = PurpleFuerte, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun HomeScreen(email: String, onLogout: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "¡Bienvenido!", fontSize = 30.sp, fontWeight = FontWeight.ExtraBold)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = email, fontSize = 16.sp)

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onLogout,
            modifier = Modifier.width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PurpleFuerte,
                contentColor = Color.White
            )
        ) {
            Text(text = "Cerrar sesión")
        }
    }
}