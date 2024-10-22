package com.pmdm.cristian.botonescolores.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun initialText(bienvenido:String) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(
            start = 90.dp,
            top = 80.dp
        )
    ) {
        Text(
            text = bienvenido,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

    }

}
