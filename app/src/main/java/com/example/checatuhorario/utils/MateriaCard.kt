package com.example.checatuhorario.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checatuhorario.R
import com.example.checatuhorario.utils.dataClasses.Materia

@Composable
fun MateriasList(materia: Materia) {
    Row(Modifier.padding(vertical = 10.dp)) {
        Column(modifier= Modifier
            .size(width = 90.dp, height = 80.dp)
            .padding(vertical = 8.dp)) {
            Text(text = materia.horaInicio,
                style = TextStyle(
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = materia.horaSalida,
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .fillMaxWidth(),
                style = TextStyle(
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )
            )
        }


        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
            modifier = Modifier
                .size(width = 260.dp, height = 130.dp)
        ) {
            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {
                Text(
                    text = materia.nombre,
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 15.sp, color = Color.Black)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Row {
                    Icon(imageVector = Icons.Default.LocationOn, contentDescription = "loc-icon")
                    Text(text = "${materia.ubicacionClase}\n${materia.ubicacionEdificio}",
                        style = TextStyle(fontSize = 12.sp, color = Color.Black)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))

                Row {
                    Image(
                        painter = painterResource(R.drawable.fcaei),
                        contentDescription = "Contact profile picture",
                        modifier = Modifier
                            // Set image size to 40 dp
                            .size(25.dp)
                            // Clip image to be shaped as a circle
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = materia.profesor,
                        style = TextStyle(fontSize = 13.sp, color = Color.Black)
                    )
                }

            }

        }
    } // Fin materia 1

}