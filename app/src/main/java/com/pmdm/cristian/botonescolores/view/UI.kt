package com.pmdm.cristian.botonescolores.view

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.cristian.botonescolores.model.Colores
import com.pmdm.cristian.botonescolores.model.DataRecord
import com.pmdm.cristian.botonescolores.modelview.MyViewModel

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



