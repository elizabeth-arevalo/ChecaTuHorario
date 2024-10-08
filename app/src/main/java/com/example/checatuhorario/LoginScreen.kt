package com.example.checatuhorario

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blueL40
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

// Validación de Datos

private fun CoroutineScope.login(
    email: String,
    password: String,
    wrongPasswordAttempts: Int,
    callback: (Boolean) -> Unit
) {
    launch {
        try {
            val auth = FirebaseAuth.getInstance()
            val result = auth.signInWithEmailAndPassword(email, password).await()
            // Login successful
            callback(true)
        } catch (e: Exception) {
            // Handle login failure
            if (e is FirebaseAuthInvalidUserException || e is FirebaseAuthInvalidCredentialsException) {
                // Handle incorrect email or password
                if (wrongPasswordAttempts >= 5) {
                    // Reset password after 5 wrong attempts
                    resetPassword(email)
                } else {
                    callback(false)
                }
            } else {
                // Handle other exceptions
                callback(false)
            }
        }
    }
}

private suspend fun resetPassword(email: String) {
    try {
        val auth = FirebaseAuth.getInstance()
        auth.sendPasswordResetEmail(email).await()
        // Password reset email sent successfully
    } catch (e: Exception) {
        // Handle password reset failure
    }
}


@Composable
fun LoginScreen(navController: NavHostController){
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var wrongPasswordAttempts by remember { mutableIntStateOf(0) }
    val offset = Offset(2.5f, 6.0f)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(painter = painterResource(id = R.drawable.uniconcep),
            contentDescription = "LETRAS",
            Modifier.size(289.dp,271.dp))
        Text(text = "Bienvenidos!",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 25.sp,
                shadow = Shadow(
                    color = Color.Gray, offset = offset, blurRadius = 1f
                )
            )
        )
        Row {
            Icon(imageVector = Icons.Default.Email,
                contentDescription = "logo email",
                Modifier.padding(15.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = { Text("Correo")},)
        }
        Row(Modifier.padding(6.dp)) {
            Icon(imageVector = Icons.Default.Lock,
                contentDescription = "logo pass",
                Modifier.padding(15.dp))
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = { Text("Contraseña")},
                visualTransformation = PasswordVisualTransformation(),
                )
        }
        Button(
            onClick = {
                // Call your login function
                coroutineScope.login(email, password, wrongPasswordAttempts) { success ->
                    if (success) {
                        // Navigate to home screen on successful login
                        navController.navigate(AppScreens.HomeScreen.route)
                    } else {
                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(context, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                        }
                        if (wrongPasswordAttempts <= 5) {
                            Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                            wrongPasswordAttempts++

                        }
                        if (wrongPasswordAttempts > 5) {
                            Toast.makeText(context, "Has superado los 5 intentos", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            },
            Modifier.padding(25.dp)
        ) {
            Text(text = "Iniciar Sesión",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 25.sp)
            )
        }
        Text(text = "¿Olvidaste tu contraseña?",
            modifier = Modifier.clickable { navController.navigate(AppScreens.ForgotScreen.route) })
        Row {
            Text(text = "¿No tienes cuenta? ")
            Text(text = " Registrate",
                color = blueL40,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { navController.navigate(AppScreens.RegisterScreen.route) })
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(){
    val navController = rememberNavController()
    LoginScreen(navController)
}

