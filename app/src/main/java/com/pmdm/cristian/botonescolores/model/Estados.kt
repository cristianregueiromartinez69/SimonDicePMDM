package com.pmdm.cristian.botonescolores.model

enum class Estados(val startActivo: Boolean, val botonesColoresActivos:Boolean){

    INICIO(startActivo = true, botonesColoresActivos = false),
    GENERNANDO(startActivo = false, botonesColoresActivos = false),
    ADVININANDO(startActivo = false, botonesColoresActivos = true)

}