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

    //variable record para el record
    var record = mutableStateOf(0)

    //variable rondas para las rondas del juego
    var rondas = mutableStateOf(0)

    //objeto random para hacer el random
    val random = Random

    //mutable para hacer multiples randoms en la partida
    var numRandom = mutableStateOf(0)

    //contador para aumentar el numero de secuencias de la partida
    var contador = mutableStateOf(1)


    /**
     * Método que devuelve el saludo inicial
     */
    fun getSaludoInicio():String{

        return saludoInicio
    }

    /**
     * Metodo para incrementar el contador de secuencia del simon dice
     */
    fun incrementContador(){
        //incrementamos el valor y se lo pasamos a los datos
        contador.value++
        Datos.contador = contador.value
    }

    /**
     * Metodo para reiniciar el contador en caso de que el usuario perdiera la ronda
     */
    fun restartContador(){
        //iniciamos el valor a 1 y lo pasamos a los datos
        contador.value = 1
        Datos.contador = contador.value
    }

    /**
     * Metodo que devuelve el contador para la secuencia de simon dice
     */
    fun returnContador():Int{
        return Datos.contador
    }


    /**
     * Metodo para incrementar el record una vez se gana y se almacena en el singleton
     */
    fun saveRecord(){
        /**
         * 1. aumentamos el record
         * 2. lo pasamos a los datos
         * 3. solo aumentamos el record en caso de el record sea menor que los aciertos
         * 4. asi nos aseguramos de que aunque el usuario pierda, el record se mantiene intacto
         */
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

    /**
     * Metodo para resetear los aciertos del usuarios
     */
    fun resetRecord(){
        //lo iniciamos a 0 y se lo pasamos a los aciertos en datos
        record.value = 0
        Datos.aciertos = record.value
    }

    /**
     * Metodo que me devuelve el record máximo al cual llegó el usuario
     */
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

    /**
     * Metodo que reinicia las rondas a 0, lo llamamos solo si el usuario ha perdido
     */
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

    /**
     * Logica una vez el usuario pulse el boton de start
     */
    fun logicalStartButton(presioneStart1: MutableState<Boolean>):Unit{
        presioneStart1.value = false
        setRandom()
        Log.d("Random", getRandom().toString())
    }

    /**
     * metodo que hace una secuencia de numeros aleatorios
     */
    fun setRandom(){
        //el for va desde 1 hasta lo que nos devuelve le metodo contador
        for(i in 1..returnContador()){
            numRandom.value = random.nextInt(4) + 1
            Datos.listaNumerosRandom.add(numRandom.value)
            Datos.numRandom = numRandom.value
        }
        Log.d("Random", Datos.listaNumerosRandom.toString())
    }

    /**
     * metodo para devolver la lista de numeros randoms
     */
    fun getRandom():MutableList<Int>{
        return Datos.listaNumerosRandom
    }

    /**
     * metodo que limpia la lista de ranoms
     */
    fun clearListaRandoms(){
        Datos.listaNumerosRandom.clear()
    }


    /**
     * metodo que limpia la lista de colores
     */
    fun clearListaColores(lista:MutableList<Int>){
        lista.clear()
    }

    /**
     * logica para saber si el usuario ganó o perdió la partida
     */
    fun winOrLose(lista_Random:MutableList<Int>,listaColores:MutableList<Int>):Boolean{

        for(i in lista_Random.indices){
            if(lista_Random[i] != listaColores[i]){
                return false
            }
        }
        return true
    }


    /**
     * funcion que crea una toast en la que se muestra al usuario que ganó la ronda
     */
    fun showLose(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT){

        Toast.makeText(context,message,duration).show()
    }
    /**
     * funcion que crea una toast en la que se muestra al usuario que perdió la ronda
     */
    fun showWin(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT){

        Toast.makeText(context,message,duration).show()
    }

    /**
     * logica para llamar a la toast que muestra al usuario cuantos botones tiene que pulsar si pierde una ronda
     */
    fun loseGameAndShowAgainToast(showStartButton:MutableState<Boolean>, isStartButtonPressed:MutableState<Boolean>){

        if(showStartButton.value){
            isStartButtonPressed.value = false
        }
    }







}

