package com.example.state

import ViewModel.AFORO_MAX
import ViewModel.VM
import Components.AforoControles
import Components.AforoMainCard
import Components.CerrarDiaButton
import Components.VisitasCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.state.ui.theme.gris

@Composable
fun AforoScreen(modifier: Modifier = Modifier, viewModel: VM = viewModel())
{
    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        Spacer(modifier = Modifier.height(25.dp))

        Text(
            text = "Biblioteca Central",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "Sala de estudio - Capacidad $AFORO_MAX",
            fontSize = 14.sp,
            color = gris
        )

        // SECCION - Aforo disponible
        AforoMainCard(
            personasSala = state.personasSala,
            aforoMax = AFORO_MAX
        )

        // SECCION - Botones Entra / Sale
        AforoControles(
            onSaleClick = viewModel::onSaleClick,
            onEntraClick = viewModel::onEntraClick,
            canSale = state.canSale,
            canEntra = state.canEntra
        )

        // SECCION: Tarjeta de Visitas del Día
        VisitasCard(visitasDelDia = state.visitasDelDia)

        // SECCION: Leyenda de colores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Text(text = "🟢 Verde < 70%", fontSize = 12.sp, color = Color.DarkGray)
            Text(text = "🟡 Amarillo 70–99%", fontSize = 12.sp, color = Color.DarkGray)
            Text(text = "🔴 Rojo 100%", fontSize = 12.sp, color = Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(140.dp))

        // SECCION: Botón Cerrar Día
        CerrarDiaButton(
            onClick = viewModel::onCerrarDiaClick
        )
    }
}