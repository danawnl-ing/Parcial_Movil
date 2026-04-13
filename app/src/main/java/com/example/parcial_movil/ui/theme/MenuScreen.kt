package com.example.parcial_movil.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

//Funcion para colocar cada boton para las ventanas
@Composable
fun MenuScreen(
    onNavigateToLength: () -> Unit,
    onNavigateToMasa: () -> Unit,
    onNavigateToMagnitud: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Button(onClick = { onNavigateToLength() }) {
            Text("Longitud")
        }

        Button(onClick = { onNavigateToMasa() }) {
            Text("Masa")
        }

        Button(onClick = { onNavigateToMagnitud() }) {
            Text("Magnitud fisica")
        }
    }
}