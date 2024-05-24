package com.example.checatuhorario

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.checatuhorario.navigation.AppScreens
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController){

    LaunchedEffect(key1 = true) {
        delay(2000)//dura 2 segundos
        navController.popBackStack() // Se limpia el stack de navegacion
        navController.navigate(AppScreens.WelcomeScreen.route) //se direcciona a la pantalla Main
    }
    Splash()
}

@Composable
fun Splash(){
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(painter = painterResource(id = R.drawable.fcaei),
            contentDescription = "logo",
            Modifier.size(150.dp, 150.dp))
    }
}
