package com.example.checatuhorario


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blue80
import com.example.checatuhorario.ui.theme.blueL40


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController){
    val context = LocalContext.current
    val intentKardex = Intent(Intent.ACTION_VIEW, Uri.parse("https://kardex.uaem.mx/"))
    val intentPay = Intent(Intent.ACTION_VIEW, Uri.parse("https://pagos.uaem.mx/"))
    val intentCredits = Intent(Intent.ACTION_VIEW, Uri.parse("http://seadfcaei.uaem.mx/SAE/"))
    Scaffold(
        topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = blueL40,
                titleContentColor = blue80,
            ),
                title = {
                    Column {
                        Text(
                            """
                Alumno: Hernández Magni Marco Antonio 
                Matrícula: 10044529 
                Carrera: Licenciatura en Informática 7”U
                            """.trimIndent(),
                            style = TextStyle(
                                fontSize = 15.sp
                            )
                        )
                    }
                    
                })
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = { navController.navigate(AppScreens.CalendarScreen.route) },
                modifier = Modifier.padding(20.dp)) {
                Text(text = "Ver mi Horario de Clase")
            }
            Button(onClick = { navController.navigate(AppScreens.RegisterScreen.route) },
                modifier = Modifier.padding(20.dp)) {
                Text(text = "Agendar Conferencia")
            }
            Button(onClick = { navController.navigate(AppScreens.RegisterScreen.route) },
                modifier = Modifier.padding(20.dp)) {
                Text(text = "Ver Contacto del Profesor")
            }
            Button(onClick = { context.startActivity(intentKardex) },
                modifier = Modifier.padding(20.dp)) {
                Text(text = "Kardex UAEM")
            }
            Button(onClick = { context.startActivity(intentPay) },
                modifier = Modifier.padding(20.dp)) {
                Text(text = "Portal de Pagos UAEM")
            }
            Button(onClick = { context.startActivity(intentCredits) },
                modifier = Modifier.padding(20.dp)) {
                Text(text = "Revisión de Créditos")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    val navController = rememberNavController()
    HomeScreen(navController)
}

