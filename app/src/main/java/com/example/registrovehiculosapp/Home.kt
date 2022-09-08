package com.example.registrovehiculosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.DialogInterface
import android.view.View
import android.widget.*
import androidx.core.view.doOnAttach
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        val extras = intent.extras;
        var mail = "";
        var pass = "";

        if(extras != null) {

            mail = extras.getString("mail").toString();
            this.supportActionBar!!.title = "Bienvenido/a: " + mail;
        }

        iniciarSpinnerMarca();
        iniciarSpinnerColor();
        iniciarSpinnerMotivo()
    }
    fun iniciarSpinnerMarca(){
        var opciones = arrayOf("Chevrolet", "Audi", "Toyota", "Ford");

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_marca);
        spinner.adapter = adapter;
    }

    fun iniciarSpinnerColor(){
        var opciones = arrayOf("Plateado", "Negro", "Rojo", "Azul");

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_color);
        spinner.adapter = adapter;
    }

    fun iniciarSpinnerMotivo(){
        var opciones = arrayOf("Servicio", "Mantención", "Otro");
        var motivo = findViewById<Spinner>(R.id.sp_motivo)
        var otro_motivo = findViewById<TextView>(R.id.txt_motivo_otro)

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_motivo);
        spinner.adapter = adapter;

        if (motivo.selectedItem.toString() == "Otro"){
            otro_motivo.isEnabled == true;
        }
        else
            otro_motivo.isEnabled == false;
    }

    fun onSubmit(){
        val builder = AlertDialog.Builder(this)
    }
}