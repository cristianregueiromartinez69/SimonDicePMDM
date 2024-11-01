package com.pmdm.cristian.botonescolores.view

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pmdm.cristian.botonescolores.R
import com.pmdm.cristian.botonescolores.model.Colores
import com.pmdm.cristian.botonescolores.model.Datos
import com.pmdm.cristian.botonescolores.modelview.MyViewModel
import kotlin.text.clear

/**
 * Interfaz con el texto del saludo de simon dice
 */
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

/**
 * Interfaz para mostrar el numero de aciertos del usuario
 */
@Composable
fun showAciertos(aciertos:Int){

    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp, start = 130.dp)) {

        Text(text = "Aciertos: $aciertos" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)

        Log.d("Record", aciertos.toString())

    }

}

/**
 * Interfaz para mostrar el record maximo del usuario en el juego
 */
@Composable
fun RecordMaximo(record: Int){
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
            .padding(top = 10.dp, start = 130.dp)) {

        Text(text = "Record: $record" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)

    }
}

/**
 * Interfaz con el boton rojo
 */
@Composable
fun buttonRed(viewModel: MyViewModel, listaColores: MutableList<Int>, colorValor:Int){
    Button(
        onClick = {
            viewModel.addColor(colorValor,listaColores)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
        ),
        modifier = Modifier
            .clip(CircleShape)
            .padding(3.dp)
            .size(95.dp)
    ){

    }
}

/**
 * Interfaz con el boton verde
 */
@Composable
fun buttonGreen(viewModel: MyViewModel, listaColores: MutableList<Int>, colorValor:Int){
    Button(
        onClick = {
            viewModel.addColor(colorValor,listaColores)        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Green,
        ),
        modifier = Modifier
            .clip(CircleShape)
            .padding(3.dp)
            .size(95.dp)
    ){

    }
}

/**
 * Interfaz con el boton azul
 */
@Composable
fun buttonBlue(viewModel: MyViewModel, listaColores: MutableList<Int>, colorValor:Int){
    Button(
        onClick = {
            viewModel.addColor(colorValor,listaColores)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue,
        ),
        modifier = Modifier
            .clip(CircleShape)
            .padding(3.dp)
            .size(95.dp)
    ){

    }
}

/**
 * Interfaz con el boton amarillo
 */
@Composable
fun buttonYellow(viewModel: MyViewModel, listaColores: MutableList<Int>, colorValor:Int){
    Button(
        onClick = {
            viewModel.addColor(colorValor,listaColores)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Yellow,
        ),
        modifier = Modifier
            .clip(CircleShape)
            .padding(3.dp)
            .size(95.dp)
    ){

    }
}



/**
 * Interfaz para mostrar las rondas que lleva el usuario
 */
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

/**
 * Interfaz que muestra el boton de start
 */
@Composable
fun showButtonStart(isButtonvisible: MutableState<Boolean>, playGame:(boolean:MutableState<Boolean>) -> Unit):Boolean {
    val rosa = Color(0xFFFF00C9)
    Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(top = 40.dp))
    {

        if(isButtonvisible.value){
            Button(onClick = { playGame(isButtonvisible) },
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

/**
 * Interfaz para mostrar una toast de inicio de juego cuando el usuario pulsa el boton start
 */
@Composable
fun startGame(
    isStartButtonPressed: MutableState<Boolean>,
    presioneStart1: MutableState<Boolean>,
    viewModel: MyViewModel
) {

    if (!showButtonStart(isStartButtonPressed, viewModel::logicalStartButton)) {

        if (!presioneStart1.value) {
            showToast(context = LocalContext.current, message = "Pulsa 1 boton")
            presioneStart1.value = true
        }


    }

    viewModel.loseGameAndShowAgainToast(isStartButtonPressed, presioneStart1)
}



/**
 * Interfaz para indicar un mensaje en el juego
 */
@Composable
fun showToast(context: Context = LocalContext.current, message: String, duration: Int = Toast.LENGTH_SHORT){

    Toast.makeText(context,message,duration).show()

}

/**
 * interfaz para mostrar un mensaje de ganador al usuario cuando gana una ronda
 */
@Composable
fun showWin(viewModel: MyViewModel, listaColores: MutableList<Int>){
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        Toast.makeText(context, "Has ganado", Toast.LENGTH_SHORT).show()
        viewModel.onWin(listaColores)
        Toast.makeText(context, "Pulsa " + viewModel.returnContador() + " botones", Toast.LENGTH_SHORT).show()
        viewModel.setRandom()
    }
}

/**
 * interfaz para mostrar un mensaje de perdedor al usuario cuando gana una ronda
 */
@Composable
fun showLose(viewModel: MyViewModel, listaColores: MutableList<Int>, startButton:MutableState<Boolean>){
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        Toast.makeText(context, "Has perdido", Toast.LENGTH_SHORT).show()
        viewModel.onLose(listaColores, startButton)
    }
}

/**
 * Interfaz para mostrar la victoria o derrota dependiendo de si el usuario gana o pierde
 */
@Composable
fun game(viewModel: MyViewModel, listaColores: MutableList<Int>, startButton: MutableState<Boolean>){
    if(viewModel.winOrLose(viewModel.getRandom(),listaColores)){
        showWin(viewModel, listaColores)
    }
    else{
        showLose(viewModel, listaColores, startButton)
    }
}


/**
 * app principal del juego
 */
@Composable
fun myApp(viewModel: MyViewModel) {
    var lista_colores = remember { mutableStateListOf<Int>() }
    val isStartButtonPressed = remember { mutableStateOf(true) }
    var presioneStart = remember { mutableStateOf(false) }



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
            RecordMaximo(viewModel.getMaxRecord())
            initialText(viewModel.getSaludoInicio())
            showAciertos(viewModel.getRecord())
        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(70.dp)
                .padding(top = 190.dp, start = 15.dp)
        ) {

            Row {

                buttonRed(viewModel, lista_colores, Colores.ROJO.valorColor)
                buttonGreen(viewModel, lista_colores, Colores.VERDE.valorColor)
            }

            Row {
                buttonBlue(viewModel, lista_colores, Colores.AZUL.valorColor)
                buttonYellow(viewModel, lista_colores, Colores.AMARILLO.valorColor)
            }

            showRondas(viewModel.getRondas())

            startGame(isStartButtonPressed, presioneStart1 = presioneStart, viewModel = viewModel)


            if(lista_colores.size == viewModel.returnContador()){

                game(viewModel, lista_colores, isStartButtonPressed)

            }



        }
    }

}





