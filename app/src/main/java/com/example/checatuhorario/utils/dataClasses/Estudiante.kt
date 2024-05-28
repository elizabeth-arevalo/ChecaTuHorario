package com.example.checatuhorario.utils.dataClasses

import com.google.firebase.database.Exclude

data class Estudiante(
    val nombre: String = "",
    val apellidos: String = "",
    val semesterSelected: String = "",
    val email: String = "",
    val password: String = "", // Hash the password before saving
    val matricula: String = "",
    val grupo: String = "",
    val carrera: String = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "nombre" to nombre,
            "apellidos" to apellidos,
            "matricula" to matricula,
            "carrera" to carrera,
            "semestre" to semesterSelected,
            "grupo" to grupo,
            "email" to email,
        )
    }
}