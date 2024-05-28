package com.example.checatuhorario.utils

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun SemesterMenu(
    expanded: Boolean, // (1)
    onItemClick: (String) -> Unit,
    onDismiss: () -> Unit
) {

    val options = listOf( // (2)
        "1",
        "2",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8"
    )

    DropdownMenu( // (3)
        expanded = expanded,
        onDismissRequest = onDismiss,
    ) {
        options.forEach { option ->
            Row {
                DropdownMenuItem(text = { option }, onClick = {
                    var selectedOption = option
                    onItemClick(selectedOption)
                    onDismiss()
                }, Modifier.fillMaxWidth())
            }
            Text(text = option)
        }
    }
}


