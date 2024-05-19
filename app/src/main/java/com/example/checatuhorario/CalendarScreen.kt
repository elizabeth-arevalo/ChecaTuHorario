package com.example.checatuhorario


import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.LocationOn
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.ui.theme.blue80
import com.example.checatuhorario.ui.theme.blueL40
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalendarScreen(navController: NavHostController){
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(colors = topAppBarColors(
                containerColor = blueL40,
                titleContentColor = blue80,
            ),
                title = {
                    Column {
                        Text("Calendario",
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            CalendarApp()
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
    LazyRow {
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
                modifier = Modifier.padding(horizontal = 40.dp, vertical = 20.dp
                )
            )
            IconButton(onClick = {
            // invoke previous callback when its button clicked
            onPrevClickListener(data.startDate.date)
            },
                modifier = Modifier.fillMaxWidth()) {
                Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = "Arrow-Back")
            }
            IconButton(onClick = {
                // invoke next callback when this button is clicked
                onNextClickListener(data.endDate.date)
            }) {
                Icon(imageVector = Icons.Default.KeyboardArrowRight,
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


@OptIn(ExperimentalMaterial3Api::class)
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


@Composable
fun ListCards(){
    LazyColumn (modifier = Modifier
        .fillMaxWidth()
        .padding(15.dp)) {
        item () {
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

            // Card 1
            Row {
                Column(modifier= Modifier
                    .size(width = 90.dp, height = 80.dp)
                    .padding(vertical = 8.dp)) {
                    Text(text = "14:25",
                        style = TextStyle(
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(text = "15:15",
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .fillMaxWidth(),
                        style = TextStyle(
                            Color.Gray,
                            textAlign = TextAlign.Center
                        )
                    )
                }


                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = blue80,
                    ),
                    modifier = Modifier
                        .size(width = 240.dp, height = 120.dp)
                ) {
                    Column(modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()) {
                        Text(
                            text = "Optativa - Ciencia de Datos",
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            style = TextStyle(fontSize = 15.sp)
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Row {
                            Icon(imageVector = Icons.Default.LocationOn, contentDescription = "loc-icon")
                            Text(text = "Salón 40\nEdificio 2",
                                style = TextStyle(fontSize = 11.sp))
                        }
                        Spacer(modifier = Modifier.height(6.dp))
                        Row {
                            Image(
                                painter = painterResource(R.drawable.fcaei),
                                contentDescription = "Contact profile picture",
                                modifier = Modifier
                                    // Set image size to 40 dp
                                    .size(40.dp)
                                    // Clip image to be shaped as a circle
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Dra. Yessica Yazmin Calderón Segura",
                                style = TextStyle(fontSize = 14.sp))
                        }

                    }

                }
            } // Fin Card 1
        }
    }
}
