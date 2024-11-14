package com.pmdm.cristian.botonescolores.modelview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pmdm.cristian.botonescolores.model.Datos
import com.pmdm.cristian.botonescolores.model.Estados
import kotlin.random.Random
import com.pmdm.cristian.botonescolores.model.Datos.record
import com.pmdm.cristian.botonescolores.model.Datos.rondas
import com.pmdm.cristian.botonescolores.model.Datos.aciertos
import com.pmdm.cristian.botonescolores.model.Datos.numRandom

class MyViewModel(): ViewModel() {

    var random = Random

    val estadoLiveData : MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    private val _recordLiveData = MutableLiveData<Int>()
    val recordLiveData: LiveData<Int> get() = _recordLiveData

    private val _rondasLiveData = MutableLiveData<Int>()
    val rondasLiveData: LiveData<Int> get() = _rondasLiveData

    private val _aciertosLiveData = MutableLiveData<Int>()
    val aciertosLiveData: LiveData<Int> get() = _aciertosLiveData

    init {
        _recordLiveData.value = record
        _rondasLiveData.value = rondas
        _aciertosLiveData.value = aciertos
    }

    fun incrementAciertos() {
        aciertos += 1
        _aciertosLiveData.value = aciertos
    }

    fun incrementRondas() {
        rondas += 1
        _rondasLiveData.value = rondas
    }

    fun incrementRecord() {
        if (getRecord() < getAciertos()) {
            record = aciertos
            _recordLiveData.value = record
        }
    }

    fun getAciertos():Int{
        return aciertos
    }

    fun getRondas():Int{
        return rondas
    }

    fun getRecord():Int{
        return record
    }

    fun incrementValues(){
        incrementAciertos()
        incrementRondas()
        incrementRecord()
    }

    fun restartValues(){
        aciertos = 0
        rondas = 0
        _rondasLiveData.value = rondas
        _aciertosLiveData.value = aciertos
    }



    /**
     * metodo que hace una secuencia de numeros aleatorios
     */
    fun setRandom(){
            numRandom = random.nextInt(4) + 1
            Datos.listaNumerosRandom.add(numRandom)
            estadoLiveData.value = Estados.ADIVINANDO

        Log.d("Random", Datos.listaNumerosRandom.toString())
    }

    fun addColor(numero:Int, listaColoresR: MutableList<Int>, lista_Random:MutableList<Int>){

        listaColoresR.add(numero)
        Datos.listaColores = listaColoresR
        winOrLose(lista_Random, listaColoresR)

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

    fun winOrLose(lista_Random: MutableList<Int>, listaColores: MutableList<Int>){
        if(listaColores.size <= lista_Random.size){
            auxWinOrLose(lista_Random, listaColores)
        }
    }

    private fun auxWinOrLose(lista_Random:MutableList<Int>,listaColores:MutableList<Int>){
        if(lista_Random == listaColores){
            onWin(listaColores)
            Log.d("random", "ganaste")
            Log.d("randomRe", getRecord().toString())
            Log.d("randomAc", getAciertos().toString())
            Log.d("randomRor", getRondas().toString())
        }
        else if (lista_Random.subList(0, listaColores.size) == listaColores){
            Log.d("TAG", "CORRECTO")
        }
        else{
            Log.d("random", "perdiste")
            onLose(listaColores)
        }
    }



    /**
     * funcion de logica de ganador de rondas
     * 1. guardamos el record
     * 2. incrementamos las rondas
     * 3. limpiamos la lista de colores
     * 4. limpiamos la lista de randoms de maquina
     * 5. incrementamos el contador para las rondas
     */
    fun onWin(listaColores: MutableList<Int>) {
        estadoLiveData.value = Estados.INICIO
        incrementValues()
        clearListaColores(listaColores)
    }

    /**
     * funcion de logica de perdedor de rondas
     * 1. reseteamos los aciertos
     * 2. reseteamos las rondas
     * 3. limpiamos la lista de colores
     * 4. limpiamos la lista de randoms de maquina
     * 5. bajamos el contador a 1 de nuevo
     */
    fun onLose(listaColores: MutableList<Int>){
        estadoLiveData.value = Estados.INICIO
        restartValues()
        clearListaColores(listaColores)
        clearListaRandoms()
    }








}

