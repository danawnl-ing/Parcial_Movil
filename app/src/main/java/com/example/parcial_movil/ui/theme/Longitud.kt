package com.example.parcial_movil.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LongitudScreen(onBack: () -> Unit) {

    // Tipos de conversión disponibles
    val conversiones = listOf("CM → INCH", "INCH → CM", "KM → Milla", "Milla → KM", "M → FT", "FT → M")

    var seleccion by remember { mutableStateOf(conversiones[0]) }
    var expanded by remember { mutableStateOf(false) }
    var entrada by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }

    // Función que realiza la conversión según la opción seleccionada
    fun convertir() {
        val valor = entrada.toDoubleOrNull()
        if (valor == null) {
            resultado = "Ingrese un número válido"
            return
        }
        resultado = when (seleccion) {
            "CM → INCH" -> "%.4f INCH".format(valor / 2.54)
            "INCH → CM" -> "%.4f CM".format(valor * 2.54)
            "KM → Milla" -> "%.4f Millas".format(valor * 0.621371)
            "Milla → KM" -> "%.4f KM".format(valor / 0.621371)
            "M → FT"     -> "%.4f FT".format(valor * 3.28084)
            "FT → M"     -> "%.4f M".format(valor / 3.28084)
            else         -> "Conversión no disponible"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Conversión de Longitud", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(24.dp))

        // Dropdown para elegir el tipo de conversión
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
                            resultado = "" // Limpia resultado al cambiar
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de entrada del valor
        OutlinedTextField(
            value = entrada,
            onValueChange = { entrada = it },
            label = { Text("Valor a convertir") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { convertir() }) {
            Text("Convertir")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Muestra el resultado
        if (resultado.isNotEmpty()) {
            Text(
                text = "Resultado: $resultado",
                style = MaterialTheme.typography.bodyLarge
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botón para regresar al menú
        OutlinedButton(onClick = { onBack() }) {
            Text("← Volver al menú")
        }
    }
}