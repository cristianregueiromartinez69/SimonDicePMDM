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

        Text(text = "Aciertos: $record" ,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold)

        Log.d("Record", record.toString())

    }

}

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
}

@Composable
fun continueGameWhileWin(viewModel: MyViewModel){
    showToast(context = LocalContext.current, message = "Pulsa " + viewModel.returnContador() + " botornes")
    viewModel.setRandom()
}

@Composable
fun showToast(context: Context = LocalContext.current, message: String, duration: Int = Toast.LENGTH_SHORT){

    Toast.makeText(context,message,duration).show()

}

@Composable
fun showWin(viewModel: MyViewModel, listaColores: MutableList<Int>){
    viewModel.showWin(context = LocalContext.current, message = "Has ganado")
    viewModel.saveRecord()
    viewModel.incrementRondas()
    viewModel.clearListaColores(listaColores)
    viewModel.clearListaRandoms()
    viewModel.incrementContador()
    continueGameWhileWin(viewModel)
}

@Composable
fun showLose(viewModel: MyViewModel, listaColores: MutableList<Int>){
    viewModel.showLose(context = LocalContext.current, message = "Has perdido")
    viewModel.restartRondas()
    viewModel.resetRecord()
    viewModel.clearListaColores(listaColores)
    viewModel.clearListaRandoms()
    viewModel.restartContador()
}


@Composable
fun game(viewModel: MyViewModel, listaColores: MutableList<Int>){
    if(viewModel.winOrLose(viewModel.getRandom(),listaColores)){
        showWin(viewModel, listaColores)
    }
    else{
        showLose(viewModel, listaColores)
    }
}







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
            showRecord(viewModel.getRecord())
        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(70.dp)
                .padding(top = 190.dp, start = 15.dp)
        ) {

            Row {

                botonesFila1(lista_colores)
            }

            Row {
                botonesFila2(lista_colores)
            }

            showRondas(viewModel.getRondas())

            startGame(isStartButtonPressed, presioneStart1 = presioneStart, viewModel = viewModel)


            if(lista_colores.size == viewModel.returnContador()){

                game(viewModel, lista_colores)

            }



        }
    }

}






