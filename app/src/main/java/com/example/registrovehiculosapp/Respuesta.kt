package com.example.registrovehiculosapp

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString

@Serializable
class Respuesta (
    var result: List<Mensaje>
    )