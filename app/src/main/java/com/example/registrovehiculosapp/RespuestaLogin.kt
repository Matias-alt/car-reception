package com.example.registrovehiculosapp

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
@Serializable

class RespuestaLogin (
    var result : String
)