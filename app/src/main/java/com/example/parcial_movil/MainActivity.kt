package com.example.parcial_movil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import com.example.parcial_movil.ui.theme.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            Parcial_MovilTheme {

                var isRegistered by remember { mutableStateOf(false) }
                var currentScreen by remember { mutableStateOf("menu") }

                when {
                    !isRegistered -> {
                        RegisterScreen(
                            onRegisterSuccess = { isRegistered = true }
                        )
                    }
                    currentScreen == "longitud" -> {
                        LongitudScreen(
                            onBack = { currentScreen = "menu" }
                        )
                    }
                    currentScreen == "magnitud" -> {
                        MagnitudScreen(
                            onBack = { currentScreen = "menu" }
                        )
                    }
                    currentScreen == "masa" -> {
                        MasaScreen(
                            onBack = { currentScreen = "menu" }
                        )
                    }
                    else -> {
                        MenuScreen(
                            onNavigateToLength = { currentScreen = "longitud" },
                            onNavigateToMasa = { currentScreen = "masa" },
                            onNavigateToMagnitud = { currentScreen = "magnitud" }

                        )
                    }
                }
            }
        }
    }
}