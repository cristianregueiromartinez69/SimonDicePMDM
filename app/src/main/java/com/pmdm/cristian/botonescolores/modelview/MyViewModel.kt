package com.pmdm.cristian.botonescolores.modelview

import androidx.lifecycle.ViewModel

class MyViewModel(): ViewModel() {

    private val saludoInicio:String = "¡SIMON DICE!"

    fun getSaludoInicio():String{

        return saludoInicio
    }
}