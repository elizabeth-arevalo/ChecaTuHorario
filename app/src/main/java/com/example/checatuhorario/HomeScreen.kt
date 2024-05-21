package com.example.checatuhorario


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blue80


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
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = blue80,
            ),
                modifier = Modifier.padding(vertical = 1.dp),
                title = {
                    Row {
                        Text(
                            """
                Alumno: Hernández Magni Marco Antonio 
                Matrícula: 10044529 
                Carrera: Licenciatura en Informática 7”U
                            """.trimIndent(),
                            style = TextStyle(
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start
                            ),
                            modifier = Modifier.padding(15.dp),
                        )
                        Image(painter = painterResource(id = R.drawable.fcaei),
                            contentDescription = "logo-uni",
                            modifier = Modifier.size(75.dp,75.dp),
                            Alignment.TopEnd
                        )
                    }
                    
                })
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Spacer(modifier = Modifier.padding(10.dp))
                Button(
                    onClick = { navController.navigate(AppScreens.CalendarScreen.route) },
                    modifier = Modifier
                        .padding(15.dp)
                        .size(width = 1000.dp, height = 80.dp)
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar_month),
                            contentDescription = "Calendar-logo",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(50.dp, 50.dp)
                        )
                        Text(
                            text = "Ver mi Horario de Clase",
                            style = TextStyle(
                                fontSize = 19.2.sp,
                                textAlign = TextAlign.Start
                            ),
                            modifier = Modifier.padding(17.dp)
                        )
                    }
                }
                Button(
                    onClick = { navController.navigate(AppScreens.RegisterScreen.route) },
                    modifier = Modifier
                        .padding(15.dp)
                        .size(width = 1000.dp, height = 80.dp)
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.edit_calendar),
                            contentDescription = "Calendar-logo",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(50.dp, 50.dp)
                        )
                        Text(
                            text = "Agendar una Conferencia",
                            style = TextStyle(
                                fontSize = 17.81.sp
                            ),
                            modifier = Modifier.padding(17.dp)
                        )
                    }
                }
                Button(
                    onClick = { navController.navigate(AppScreens.RegisterScreen.route) },
                    modifier = Modifier
                        .padding(15.dp)
                        .size(width = 1000.dp, height = 80.dp)
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.contacts),
                            contentDescription = "contacts-logo",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(50.dp, 50.dp)
                        )
                        Text(
                            text = "Contacto de Profesores",
                            style = TextStyle(
                                fontSize = 19.2.sp
                            ),
                            modifier = Modifier.padding(17.dp)
                        )
                    }
                }
                Button(
                    onClick = { context.startActivity(intentKardex) },
                    modifier = Modifier
                        .padding(15.dp)
                        .size(width = 1000.dp, height = 80.dp)
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "link-logo",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(50.dp, 50.dp)
                        )
                        Text(
                            text = "Portal  Kardex UAEM",
                            style = TextStyle(
                                fontSize = 19.2.sp
                            ),
                            modifier = Modifier.padding(17.dp)
                        )
                    }
                }
                Button(
                    onClick = { context.startActivity(intentPay) },
                    modifier = Modifier
                        .padding(20.dp)
                        .size(width = 1000.dp, height = 80.dp)
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "link-logo",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(50.dp, 50.dp)
                        )
                        Text(
                            text = "Portal Pagos UAEM",
                            style = TextStyle(
                                fontSize = 19.2.sp
                            ),
                            modifier = Modifier.padding(17.dp)
                        )
                    }
                }
                Button(
                    onClick = { context.startActivity(intentCredits) },
                    modifier = Modifier
                        .padding(20.dp)
                        .size(width = 1000.dp, height = 80.dp)
                ) {
                    Row {
                        Icon(
                            painter = painterResource(id = R.drawable.link),
                            contentDescription = "link-logo",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(50.dp, 50.dp)
                        )
                        Text(
                            text = "Portal  de  Créditos",
                            style = TextStyle(
                                fontSize = 19.2.sp
                            ),
                            modifier = Modifier.padding(17.dp)
                        )
                    }
                }

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

