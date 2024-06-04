package com.example.checatuhorario


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
    Scaffold(
        topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = blue80,
            ),
                title = {
                    Row {
                        Text(
                            """
                Alumno: Hernández Magni Marco Antonio 
                Matrícula: 10044529 
                Carrera: Licenciatura en Informática 8”U
                            """.trimIndent(),
                            style = TextStyle(
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start
                            ),
                            modifier = Modifier.padding(10.dp),
                        )
                        Image(painter = painterResource(id = R.drawable.fcaei),
                            contentDescription = "logo-uni",
                            modifier = Modifier.size(65.dp),
                            Alignment.TopEnd
                        )
                    }

                })
        },
    ) { innerPadding ->
        // En tu composable, usa un bucle para crear los botones dinámicamente
        Column(
            Modifier
                .padding(innerPadding)
                .fillMaxWidth()) {
            ButtonOptions(navController)

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    val navController = rememberNavController()
    HomeScreen(navController)
}



data class ButtonOption(val iconId: Int, val text: String, val onClick: () -> Unit)

val intentKardex = Intent(Intent.ACTION_VIEW, Uri.parse("https://kardex.uaem.mx/"))
val intentPay = Intent(Intent.ACTION_VIEW, Uri.parse("https://pagos.uaem.mx/"))
val intentCredits = Intent(Intent.ACTION_VIEW, Uri.parse("http://seadfcaei.uaem.mx/"))


@Composable
fun ButtonOptions(navController: NavHostController) {

    val context = LocalContext.current
    // Lista de opciones de botones
    val buttonOptions = listOf(
        ButtonOption(
            iconId = R.drawable.calendar_month,
            text = "Ver mi Horario de Clase",
            onClick = { navController.navigate(AppScreens.CalendarScreen.route) }
        ),
        ButtonOption(
            iconId = R.drawable.edit_calendar,
            text = "Agendar una Conferencia",
            onClick = { navController.navigate(AppScreens.ConferenceScreen.route) }
        ),
        ButtonOption(
            iconId = R.drawable.contacts,
            text = "Contacto de Profesores",
            onClick = { navController.navigate(AppScreens.ContactsScreen.route) }
        ),
        ButtonOption(
            iconId = R.drawable.link,
            text = "Portal Kardex UAEM",
            onClick = { context.startActivity(intentKardex) }
        ),
        ButtonOption(
            iconId = R.drawable.link,
            text = "Portal Pagos UAEM",
            onClick = { context.startActivity(intentPay) }
        ),
        ButtonOption(
            iconId = R.drawable.link,
            text = "Portal de Créditos",
            onClick = { context.startActivity(intentCredits) }
        )
    )
    Column {
        buttonOptions.forEach { option ->
            Button(
                onClick = option.onClick,
                modifier = Modifier
                    .padding(15.dp)
                    .size(width = 1000.dp, height = 80.dp)
            ) {
                Row {
                    Icon(
                        painter = painterResource(id = option.iconId),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(15.dp)
                            .size(40.dp)
                    )
                    Text(
                        text = option.text,
                        style = TextStyle(fontSize = 15.sp),
                        modifier = Modifier.padding(17.dp)
                    )
                }
            }
        }
    }
}



