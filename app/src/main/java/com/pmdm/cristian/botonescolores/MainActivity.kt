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

            startGame(isStartButtonPressed, presioneStart1 = presioneStart)

            if(lista_colores.size == 5){
                if(winOrLose(secuencia,lista_colores)){
                    showWin(context = LocalContext.current, message = "Has ganado")
                    viewModel.saveRecord()
                    viewModel.incrementRondas()
                    lista_colores.clear()
                }else{
                    showLose(context = LocalContext.current, message = "Has perdido")
                    viewModel.restartRondas()
                    lista_colores.clear()
                }

            }


        }
    }

}








fun winOrLose(secuencia:MutableList<Int>,lista_colores:MutableList<Int>):Boolean{

    return secuencia == lista_colores
}



fun showLose(context: Context, message: String,duration: Int = Toast.LENGTH_LONG){

    Toast.makeText(context,message,duration).show()
}

fun showWin(context: Context, message: String,duration: Int = Toast.LENGTH_LONG){

    Toast.makeText(context,message,duration).show()
}




