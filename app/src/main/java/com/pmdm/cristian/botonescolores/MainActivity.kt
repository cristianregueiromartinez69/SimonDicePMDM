package com.pmdm.cristian.botonescolores

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import com.pmdm.cristian.botonescolores.model.DataRecord
import com.pmdm.cristian.botonescolores.modelview.MyViewModel
import kotlin.random.Random
import com.pmdm.cristian.botonescolores.view.*



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel : MyViewModel = MyViewModel()
        enableEdgeToEdge()
        setContent {
            interfazColores(viewModel)
        }
    }
}

@Composable
fun interfazColores(viewModel: MyViewModel) {
    var lista_colores = remember { mutableStateListOf<Int>() }
    var record by remember { mutableStateOf(0) }
    val isStartButtonPressed = remember { mutableStateOf(true) }
    var numero_ronda by remember { mutableStateOf(0) }
    var presioneStart = remember { mutableStateOf(false) }
    var secuencia by remember { mutableStateOf(mutableListOf<Int>()) }


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
            initialText(viewModel.getSaludoInicio())
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

               botonesFila1(lista_colores)
            }

            Row {
                botonesFila2(lista_colores)
            }

            showRondas(numeroRondas = numero_ronda)

            startGame(isStartButtonPressed, presioneStart1 = presioneStart, secuencia)

            if(lista_colores.size == 5){
                if(winOrLose(secuencia,lista_colores)){
                    showWin(context = LocalContext.current, message = "Has ganado")
                    numero_ronda++
                    record++
                    viewModel.saveRecord(record)
                    lista_colores.clear()
                }else{
                    showLose(context = LocalContext.current, message = "Has perdido")
                    lista_colores.clear()
                }

            }


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
private fun startGame(
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




