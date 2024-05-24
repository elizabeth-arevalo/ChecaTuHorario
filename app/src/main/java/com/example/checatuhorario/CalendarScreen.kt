package com.example.checatuhorario


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blue80
import com.example.checatuhorario.utils.MateriasList
import com.example.checatuhorario.utils.dataClasses.Materia
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavHostController){
    Scaffold(
        topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = blue80,
            ),
                title = {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Regresar",
                            Modifier.clickable {
                                navController.popBackStack() // Se limpia el stack de navegacion
                                navController.navigate(AppScreens.HomeScreen.route)

                            } )
                        Text(
                            text = "Calendario",
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
            CalendarApp()
            ListCards()
        }
    }

}

@Composable
fun CalendarApp(modifier: Modifier = Modifier) {
    val dataSource = CalendarDataSource()
    // we use `mutableStateOf` and `remember` inside composable function to schedules recomposition
    var calendarUiModel by remember { mutableStateOf(dataSource.getData(lastSelectedDate = dataSource.today)) }

    Column(modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Header(
            data = calendarUiModel,
            onPrevClickListener = { startDate ->
                // refresh the CalendarUiModel with new data
                // by get data with new Start Date (which is the startDate-1 from the visibleDates)
                val finalStartDate = startDate.minusDays(1)
                calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
            },
            onNextClickListener = { endDate ->
                // refresh the CalendarUiModel with new data
                // by get data with new Start Date (which is the endDate+2 from the visibleDates)
                val finalStartDate = endDate.plusDays(2)
                calendarUiModel = dataSource.getData(startDate = finalStartDate, lastSelectedDate = calendarUiModel.selectedDate.date)
            }
        )
        Content(data = calendarUiModel, onDateClickListener = { date ->
            // refresh the CalendarUiModel with new data
            // by changing only the `selectedDate` with the date selected by User
            calendarUiModel = calendarUiModel.copy(
                selectedDate = date,
                visibleDates = calendarUiModel.visibleDates.map {
                    it.copy(
                        isSelected = it.date.isEqual(date.date)
                    )
                }
            )
        })
        Spacer(modifier = Modifier.height(20.dp))
        ListCards()
    }
}

@Composable
fun Header(
    data: CalendarUiModel,
    // calbacks to click previous & back button should be registered outside
    onPrevClickListener: (LocalDate) -> Unit,
    onNextClickListener: (LocalDate) -> Unit,
) {
    LazyRow(Modifier.padding(10.dp)) {
        item {
            Text(
                // show "Today" if user selects today's date
                // else, show the full format of the date
                text = if (data.selectedDate.isToday) {
                    "Today: ${data.selectedDate.date.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL))}"
                } else {
                    data.selectedDate.date.format(
                        DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                    )
                },
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 30.dp)
            )
            IconButton(onClick = {
            // invoke previous callback when its button clicked
            onPrevClickListener(data.startDate.date)
            },
                modifier = Modifier.padding(8.dp)) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Arrow-Back")
            }
            IconButton(onClick = {
                // invoke next callback when this button is clicked
                onNextClickListener(data.endDate.date)
            },
                modifier = Modifier.padding(8.dp)) {
                Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Arrow")
            }
    }

    }
}

@Composable
fun Content(
    data: CalendarUiModel,
    // callback should be registered from outside
    onDateClickListener: (CalendarUiModel.Date) -> Unit,
) {
    LazyRow(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 10.dp)) {
        items(items = data.visibleDates) { date ->
            ContentItem(
                date = date,
                onDateClickListener
            )
        }
    }
}



@Composable
fun ContentItem(
    date: CalendarUiModel.Date,
    onClickListener: (CalendarUiModel.Date) -> Unit, // still, callback should be registered from outside
) {
    Card(
        modifier = Modifier
            .padding(vertical = 4.dp, horizontal = 4.dp)
            .clickable { // making the element clickable, by adding 'clickable' modifier
                onClickListener(date)
            },
        colors = CardDefaults.cardColors(
            // background colors of the selected date
            // and the non-selected date are different
            containerColor = if (date.isSelected) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondary
            }
        ),
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .height(48.dp)
                .padding(4.dp)
        ) {
            Text(
                text = date.day, // day "Mon", "Tue"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = date.date.dayOfMonth.toString(), // date "15", "16"
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarScreenPreview(){
    val navController = rememberNavController()
    CalendarScreen(navController)
}

// @Events
@SuppressLint("SuspiciousIndentation")
@Composable
fun ListCards(){
    val lab1 = "Laboratorio 1"
    val edificio = "Edificio 2"
    val lab2 = "Laboratorio 2"
    val lab3 = "Laboratorio 3"
    val lab4 = "Laboratorio 4"
    val labIn4 = "Laboratorio Industria 4.0"
    val salon= "Salon 40"



    val materias = listOf(
        Materia("Optativa - Ciencia de Datos", "14:25","15:15", salon, edificio,"Dra. Yessica Yazmin Calderón Segura"),
        Materia("Arquitectura y desarrollo del cómputo móvil", "15:25","16:15", lab1, edificio,"Ing. Roberto Pablo López Romero"),
        Materia("Realidad Aumentada", "16:35","17:25", labIn4, edificio,"Mtra. Beatriz Serrano Rodríguez"),
        Materia("Sistemas Inteligentes", "17:30","18:15", lab3, edificio,"Ing. Ariana María García Reyes"),
        Materia("Internet de las Cosas", "18:20","19:15", labIn4, edificio,"Dr. David Torres Moreno"),
        Materia("Seminario de Investigación", "19:20","20:15", salon, edificio,"Dra- Laura Cruz Abarca")
    )
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .padding(15.dp)
            .fillMaxSize()) {
        item  {
            Row {
                Text(text = "Hora",
                    style = TextStyle(
                        Color.Gray,
                        textAlign = TextAlign.Center
                    ),
                    modifier= Modifier.size(width = 90.dp, height = 20.dp))
                Text(text = " Salon o Laboratorio",
                    style = TextStyle(
                        Color.Gray,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.size(width = 270.dp, height = 20.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            materias.forEach(){materia ->
                MateriasList(materia)
            }
        }
    }
}
