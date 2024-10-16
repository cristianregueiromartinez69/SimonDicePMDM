package com.pmdm.cristian.botonescolores

import android.content.Context
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.Circle
import com.pmdm.cristian.botonescolores.ui.theme.BotonesColoresTheme
import com.pmdm.cristian.botonescolores.Colores
import kotlin.random.Random

val recordJugador = DataRecord(0)

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
    var lista_colores = remember { mutableStateListOf<Int>() }
    var record by remember { mutableStateOf(0) }
    val isStartButtonPressed = remember { mutableStateOf(true) }
    var numero_ronda by remember { mutableStateOf(0) }
    var presioneStart by remember { mutableStateOf(false) }

    var secuencia by remember { mutableStateOf(mutableListOf<Int>()) }

    recordJugador.saveRecord(record)

    Box (modifier = Modifier
        .fillMaxSize()
        ){
        val backgroundImage = painterResource(id = R.drawable.fondo)
        Image(
            painter = backgroundImage,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column {
            initialText()
            showRecord(record)
        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(70.dp)
                .padding(top = 190.dp, start = 15.dp)
        ) {

            Row {

                Button(
                    onClick = {
                        color_texto = "Rojo"
                        lista_colores.add(Colores.ROJO.valorColor)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(3.dp)
                        .size(95.dp)
                ) {

                }


                Button(
                    onClick = {
                        color_texto = "Verde"
                        lista_colores.add(Colores.VERDE.valorColor)

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Green,
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(3.dp)
                        .size(95.dp)
                ) {

                }
            }

            Row {
                Button(
                    onClick = {
                        color_texto = "Azul"
                        lista_colores.add(Colores.AZUL.valorColor)

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(3.dp)
                        .size(95.dp)
                ) {

                }

                Button(
                    onClick = {
                        color_texto = "Amarillo"
                        lista_colores.add(Colores.AMARILLO.valorColor)

                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Yellow,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .clip(CircleShape)
                        .padding(3.dp)
                        .size(95.dp)
                ) {

                }
            }

            Text(

                text = "Ronda: $numero_ronda",

                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(top = 100.dp)

            )

            if(!showButtonStart(isStartButtonPressed)){

                if(!presioneStart){
                    showToast(secuencia = secuencia, message = "Teclea la secuencia correcta")
                    presioneStart = true
                }


            }
            if(lista_colores.size == 5){
                if(winOrLose(secuencia,lista_colores)){
                    showWin(context = LocalContext.current, message = "Has ganado")
                    numero_ronda++
                    record++
                    lista_colores.clear()
                }else{
                    showLose(context = LocalContext.current, message = "Has perdido")
                    lista_colores.clear()
                }

            }


        }
        Log.d("Colores", color_texto)
    }

}



@Composable
fun initialText() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            start = 90.dp,
            top = 80.dp
        )
    ) {
        Text(
            text = "SIMON DICE!",
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

        Text(text = "Record: $record",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)

        Log.d("Record", recordJugador.record.toString())

    }

}

@Composable
fun showButtonStart(isButtonvisible:MutableState<Boolean>):Boolean {
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

fun winOrLose(secuencia:MutableList<Int>,lista_colores:MutableList<Int>):Boolean{

    return secuencia == lista_colores
}

@Composable
fun showToast(context: Context = LocalContext.current, secuencia:MutableList<Int>, message: String,duration: Int = Toast.LENGTH_LONG){
    val random = Random
    for (i in 1..5){
            secuencia.add(random.nextInt(4) + 1)
    }
    Toast.makeText(context,secuencia.toString(),duration).show()

}

fun showLose(context: Context, message: String,duration: Int = Toast.LENGTH_LONG){

    Toast.makeText(context,message,duration).show()
}

fun showWin(context: Context, message: String,duration: Int = Toast.LENGTH_LONG){

    Toast.makeText(context,message,duration).show()
}

/**
 * variables a necesitar logica simon dice:
 * 1. booleanos para saber si ganaste 1 ronda o no
 * 2. variable incremento para ir aumentando el numero de colores a iluminar
 * 3. lista que se va limpiando a cada ronda y que guarda los colores iluminados
 *
 * logica:
 * 1. a cada ronda, se ilumina un color y tienes que pulsarlo en el orden establecido
 * 2. por cada ronda se aumenta en 1
 * 3. si ganas aumenta la ronda y el record
 * 4. comparamos 2 listas, las que pulsas y las que guardas colores, si hay coincidencias, ganaste, si no, perdiste
 */

/**
 * Todo: Una rama nueva
 * 1. Inicializar una secuencia de 5 numeros randoms entre (1-4)
 * 2. una toast muestra la secuencia con un periodo corto
 * 3. El usuario marca la secuencia
 * 4. Se comprueba en cada click si coincide
 * 5. Si falla game over sale una toast game over, si llega al final sin fallar congratulations
 */