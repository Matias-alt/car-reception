package com.example.registrovehiculosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.content.Intent
import android.view.View
import android.widget.*

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val extras = intent.extras;
        var mail = "";

        if(extras != null) {
            mail = extras.getString("mail").toString();
            this.supportActionBar!!.title = "Bienvenido/a: " + mail;
        }

        iniciarSpinnerMarca();
        iniciarSpinnerColor();
        iniciarSpinnerMotivo();
    }

    fun onSubmit(view: View){
        var patent = findViewById<TextView>(R.id.txt_home_patent).text.toString();
        var brand = findViewById<Spinner>(R.id.sp_home_brand).selectedItem.toString();
        var color = findViewById<Spinner>(R.id.sp_home_color).selectedItem.toString();
        var entryDate = findViewById<TextView>(R.id.txt_home_entry_date).text.toString();
        var reason = findViewById<Spinner>(R.id.sp_home_reason).selectedItem.toString();
        var kilometers = findViewById<TextView>(R.id.txt_home_kilometers).text.toString();
        var name = findViewById<TextView>(R.id.txt_home_name).text.toString();
        var rut = findViewById<TextView>(R.id.txt_home_rut).text.toString();

        val builder = AlertDialog.Builder(this)
        if(validateForm()){

            val bundle = Bundle();
            bundle.putString("patent", patent);
            bundle.putString("brand", brand);
            bundle.putString("color", color);
            bundle.putString("entryDate", entryDate);
            bundle.putString("reason", reason);
            bundle.putString("kilometers", kilometers);
            bundle.putString("name", name);
            bundle.putString("rut", rut);
            val intent = Intent(this, Summary::class.java);
            intent.putExtras(bundle);

            startActivity(intent);
        }
    }

    private fun iniciarSpinnerMarca(){
        var opciones = arrayOf("Chevrolet", "Audi", "Toyota", "Ford");

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_home_brand);
        spinner.adapter = adapter;
    }

    private fun iniciarSpinnerColor(){
        var opciones = arrayOf("Plateado", "Negro", "Rojo", "Azul");

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_home_color);
        spinner.adapter = adapter;
    }

    private fun iniciarSpinnerMotivo(){
        var opciones = arrayOf("Servicio", "Mantención", "Otro");
        var motivo = findViewById<Spinner>(R.id.sp_home_reason)
        var otro_motivo = findViewById<TextView>(R.id.txt_home_other_reason)

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_home_reason);
        spinner.adapter = adapter;

        if (motivo.selectedItem.toString() == "Otro"){
            otro_motivo.isEnabled == true;
        }
        else
            otro_motivo.isEnabled == false;
    }

    private fun validateForm(): Boolean {
        val entryDate = findViewById<TextView>(R.id.txt_home_entry_date).text;
        val kilometers = findViewById<TextView>(R.id.txt_home_kilometers).text;
        val name = findViewById<TextView>(R.id.txt_home_name).text;
        val rut = findViewById<TextView>(R.id.txt_home_rut).text;

        if(entryDate.isEmpty()){
            showDialog("Debe ingresar una fecha de ingreso")
            return false
        }
        else if(kilometers.isEmpty()){
            showDialog("Debe ingresar cantidad de kilometros")
            return false
        }
        else if(name.isEmpty()){
            showDialog("Debe ingresar su nombre")
            return false
        }
        else if(rut.isEmpty()){
            showDialog("Debe ingresar su rut")
            return false
        }

        return true
    }

    private fun showDialog(text: String){
        val toast = Toast.makeText(this, text,
            Toast.LENGTH_LONG);
        toast.show();
    }
}