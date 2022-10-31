package com.example.registrovehiculosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException

class Inspecciones : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspecciones)

        val ruta = "https://fer-sepulveda.cl/API_PRUEBA2/api-service.php?nombreFuncion=InspeccionObtener"
        val client = OkHttpClient();
        val mediaType: MediaType? = MediaType.parse("application/json; charset=utf-8");

        val request: Request = Request.Builder().url(ruta).get().build();

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("AVR: la petición post falló");
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonData = response.body()?.string()
                println("AVR: " + jsonData)

            }
        })
    }
}