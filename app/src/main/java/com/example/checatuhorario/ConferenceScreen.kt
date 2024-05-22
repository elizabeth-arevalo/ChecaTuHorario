package com.example.checatuhorario

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blue80
import com.example.checatuhorario.ui.theme.blueL40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConferenceScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = blueL40,
                titleContentColor = blue80,
            ),
                title = {
                    Row(modifier = Modifier.fillMaxWidth().fillMaxWidth()) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Regresar a Home",
                            Modifier.clickable {
                                navController.popBackStack() // Se limpia el stack de navegacion
                                navController.navigate(AppScreens.HomeScreen.route)

                            } )
                        Text(
                            text = "Agendar Conferencia",
                            style = TextStyle(
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConferenceScreenPreview(){
    val navController = rememberNavController()
    ConferenceScreen(navController)
}