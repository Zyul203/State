package com.example.state

import Screen.Inicio
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.state.ui.theme.StateTheme
import androidx.compose.runtime.getValue //AGREGAR MANUALMENTE
import androidx.compose.runtime.setValue //AGREGAR MANUALMENTE

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var x by remember { mutableStateOf(0) }
                    Inicio(modifier = Modifier.padding(innerPadding), x, {x += 1 })
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    StateTheme {
        Greeting("Android")
    }
}