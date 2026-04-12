package com.example.parcial_movil.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MagnitudScreen(onBack: () -> Unit) {

    var valor by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    fun convertir() {
        val num = valor.toDoubleOrNull()
        if (num == null) {
            resultado = "Valor inválido"
            return
        }

        // Celsius → Fahrenheit
        val res = (num * 9/5) + 32
        resultado = "$res °F"
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Magnitud Física (Temperatura)")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = { valor = it },
            label = { Text("Celsius") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { convertir() }) {
            Text("Convertir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Resultado: $resultado")

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { onBack() }) {
            Text("Volver")
        }
    }
}