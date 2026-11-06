package Components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun camposOutlineText(Correo: String,
               onCorreoChange: (String)->Unit,
               Contraseña: String,
               onContraseñaChange: (String)->Unit,
               recordarme: Boolean,
               onRecordarmeChange: (Boolean) -> Unit,
               passwordVisibility: Boolean,
               onPasswordVisibilityChange: (Boolean) -> Unit)
{


    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center)
    {
        OutlinedTextField(value = Correo, onValueChange = onCorreoChange, label = { Text("Correo") })
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = Contraseña,
            onValueChange = onContraseñaChange,
            label = { Text("Contraseña") },
            visualTransformation = if (passwordVisibility)
            {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                TextButton(onClick = { onPasswordVisibilityChange(!passwordVisibility) })
                {
                    Text( text = if (passwordVisibility) "👁️" else "👁️‍🗨️", fontSize = 18.sp)
                }
            },
        )
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start)
        {
            Checkbox(checked = recordarme, onCheckedChange = onRecordarmeChange)
            Text(text = "Recordarme")
        }
    }
}