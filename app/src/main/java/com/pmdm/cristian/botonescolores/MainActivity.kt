package com.pmdm.cristian.botonescolores

import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.style.BackgroundColorSpan
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.Circle
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
fun interfazColores(modifier: Modifier = Modifier) {
    var color_texto by remember { mutableStateOf("") }
    var lista_colores = remember { mutableStateListOf<String>() }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(70.dp)
            .padding(top = 150.dp, start = 25.dp)
    ) {

        Row {
            Button(onClick = {
                color_texto = "Rojo"
                lista_colores.add("Rojo")
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red,
                )) {
                Text(text = "Rojo")
            }

            Button(onClick = {
                color_texto = "Verde"
                lista_colores.add("Verde")
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Green,
                )) {
                Text(text = "Verde")
            }
        }

        Row {
            Button(onClick = {
                color_texto = "Azul"
                lista_colores.add("Azul")
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue,
                )) {
                Text(text = "Azul")
            }

            Button(onClick = {
                color_texto = "Amarillo"
                lista_colores.add("Amarillo")
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Yellow,
                    contentColor = Color.Black
                )) {
                Text(text = "Amarillo")
            }
        }

        Text(text = "Color seleccionado: $color_texto")

        Text(text = "Colores seleccionados:")
        lista_colores.forEach { color ->
            Text(text = color)
        }
    }
}

@Composable
fun initialText(){

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            start = 50.dp,
            top = 100.dp
        )) {
        Text(
            text = "ELIGE UN COLOR!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

    }

}

@Composable
fun confirmationColor(name:String){

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(
            top = 80.dp,
            end = 20.dp
        )) {

        if(name.equals("Rojo")){
            Text(
                text = "Has pulsado el boton $name",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
        else if (name.equals("Verde")){
            Text(
                text = "Has pulsado el boton $name",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp


            )
        }
        else if (name.equals("Amarillo")){
            Text(
                text = "Has pulsado el boton $name",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp

            )
        }
        else if (name.equals("Azul")){
            Text(
                text = "Has pulsado el boton $name",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp

            )
        }
    }
}

