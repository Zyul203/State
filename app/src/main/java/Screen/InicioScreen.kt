package Screen

import Components.MontoTextField
import Components.Propina
import Components.RedondearSwitchRow
import Components.Resumen
import ViewModel.VM
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@SuppressLint("DefaultLocale")
@Composable
fun InicioScreen( modifier: Modifier = Modifier, viewModel: VM = viewModel())
{
    val state = viewModel.uiState

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Cuenta dividida", fontSize = 25.sp)

        MontoTextField(
            montoInput = state.montoInput,
            onMontoChange = viewModel::onMontoChange
        )

        Text(text = "Propina", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        Propina(
            propinaSeleccionada = state.propinaSeleccionada,
            OnPropinaSeleccionada = viewModel::onPropinaSeleccionada,
            propinaSliderValor = state.propinaSliderValor,
            OnpropinaSliderValor = viewModel::onPropinaSliderValorChange
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Personas", fontSize = 20.sp, fontWeight = FontWeight.Bold)

            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedIconButton(
                    onClick = viewModel::onDecrementarPersonas,
                    enabled = state.personasCantidad > 1,
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape
                ) {
                    Text(text = "−", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }

                Text(
                    text = "${state.personasCantidad}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                OutlinedIconButton(
                    onClick = viewModel::onIncrementarPersonas,
                    enabled = state.personasCantidad < 20,
                    modifier = Modifier.size(36.dp),
                    shape = CircleShape
                ) {
                    Text(text = "+", fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
            }
        }

        RedondearSwitchRow(
            redondearPersona = state.redondearPersona,
            onRedondearChange = viewModel::onRedondearChange
        )

        HorizontalDivider()

        Resumen(
            propinaSeleccionada = state.propinaSeleccionada,
            propinaSliderValor = state.propinaSliderValor,
            subtotal = state.subtotal,
            total = state.total,
            personasCantidad = state.personasCantidad,
            propinaFinal = state.propinaFinal,
            sobrante = state.sobrante,
            redondearPersona = state.redondearPersona
        )
    }
}