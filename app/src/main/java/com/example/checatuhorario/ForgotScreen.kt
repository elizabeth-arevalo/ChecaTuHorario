package com.example.checatuhorario

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.navigation.NavHostController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blueL40


@Composable
fun ForgotScreen(navController: NavHostController){
    var text by remember { mutableStateOf("") }
    val offset = Offset(2.5f, 6.0f)
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(painter = painterResource(id = R.drawable.uniconcep),
            contentDescription = "LETRAS",
            Modifier.size(289.dp,271.dp),
            Alignment.TopStart)
        Text(text = "Ingresa el correo vinculado a tu cuenta",
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 20.sp,
                shadow = Shadow(
                    color = Color.Gray, offset = offset, blurRadius = 1f
                )
            )
        )
        Row(Modifier.padding(15.dp)) {
            Icon(painter = painterResource(id = R.drawable.contact_mail_24),
                contentDescription = "logo email",
                Modifier.padding(12.dp))
            OutlinedTextField(
                value = text,
                onValueChange = {text = it},
                label = { Text("Email")})
        }
        Button(
            onClick = { navController.navigate(AppScreens.Forgot2Screen.route) },
            Modifier.padding(25.dp)) {
            Text(text = "Recuperar",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 25.sp)
            )
        }
        Row {
            Text(text = "¿Tienes una cuenta? ")
            Text(text = "Llévame al Login",
                color = blueL40,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { navController.navigate(AppScreens.LoginScreen.route) })
        }
        Row {
            Text(text = "¿No tienes cuenta? ")
            Text(text = "Registrate",
                color = blueL40,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { navController.navigate(AppScreens.RegisterScreen.route) })
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forgot2Screen(navController: NavHostController){
    var text by remember { mutableStateOf("") }
    val offset = Offset(2.5f, 6.0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(painter = painterResource(id = R.drawable.uniconcep),
            contentDescription = "LETRAS",
            Modifier.size(289.dp,271.dp),
            Alignment.TopStart)
        Text(text = "A tu correo llegó un código de verificación ingrésalo en el recuadro de abajo. Nota: Recuerda revisar en el SPAM",
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 20.sp,
                shadow = Shadow(
                    color = Color.Gray, offset = offset, blurRadius = 1f
                )
            )
        )

        OutlinedTextField(
            value = text,
            onValueChange = {text = it},
            label = { Text("Code")},
            modifier = Modifier.padding(15.dp),
        )

        Text(text = "Reenviar Código" , style = TextStyle(fontSize = 15.sp),
            modifier = Modifier.clickable { navController.navigate(AppScreens.RegisterScreen.route) }
                .padding(horizontal = 15.dp, vertical = 20.dp))

        Button(
            onClick = { navController.navigate(AppScreens.Forgot3Screen.route) },
            Modifier.padding(25.dp)) {
            Text(text = "Verificar",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 25.sp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forgot3Screen(navController: NavHostController){
    var password by rememberSaveable { mutableStateOf("") }
    var passwordC by rememberSaveable { mutableStateOf("") }
    val offset = Offset(2.5f, 6.0f)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Image(painter = painterResource(id = R.drawable.uniconcep),
            contentDescription = "LETRAS",
            Modifier.size(289.dp,271.dp),
            Alignment.TopStart)
        Text(text = "Realiza el cambio de tu contraseña",
            fontWeight = FontWeight.Bold,
            style = TextStyle(
                fontSize = 20.sp,
                shadow = Shadow(
                    color = Color.Gray, offset = offset, blurRadius = 1f
                )
            )
        )

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = { Text("Nueva Contraseña")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(15.dp),
        )

        OutlinedTextField(
            value = passwordC,
            onValueChange = {passwordC = it},
            label = { Text("Confirmar Contraseña")},
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(10.dp),
        )


        Button(
            onClick = { navController.navigate(AppScreens.LoginScreen.route) },
            Modifier.padding(25.dp)) {
            Text(text = "Confirmar",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 25.sp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotScreenPreview(){
    val navController = rememberNavController()
    ForgotScreen(navController)
}

