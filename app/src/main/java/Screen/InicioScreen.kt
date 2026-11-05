package Screen


import Components.Propina
import Components.Resumen
import android.annotation.SuppressLint
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@SuppressLint("DefaultLocale")
@Composable
fun InicioScreen(modifier : Modifier = Modifier)
{

    var montoInput by rememberSaveable { mutableStateOf("") }
    var propinaSeleccionada by rememberSaveable { mutableStateOf(0) }
    var personasCantidad by rememberSaveable { mutableStateOf(0) }
    var redondearPersona by rememberSaveable { mutableStateOf(false) }
    var subtotal: Float by rememberSaveable {mutableStateOf(0.00f) }
    var total : Float by rememberSaveable {mutableStateOf(0.00f) }
    var totalDividido : Float by rememberSaveable { mutableStateOf(0.00f) }
    var propinaSliderValor by rememberSaveable { mutableStateOf(15f) } // Valor por defecto si elige "Otro"



    Column(modifier = Modifier.fillMaxSize().statusBarsPadding().padding(20.dp), verticalArrangement = Arrangement.spacedBy(15.dp))
    {
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Cuenta dividida", fontSize = 25.sp)

        OutlinedTextField(
            value = montoInput,
            onValueChange = { input -> montoInput = input },
            label = { Text("Monto de la cuenta") },
            modifier = Modifier.fillMaxWidth()

        )

        Text(text = "Propina", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Propina(propinaSeleccionada = propinaSeleccionada, OnPropinaSeleccionada = { cambioState -> propinaSeleccionada = cambioState }
            , propinaSliderValor = propinaSliderValor, OnpropinaSliderValor = {propinaSliderValor = it}) //Cualquiera de las 2

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween)
        {
            Text(text = "Personas", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Row (modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically)
            {
                OutlinedIconButton(
                    onClick = { if (personasCantidad > 0) personasCantidad++ else (personasCantidad - 1) },
                    enabled = personasCantidad > 0,
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape
                ) {
                    Text(text = "−", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Text(
                    text = "$personasCantidad",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )

                OutlinedIconButton(
                    onClick = { if (personasCantidad < 10) personasCantidad-- else(personasCantidad + 1) },
                    enabled = personasCantidad < 10,
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape
                ) {
                    Text(text = "+", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically)
        {
            Text(text = "Redondear por Persona", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Switch(
                checked = redondearPersona,
                onCheckedChange = {cambioState -> if (redondearPersona == false ) redondearPersona = true else redondearPersona = false},
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Magenta,
                    checkedBorderColor = Color.White,
                ))
        }
        HorizontalDivider()

        Resumen(propinaSeleccionada = propinaSeleccionada, propinaSliderValor = propinaSliderValor, subtotal = subtotal, total = total, personasCantidad = personasCantidad)
    }
}

