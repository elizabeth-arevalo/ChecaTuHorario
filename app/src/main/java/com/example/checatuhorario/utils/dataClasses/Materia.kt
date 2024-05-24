package com.example.checatuhorario.utils.dataClasses

data class Materia(
    val nombre: String,
    var horaInicio: String,
    var horaSalida: String,
    var ubicacionClase: String,
    var ubicacionEdificio: String,
    var profesor: String
)
