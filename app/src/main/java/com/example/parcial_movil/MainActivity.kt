package com.example.parcial_movil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.parcial_movil.ui.theme.Parcial_MovilTheme
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.parcial_movil.ui.theme.Red
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Parcial_MovilTheme {
                var isRegistered by remember {mutableStateOf(false)}
                if (isRegistered){
                    HomeScreen()
                }else{
                    RegisterScreen (
                        onRegisterSuccess = {
                            isRegistered = true
                        }
                    )
                }
            }
        }
    }
}

@Composable
    fun RegisterScreen(onRegisterSuccess: () -> Unit) {

        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var error by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "Registro")

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                if (username.isNotEmpty() && password.isNotEmpty()) {
                    onRegisterSuccess()
                } else {
                    error = "Debe llenar todos los campos"
                }
            }) {
                Text("Ingresar")
            }

            if (error.isNotEmpty()) {
                Text(text = error, color = Red)
            }
        }
    }


// Esta es una función provisional hasta que se haga la ventana de mediciones a la que se ingresa
// apenas se oprime el boton (las de medidas)
@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Bienvenido 🎉")
    }
}