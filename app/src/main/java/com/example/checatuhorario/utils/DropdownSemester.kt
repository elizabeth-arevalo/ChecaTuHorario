package com.example.checatuhorario.utils

import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable

@Composable
fun TaskMenu(
    expanded: Boolean, // (1)
    onItemClick: (String) -> Unit,
    onDismiss: () -> Unit
) {

    val options = listOf( // (2)
        "Cambiar nombre",
        "Enviar por email",
        "Copiar enlace",
        "Ocultar subtareas completas",
        "Eliminar"
    )

    DropdownMenu( // (3)
        expanded = expanded,
        onDismissRequest = onDismiss
    ) {
        options.forEach { option ->
            DropdownMenuItem(text = { /*TODO*/ }, onClick = { /*TODO*/ })
        }
    }
}