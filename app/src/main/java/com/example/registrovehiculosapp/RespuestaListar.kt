package com.example.registrovehiculosapp

import kotlinx.serialization.Serializable

@Serializable
class RespuestaListar (
    val result: List<MensajeListar>
)