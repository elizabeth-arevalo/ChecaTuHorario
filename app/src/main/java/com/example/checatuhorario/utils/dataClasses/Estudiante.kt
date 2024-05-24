package com.example.checatuhorario.utils.dataClasses

data class User(
    val email: String = "",
    val password: String = "", // Hash the password before saving
    val matricula: String = "",
    val nombre: String = "",
    val apellidos: String = "",
    val semesterSelected: String = "",
    val grupo: String = "",
    val carrera: String = ""
)