package com.example.checatuhorario

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.checatuhorario.navigation.AppScreens
import com.example.checatuhorario.ui.theme.blueL40
import com.example.checatuhorario.utils.SemesterMenu
import com.example.checatuhorario.utils.dataClasses.Estudiante
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


@Composable
fun RegisterScreen(navController: NavHostController){
    val db = Firebase.firestore
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var matricula by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellidos by remember { mutableStateOf("") }
    var semesterMenuOpen by remember { mutableStateOf(false) }
    var semesterSelected by remember { mutableStateOf("") }
    var grupo by remember { mutableStateOf("") }
    var carrera by remember { mutableStateOf("") }
    val offset = Offset(2.5f, 6.0f)

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            Image(painter = painterResource(id = R.drawable.uniconcep),
                contentDescription = "LETRAS",
                Modifier.size(289.dp,271.dp))
            Text(text = "Ingresa tus Datos",
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 25.sp,
                    shadow = Shadow(
                        color = Color.Gray, offset = offset, blurRadius = 1f
                    )
                ),
                modifier = Modifier.padding(15.dp)
            )
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = apellidos,
                onValueChange = { apellidos = it },
                label = { Text("Apellidos") },
            )


            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = matricula,
                onValueChange = {matricula =it},
                label = { Text(text = "Matrícula") }
            )


            Spacer(modifier = Modifier.height(16.dp))

            Box {
                OutlinedButton(
                    onClick = { semesterMenuOpen = true },
                    modifier = Modifier.padding(6.dp)) {
                    Text(text = "Selecciona el Semestre ")
                    Text(text = semesterSelected, color = Color.Black)
                }
                SemesterMenu(
                    expanded = semesterMenuOpen,
                    onItemClick = { semesterSelected = it },
                    onDismiss = { semesterMenuOpen = false }
                )
            }


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = grupo,
                onValueChange = {grupo =it},
                label = { Text(text = "Grupo") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = carrera,
                onValueChange = {carrera =it},
                label = { Text(text = "Carrera") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))


            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )



            Spacer(modifier = Modifier.height(24.dp))

            Button(
                modifier = Modifier.padding(6.dp),
                onClick = {
                    // 1. Create a data class to represent the user


                    // 2. Create a user object with the collected data
                    val user = Estudiante(
                        email, password, matricula, nombre, apellidos, semesterSelected, grupo, carrera
                    )

                    if (email.isNotEmpty() && password.isNotEmpty()){
                        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                            if (it.isSuccessful){
                                navController.navigate(AppScreens.HomeScreen.route)
                                // 3. Save user data to Firestore
                                isLoading = true // Show loading indicator
                                db.collection("users")
                                    .add(user)
                                    .addOnSuccessListener { documentReference ->
                                        Log.d("Firestore", "DocumentSnapshot added with ID: ${documentReference.id}")
                                        isLoading = false // Hide loading indicator
                                        // Navigate to success screen or perform other actions
                                    }
                                    .addOnFailureListener { e ->
                                        Log.w("Firestore", "Error adding document", e)
                                        isLoading = false // Hide loading indicator
                                        // Handle error (e.g., show error message)
                                    }
                            } else{
                                Toast.makeText(context, "No se ha podido crear el usuario", Toast.LENGTH_LONG).show()
                            }
                        }
                    }
                },
                enabled = !isLoading

            ) {
                if (isLoading) {
                    CircularProgressIndicator()
                } else {
                    Text("Registrarse")
                }
            }
            Row {
                Text(text = "¿Ya tienes cuenta? ")
                Text(text = " Ingresa",
                    color = blueL40,
                    textDecoration = TextDecoration.Underline,
                    modifier = Modifier
                        .clickable { navController.navigate(AppScreens.LoginScreen.route) }
                        .padding(20.dp))
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview(){
    val navController = rememberNavController()
    RegisterScreen(navController)
}