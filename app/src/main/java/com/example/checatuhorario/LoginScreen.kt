package com.example.checatuhorario

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
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

// Agregar la validación de Datos


@Composable
fun LoginScreen(navController: NavHostController){
    var text by remember { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
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
                value = text,
                onValueChange = {text = it},
                label = { Text("Correo")})
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
            onClick = { navController.navigate(AppScreens.HomeScreen.route) },
            Modifier.padding(25.dp)) {
            Text(text = "Iniciar Sesión",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 25.sp)
            )
        }
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