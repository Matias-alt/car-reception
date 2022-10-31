package com.example.registrovehiculosapp

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray

@Serializable
class RespuestaListar (
    val result: Array<String>
)