package com.pmdm.cristian.botonescolores.model

import androidx.compose.ui.graphics.Color

/**
 * Clase enum donde tenemos los colores asociados a un valor en concreto
 */
enum class Colores(val valorColor:Int){
    ROJO(1),
    VERDE(2),
    AZUL(3),
    AMARILLO(4)

}

enum class ColoresIluminados(var colorNomal : Color, var colorIluminado:Color = Color.Transparent){
    ROJO_PARPADEO(colorNomal = Color.Red),
    VERDE_PARPADEO(colorNomal = Color.Green),
    AZUL_PARPADEO(colorNomal = Color.Blue),
    AMARILLO_PARPADEO(colorNomal = Color.Yellow)
}