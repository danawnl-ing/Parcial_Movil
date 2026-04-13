package com.example.parcial_movil.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MagnitudScreen(onBack: () -> Unit) {

    val conversiones = listOf(
        "Celsius → Fahrenheit",
        "Fahrenheit → Celsius",
        "Celsius → Kelvin",
        "Kelvin → Celsius",
        "Fahrenheit → Kelvin",
        "Kelvin → Fahrenheit"
    )

    var seleccion by remember { mutableStateOf(conversiones[0]) }
    var expanded by remember { mutableStateOf(false) }
    var entrada by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    fun convertir() {
        val valor = entrada.toDoubleOrNull()

        if (valor == null) {
            resultado = "Ingrese un número válido"
            return
        }

        resultado = when (seleccion) {

            "Celsius → Fahrenheit" -> "%.2f °F".format((valor * 9 / 5) + 32)

            "Fahrenheit → Celsius" -> "%.2f °C".format((valor - 32) * 5 / 9)

            "Celsius → Kelvin" -> "%.2f K".format(valor + 273.15)

            "Kelvin → Celsius" -> "%.2f °C".format(valor - 273.15)

            "Fahrenheit → Kelvin" -> "%.2f K".format((valor - 32) * 5 / 9 + 273.15)

            "Kelvin → Fahrenheit" -> "%.2f °F".format((valor - 273.15) * 9 / 5 + 32)
            else -> "Conversión no disponible"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Conversión de Temperatura",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Dropdown
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {

            OutlinedTextField(
                value = seleccion,
                onValueChange = {},
                readOnly = true,
                label = { Text("Tipo de conversión") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                conversiones.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            seleccion = opcion
                            expanded = false
                            resultado = ""
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = entrada,
            onValueChange = { nuevoValor ->
                val regex = Regex("^-?\\d*\\.?\\d*$")
                if (nuevoValor.isEmpty() || nuevoValor.matches(regex)) {
                    entrada = nuevoValor
                }},
            label = { Text("Valor a convertir") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { convertir() }) {
            Text("Convertir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        if (resultado.isNotEmpty()) {
            Text(
                text = "Resultado: $resultado",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        OutlinedButton(onClick = { onBack() }) {
            Text("← Volver al menú")
        }
    }
}