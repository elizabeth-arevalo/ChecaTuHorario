package com.example.checatuhorario

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blue40
import com.example.checatuhorario.ui.theme.blueL40


@Composable
fun WelcomeScreen(navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(painter = painterResource(id = R.drawable.checahor),
            contentDescription = "LETRAS",
            Modifier.size(200.dp,158.dp))
        Image(
            contentDescription = "Concepto Uni",
            painter = painterResource(id = R.drawable.uniconcep))

        Button(onClick = { navController.navigate(AppScreens.LoginScreen.route) },
            Modifier.padding(15.dp)) {
            Text("Ingresar")

        }

        Text(text = "¿Olvidaste tu contraseña?",
            modifier = Modifier.clickable { navController.navigate(AppScreens.ForgotScreen.route) })

        Row {
            Text(text = "¿No tienes cuenta? ")
            Text(text = " Registrate",
                color = blueL40,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { navController.navigate(AppScreens.RegisterScreen.route) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WelcomeScreenPreview(){
    val navController = rememberNavController()
    WelcomeScreen(navController)
}