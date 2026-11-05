package com.example.state


import Components.AforoControles
import Components.AforoMainCard
import Components.CerrarDiaButton
import Components.VisitasCard
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.gris



const val AFORO_MAX = 50

@Composable
fun AforoScreen(modifier: Modifier = Modifier)
{
    var personasSala by remember { mutableStateOf(30) }
    var visitasDelDia by remember { mutableStateOf(68) }

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

        //SECCION - Aforo disponible
        AforoMainCard(
            personasSala = personasSala,
            aforoMax = AFORO_MAX
        )

        //SECCION - Botones Entra / Sale
        AforoControles(
            onSaleClick = { if (personasSala > 0) personasSala-- }, //En el llamado de funciones es donde le digo a las acciones o estados que hacer
            onEntraClick = {
                if (personasSala < AFORO_MAX)
                {
                    personasSala++
                    visitasDelDia++
                }
            },
            canSale = personasSala > 0, //Mientras se cumplan pasaran un true
            canEntra = personasSala < AFORO_MAX
        )

        // SECCION: Tarjeta de Visitas del Dí
        VisitasCard(visitasDelDia = visitasDelDia)

        // SECCION: Leyenda de colores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround //SpaceAround: Deja mismo espacio entre elementos | SpaceBetween: Primer elemento a la izq y ultimo elemento al final de la pantalla
        ) {
            Text(text = "🟢 Verde < 70%", fontSize = 12.sp, color = Color.DarkGray)
            Text(text = "🟡 Amarillo 70–99%", fontSize = 12.sp, color = Color.DarkGray)
            Text(text = "🔴 Rojo 100%", fontSize = 12.sp, color = Color.DarkGray)
        }

        Spacer(modifier = Modifier.height(140.dp))

        // SECCION: Botón Cerrar Día
        CerrarDiaButton(
            onClick = {
                personasSala = 0
                visitasDelDia = 0
            }
        )
    }
}

