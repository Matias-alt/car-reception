package com.example.registrovehiculosapp

import kotlinx.serialization.Serializable

@Serializable
class Respuesta (
    var result: List<Mensaje>
    )