package Screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.state.ui.theme.StateTheme
import androidx.compose.runtime.getValue //AGREGAR MANUALMENTE
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue //AGREGAR MANUALMENTE
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.MultiParagraphIntrinsics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun Inicio(modifier : Modifier = Modifier)
{
    var contador by rememberSaveable { mutableIntStateOf(0) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(25.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),


    )
    {
        Spacer(modifier = Modifier.height(25.dp))

        Text(text = "Contador",
            fontSize = 25.sp, fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween)
        {
            Button(onClick = { contador++ }, modifier = Modifier.width(150.dp))
            {
                Text(text = "Sumar")
            }
            Text(text = "$contador", fontWeight = FontWeight.Bold)
        }
    }
}

