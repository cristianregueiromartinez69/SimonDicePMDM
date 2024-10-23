package com.pmdm.cristian.botonescolores.modelview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pmdm.cristian.botonescolores.model.DataRecord
import com.pmdm.cristian.botonescolores.model.Rondas

class MyViewModel(): ViewModel() {

    //varialbe que almacena el saludo inicial del juego
    private val saludoInicio:String = "¡SIMON DICE!"

    var record = mutableStateOf(0)

    var rondas = mutableStateOf(0)



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
        DataRecord.record = record.value
    }

    /**
     * Metodo que nos devuelve el record actual
     */
    fun getRecord():Int{
        return DataRecord.record
    }

    /**
     * Metodo para incrementar las rondas y guardarlas en el singleton
     */
    fun incrementRondas(){
        rondas.value++
        Rondas.rondas = rondas.value
    }

    fun restartRondas(){
        rondas.value = 0
        Rondas.rondas = rondas.value
    }

    /**
     * Metodo que devuelve el valor de las rondas
     */
    fun getRondas():Int{
        return Rondas.rondas
    }


}