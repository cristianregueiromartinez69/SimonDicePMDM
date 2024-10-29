package com.pmdm.cristian.botonescolores.modelview

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.pmdm.cristian.botonescolores.model.Datos
import kotlin.random.Random

class MyViewModel(): ViewModel() {

    //varialbe que almacena el saludo inicial del juego
    private val saludoInicio:String = "¡SIMON DICE!"

    var record = mutableStateOf(0)

    var rondas = mutableStateOf(0)

    val random = Random

    var numRandom = mutableStateOf(0)



    /**
     * Método que devuelve el saludo inicial
     */
    fun getSaludoInicio():String{

        return saludoInicio
    }


    /**
     * Metodo para incrementar el record una vez se gana y se almacena en el singleton
     */
    fun saveRecord(){
        record.value++
        Datos.aciertos = record.value
        if (Datos.record < Datos.aciertos){
            Datos.record = Datos.aciertos
        }
    }

    /**
     * Metodo que nos devuelve el record actual
     */
    fun getRecord():Int{
        return Datos.aciertos
    }

    fun resetRecord(){
        record.value = 0
        Datos.aciertos = record.value
    }

    fun getMaxRecord():Int{
        return Datos.record
    }



    /**
     * Metodo para incrementar las rondas y guardarlas en el singleton
     */
    fun incrementRondas(){
        rondas.value++
        Datos.rondas = rondas.value
    }

    fun restartRondas(){
        rondas.value = 0
        Datos.rondas = rondas.value
    }

    /**
     * Metodo que devuelve el valor de las rondas
     */
    fun getRondas():Int{
        return Datos.rondas
    }

    fun logicalStartButton(presioneStart1: MutableState<Boolean>):Unit{
        presioneStart1.value = false
        setRandom()
        Log.d("Random", getRandom().toString())
    }

    fun setRandom(){
        for(i in 1..3){
            numRandom.value = random.nextInt(4) + 1
            Datos.listaNumerosRandom.add(numRandom.value)
            Datos.numRandom = numRandom.value
        }
    }

    fun getRandom():List<Int>{
        return Datos.listaNumerosRandom
    }

    fun winOrLose(num:Int,listaColores:MutableList<Int>):Boolean{

        for(i in listaColores){
            if(i == num){
                return true
            }

        }
        return false
    }


    fun showLose(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT){

        Toast.makeText(context,message,duration).show()
    }

    fun showWin(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT){

        Toast.makeText(context,message,duration).show()
    }







}

/**
 * genera una lista de 3 randoms y el usuario le da 3 veces a los botones comparando los indices de la lista con los randoms
 */