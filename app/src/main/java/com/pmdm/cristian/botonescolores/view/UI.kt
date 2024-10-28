package com.pmdm.cristian.botonescolores.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.cristian.botonescolores.model.Colores
import com.pmdm.cristian.botonescolores.model.DataRecord
import com.pmdm.cristian.botonescolores.modelview.MyViewModel
import kotlin.random.Random

@Composable
fun initialText(bienvenido:String) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            start = 90.dp,
            top = 80.dp
        )
    ) {
        Text(
            text = bienvenido,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

    }

}

@Composable
fun showRecord(record:Int){

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp, start = 130.dp)) {

        Text(text = "Record: $record" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)

        Log.d("Record", record.toString())

    }

}

@Composable
fun botonesFila1(misColores: MutableList<Int>) {
    val listaColores = listOf(Color.Red, Color.Green)

    @Composable
    fun crearBoton(color: Color, colorValor: Int) {
        Button(
            onClick = { misColores.add(colorValor) },
            colors = ButtonDefaults.buttonColors(containerColor = color),
            modifier = Modifier
                .clip(CircleShape)
                .padding(3.dp)
                .size(95.dp)
        ) {
        }
    }

    for (color in listaColores) {
        when (color) {
            Color.Red -> crearBoton(color, Colores.ROJO.valorColor)
            Color.Green -> crearBoton(color, Colores.VERDE.valorColor)
        }
    }
}

@Composable
fun botonesFila2(misColores: MutableList<Int>) {
    val listaColores = listOf(Color.Blue, Color.Yellow)

    @Composable
    fun crearBoton(color: Color, colorValor: Int) {
        Button(
            onClick = { misColores.add(colorValor) },
            colors = ButtonDefaults.buttonColors(containerColor = color),
            modifier = Modifier
                .clip(CircleShape)
                .padding(3.dp)
                .size(95.dp)
        ) {
        }
    }

    for (color in listaColores) {
        when (color) {
            Color.Blue -> crearBoton(color, Colores.AZUL.valorColor)
            Color.Yellow -> crearBoton(color, Colores.AMARILLO.valorColor)
        }
    }
}

@Composable
fun showRondas(numeroRondas: Int){

    Text(

        text = "Ronda: $numeroRondas",

        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        modifier = Modifier
            .padding(top = 100.dp)

    )

}

@Composable
fun showButtonStart(isButtonvisible: MutableState<Boolean>):Boolean {
    val rosa = Color(0xFFFF00C9)
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp))
    {

        if(isButtonvisible.value){
            Button(onClick = { isButtonvisible.value = false },
                colors = ButtonDefaults.buttonColors(
                    containerColor = rosa,
                    contentColor = Color.Black
                ),
                modifier = Modifier
                    .size(145.dp)
                    .clip(RectangleShape)
            ) {
                Text(text = "Start",
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold)

            }
        }


    }
    return isButtonvisible.value
}

@Composable
fun startGame(
    isStartButtonPressed: MutableState<Boolean>,
    presioneStart1: MutableState<Boolean>,
    secuencia: MutableList<Int>
) {

    if (!showButtonStart(isStartButtonPressed)) {

        if (!presioneStart1.value) {
            showToast(secuencia = secuencia, message = "Teclea la secuencia correcta")
            presioneStart1.value = true
        }


    }
}

@Composable
fun showToast(context: Context = LocalContext.current, secuencia:MutableList<Int>, message: String, duration: Int = Toast.LENGTH_LONG){
    val random = Random
    for (i in 1..5){
        secuencia.add(random.nextInt(4) + 1)
    }
    Toast.makeText(context,secuencia.toString(),duration).show()

}





