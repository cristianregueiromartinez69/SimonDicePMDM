package com.pmdm.cristian.botonescolores

import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.style.BackgroundColorSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.pmdm.cristian.botonescolores.ui.theme.BotonesColoresTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            interfazColores()
        }
    }
}

@Composable
fun interfazColores() {

    var color_texto by remember { mutableStateOf("") }

    Column {

        Button(onClick = { color_texto = "Rojo" },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
            )) {
            Text(text = "Rojo")

        }
        Button(onClick = { color_texto = "Verde" },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Green,
            )) {
            Text(text = "Verde")
        }
        Button(onClick = { color_texto = "Azul" },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue,
            )) {
            Text(text = "Azul")
        }
        Button(onClick = { color_texto = "Amarillo" },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
                contentColor = Color.Black
            )) {
            Text(text = "Amarillo")
        }




    }



}

