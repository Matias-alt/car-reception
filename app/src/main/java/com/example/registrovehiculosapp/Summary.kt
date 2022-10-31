package com.example.registrovehiculosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import okhttp3.MediaType
import okhttp3.OkHttpClient

class Summary : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        this.supportActionBar!!.title = "Resumen del Ingreso";

        val extras = intent.extras;

        if(extras != null) {
            var patent = extras.getString("patent").toString();
            var brand = extras.getString("brand").toString();
            var color = extras.getString("color").toString();
            var entryDate = extras.getString("entryDate").toString();
            var kilometers = extras.getString("kilometers").toString();
            var reason = extras.getString("reason").toString();
            var name = extras.getString("name").toString();
            var rut = extras.getString("rut").toString();
            findViewById<TextView>(R.id.txt_summary_patent).text = "Patente: $patent"
            findViewById<TextView>(R.id.txt_summary_brand).text = "Marca: $brand"
            findViewById<TextView>(R.id.txt_summary_color).text = "Color: $color"
            findViewById<TextView>(R.id.txt_summary_entry_date).text = "Fecha de ingreso: $entryDate"
            findViewById<TextView>(R.id.txt_summary_reason).text = "Kilometros: $kilometers "
            findViewById<TextView>(R.id.txt_summary_kilometers).text = "Motivo: $reason"
            findViewById<TextView>(R.id.txt_summary_name).text = "Nombre: $name"
            findViewById<TextView>(R.id.txt_summary_rut).text = "Rut: $rut"
        }
    }

    fun listarInspecciones(view:View){
        val intent = Intent(this, Inspecciones::class.java);
        startActivity(intent)
    }
}