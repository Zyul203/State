package Screen

import Components.camposOutlineText
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.PurpleClaro
import com.example.state.ui.theme.PurpleFuerte


enum class loginEstado
{
    INACTIVO, ERROR, BLOQUEADO, EXITO
}
@Composable
fun InicioScreen(modifier: Modifier = Modifier) {
    var textoCorreo by remember { mutableStateOf("") }
    var textoContrasena by remember { mutableStateOf("") }
    var recordarme by remember { mutableStateOf(false) }
    var intentosFallidos by remember { mutableStateOf(0) }
    var estadoLogin by remember { mutableStateOf(loginEstado.INACTIVO) }
    val maxIntentos = 3
    val correoCorrecto = "paciente@vida.mx"
    val passwordCorrecta = "Vida2025"
    var passwordVisibility by remember { mutableStateOf(false) }
    val mensajeError = when (estadoLogin)
    {
        loginEstado.ERROR -> "Correo o contraseña incorrectos (intento $intentosFallidos de $maxIntentos)"
        loginEstado.BLOQUEADO -> "Demasiados intentos. Intente de nuevo más tarde."
        else -> ""
    }


    when (estadoLogin) {
        loginEstado.EXITO -> {
            HomeScreen(
                email = textoCorreo,
                onLogout = {
                    textoCorreo = ""
                    textoContrasena = ""
                    recordarme = false
                    intentosFallidos = 0
                    estadoLogin = loginEstado.INACTIVO
                }
            )
        }
        else -> {
            Column(
                modifier = Modifier.fillMaxSize(). padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(90.dp)
                        .background(PurpleClaro),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(text = "+", fontSize = 50.sp,  color = PurpleFuerte)
                }
                Spacer(modifier = Modifier.height(15.dp))
                Text(text = "Clínica Vida", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Consulta tus citas medicas")

                //Seccion de OutlineTextField
                camposOutlineText (
                    Correo = textoCorreo,
                    onCorreoChange = { textoCorreo = it },
                    Contraseña = textoContrasena,
                    onContraseñaChange = { textoContrasena = it },
                    recordarme = recordarme,
                    onRecordarmeChange = { recordarme = it },
                    passwordVisibility = passwordVisibility,
                    onPasswordVisibilityChange = { passwordVisibility = it }
                )

                if (mensajeError.isNotEmpty())
                {
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = mensajeError, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(8.dp))

                //Boton
                Button(
                    onClick = {
                        if (textoCorreo == correoCorrecto && textoContrasena == passwordCorrecta)
                        {
                            estadoLogin = loginEstado.EXITO
                            intentosFallidos = 0
                        } else {
                            intentosFallidos++
                            if (intentosFallidos >= maxIntentos)
                            {
                                estadoLogin = loginEstado.BLOQUEADO
                            } else {
                                estadoLogin = loginEstado.ERROR
                            }
                        }
                    },
                    enabled = estadoLogin != loginEstado.BLOQUEADO,
                    modifier = Modifier.height(60.dp).fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor =  PurpleFuerte, contentColor = Color.White)
                )
                {
                    Text(text = "Entrar", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "¿Olvidaste tu contraseña?", color = PurpleFuerte, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun HomeScreen(email: String, onLogout: () -> Unit)
{
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
            colors = ButtonDefaults.buttonColors(containerColor =  PurpleFuerte, contentColor = Color.White))
        {
            Text(text = "Cerrar sesión")
        }
    }
}
