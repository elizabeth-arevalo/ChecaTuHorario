package com.example.checatuhorario.utils

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.checatuhorario.R
import com.example.checatuhorario.utils.dataClasses.Profesor

@Composable
fun ProfesorCardItem(profesor: Profesor) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Row {
            Text(text = profesor.nombre,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 4.dp))
            Text(text = profesor.apellido,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 20.dp, horizontal = 4.dp))
        }
        if (expanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.padding(horizontal = 20.dp)) {
                Button(
                    onClick = {
                        context.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://wa.me/${profesor.whatsapp}")
                            )
                        ) },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.whatsapp),
                        contentDescription = "WhatsApp"
                    )
                }
                Button(onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_DIAL,
                            Uri.parse("tel:${profesor.telefono}")
                        )
                    )
                },
                    modifier = Modifier
                        .padding(10.dp)
                        .clip(CircleShape)
                ) {
                    Icon(imageVector = Icons.Filled.Call, contentDescription = "Llamada")
                }
                Button(onClick = {
                    context.startActivity(
                        Intent(
                            Intent.ACTION_SENDTO,
                            Uri.parse("email:${profesor.email}")
                        )
                    )
                },
                    modifier = Modifier.padding(10.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "correo")
                }
            }
        }
    }

}