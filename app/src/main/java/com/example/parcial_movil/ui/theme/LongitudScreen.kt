package com.example.parcial_movil.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LongitudScreen(onBack: () -> Unit) {

    val unidades = listOf(
        "Metros",
        "Kilómetros",
        "Pies",
        "Millas"
    )

    val conversiones = unidades.flatMap { origen ->
        unidades.filter { destino -> destino != origen }
            .map { destino -> "$origen → $destino" }
    }

    var seleccion by remember { mutableStateOf(conversiones[0]) }
    var expanded by remember { mutableStateOf(false) }

    var entrada by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    //  Conversión base a metros
    fun aMetros(valor: Double, unidad: String): Double {
        return when (unidad) {
            "Metros" -> valor
            "Kilómetros" -> valor * 1000
            "Pies" -> valor * 0.3048
            "Millas" -> valor * 1609.34
            else -> valor
        }
    }

    // De metros a cualquier unidad
    fun deMetros(valor: Double, unidad: String): Double {
        return when (unidad) {
            "Metros" -> valor
            "Kilómetros" -> valor / 1000
            "Pies" -> valor / 0.3048
            "Millas" -> valor / 1609.34
            else -> valor
        }
    }

    // Conversión final
    fun convertir() {
        val valor = entrada.toDoubleOrNull()

        if (valor == null) {
            resultado = "Ingrese un número válido"
            return
        }

        val partes = seleccion.split(" → ")
        val origen = partes[0]
        val destino = partes[1]

        val enMetros = aMetros(valor, origen)
        val convertido = deMetros(enMetros, destino)

        resultado = "%.4f %s".format(convertido, destino)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Conversión de Longitud",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

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

        // Campo de entrada
        OutlinedTextField(
            value = entrada,
            onValueChange = { nuevoValor ->
                val regex = Regex("^-?\\d*\\.?\\d*$")
                if (nuevoValor.isEmpty() || nuevoValor.matches(regex)) {
                    entrada = nuevoValor
                }
            },
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
