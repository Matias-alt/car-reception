package com.example.registrovehiculosapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        this.supportActionBar!!.title = "Home"
    }

    fun solicitarServicio(view: View){
        val intent = Intent(this, Home::class.java);
        startActivity(intent);
    }

    fun asistenciaQr(view: View){
        val intent = Intent(this, RegistroAsistenciaQR::class.java);
        startActivity(intent);
    }
}