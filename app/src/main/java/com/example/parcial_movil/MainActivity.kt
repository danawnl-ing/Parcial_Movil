package com.example.parcial_movil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.parcial_movil.ui.theme.Parcial_MovilTheme
import androidx.compose.runtime.*
import com.example.parcial_movil.ui.theme.LongitudScreen
import com.example.parcial_movil.ui.theme.MenuScreen
import com.example.parcial_movil.ui.theme.RegisterScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial_MovilTheme {
                var isRegistered by remember {mutableStateOf(false)}
                var currentScreen by remember {mutableStateOf("menu")}
                when {
                    !isRegistered -> {
                        RegisterScreen (onRegisterSuccess = {isRegistered = true })
                    }
                    currentScreen == "longitud" -> {
                        LongitudScreen (onBack = { currentScreen = "Menu"})
                    }else -> {
                        MenuScreen(onNavigateToLength = {currentScreen = "longitud"})
                    }

                }
            }
            }
        }
    }

