package com.example.checatuhorario.utils

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.Instant
import java.time.ZoneId
import java.util.Calendar


@Composable
fun InfoSection(title: String, content: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = content,
            style = TextStyle(
                color = Color(0xFF3F3E3E)
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateSection() {

    val state = rememberDatePickerState()
    var showDialog by remember {
        mutableStateOf(false)
    }
    Column {
        Text(
            text = "Fecha")
        OutlinedButton(onClick = { showDialog=true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Selecciona una Fecha")
        }
        if (showDialog ==true){
            DatePickerDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false
                    }) {
                        Text(text = "Confirmar")
                    } },
                dismissButton = {
                    OutlinedButton(onClick = { showDialog = false }) {
                        Text(text = "Cancelar")
                    }
                }
            ) {
                DatePicker(state = state)
            }
        }
        val date =state.selectedDateMillis
        date?.let {
            val localDate = Instant.ofEpochMilli(it).atZone(ZoneId.of("UTC")).toLocalDate()
            Text(text = "Fecha seleccionada: ${localDate.dayOfMonth}/${localDate.monthValue}/${localDate.year}")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DateSectionPreview(){
    DateSection()
}



// Seccion para seleccionar la hora de inicio y cierre de una conferencia
@SuppressLint("DefaultLocale")
@Composable
fun TimeSection() {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val initialHour = calendar.get(Calendar.HOUR_OF_DAY)
    val initialMinute = calendar.get(Calendar.MINUTE)

    var startTime by remember { mutableStateOf("16:30") }
    var endTime by remember { mutableStateOf("17:45") }

    val startTimePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            startTime = String.format("%02d:%02d", hour, minute)
        }, initialHour, initialMinute, true
    )

    val endTimePickerDialog = TimePickerDialog(
        context,
        { _, hour: Int, minute: Int ->
            endTime = String.format("%02d:%02d", hour, minute)
        }, initialHour, initialMinute, true
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Hora de Inicio")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = startTime,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(8.dp)
                .clickable { startTimePickerDialog.show() },
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Hora de Finalización")
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = endTime,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFEEEEEE))
                .padding(8.dp)
                .clickable { endTimePickerDialog.show() },
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )
        )
    }
}

@Composable
fun ActionButtons(

) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "GUARDAR")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "CANCELAR")
        }
    }
}