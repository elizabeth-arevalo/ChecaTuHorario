package com.example.checatuhorario

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blue80
import com.example.checatuhorario.utils.ProfesorCardItem
import com.example.checatuhorario.utils.dataClasses.Profesor


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(navController: NavHostController) {
    val profesores = listOf(
        Profesor("Yessica Yazmin","Calderón Segura", "123456789", "juan.perez@example.com", "123456789"),
        Profesor("Roberto Pablo","López Romero", "987654321", "maria.lopez@example.com", "987654321"),
        Profesor("Beatriz","Serrano Rodríguez", "123456789", "juan.perez@example.com", "123456789"),
        Profesor("Ariana María","García Reyes", "987654321", "maria.lopez@example.com", "987654321"),
        Profesor("David","Torres Moreno", "123456789", "juan.perez@example.com", "123456789"),
        Profesor("Laura","Cruz Abarca", "987654321", "maria.lopez@example.com", "987654321"),
    )

    var searchText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedRange by remember { mutableStateOf<CharRange?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary,
                titleContentColor = blue80,
            ),
                title = {
                    Row(modifier = Modifier
                        .fillMaxWidth()) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = "Regresar",
                            Modifier.clickable {
                                navController.popBackStack() // Se limpia el stack de navegacion
                                navController.navigate(AppScreens.HomeScreen.route)

                            } )
                        Text(
                            text = "Lista de contactos",
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
        Column(Modifier.padding(innerPadding)) {
            // Barra de búsqueda
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                label = { Text("Buscar por nombre") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Botones de rango de letras
            LazyRow(
                modifier = Modifier.padding(10.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                item{
                    listOf(
                        'A'..'D', 'E'..'H', 'I'..'L', 'M'..'P', 'Q'..'T', 'U'..'Z'
                    ).forEach { range ->
                        Button(onClick = { selectedRange = range }) {
                            Text("${range.first}-${range.last}", fontSize = 20.sp)
                        }
                    }
                    Button(onClick = { selectedRange = null }) {
                        Text("Todos",fontSize = 20.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            LazyColumn(modifier = Modifier.padding(10.dp)) {

                // Filtrar profesores
                val filteredProfesores = profesores.filter {
                    val inRange = selectedRange?.let { range ->
                        it.nombre.first().uppercaseChar() in range
                    } ?: true
                    val matchesSearch = it.nombre.contains(searchText.text, ignoreCase = true)
                    inRange && matchesSearch
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    // Lista de profesores filtrados
                    filteredProfesores.forEach { profesor ->
                        ProfesorCardItem(profesor = profesor)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }

            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun ContactListScreenPreview(){
    val navController =rememberNavController()
    ContactListScreen(navController = navController)
}

