package com.pmdm.cristian.botonescolores.modelview

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.pmdm.cristian.botonescolores.model.DataRecord

class MyViewModel(): ViewModel() {

    //varialbe que almacena el saludo inicial del juego
    private val saludoInicio:String = "¡SIMON DICE!"

    var record = mutableStateOf(0)



    /**
     * Método que devuelve el saludo inicial
     */
    fun getSaludoInicio():String{

        return saludoInicio
    }

    fun saveRecord(auxRecord : Int){
        DataRecord.record = auxRecord
    }

    fun getRecord():Int{
        return DataRecord.record
    }


}