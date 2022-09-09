package com.example.registrovehiculosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.view.View
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*

class Home : AppCompatActivity() {


    var formatDate = SimpleDateFormat("dd MMMM YYYY", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val extras = intent.extras;
        var mail = "";

        if(extras != null) {
            mail = extras.getString("mail").toString();
            this.supportActionBar!!.title = "Bienvenido/a: " + mail;
        }

        initBrandSpinner();
        initColorSpinner();
        initReasonSpinner();
    }

    fun datePicker(view:View){
        findViewById<Button>(R.id.btn_home_entry_date).setOnClickListener(View.OnClickListener {
            val getDate : Calendar = Calendar.getInstance()
            val datepicker = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val selectDate : Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, i)
                selectDate.set(Calendar.MONTH, i2)
                selectDate.set(Calendar.DAY_OF_MONTH, i3)

                val date: String = formatDate.format(selectDate.time)
                findViewById<TextView>(R.id.txt_home_date).text = date

            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datepicker.show()
        })
    }

    fun onSubmit(view: View){
        var patent = findViewById<TextView>(R.id.txt_home_patent).text.toString();
        var brand = findViewById<Spinner>(R.id.sp_home_brand).selectedItem.toString();
        var color = findViewById<Spinner>(R.id.sp_home_color).selectedItem.toString();
        var entryDate = findViewById<TextView>(R.id.txt_home_date).text.toString();
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

    private fun initBrandSpinner(){
        var options = arrayOf("Chevrolet", "Audi", "Toyota", "Ford");

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_home_brand);
        spinner.adapter = adapter;
    }

    private fun initColorSpinner(){
        var options = arrayOf("Plateado", "Negro", "Rojo", "Azul");

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_home_color);
        spinner.adapter = adapter;
    }

    private fun initReasonSpinner(){
        var options = arrayOf("Servicio", "Mantención", "Otro");
        var reason = findViewById<Spinner>(R.id.sp_home_reason)
        var otherReason = findViewById<TextView>(R.id.txt_home_other_reason)

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_home_reason);
        spinner.adapter = adapter;

        if (reason.selectedItem.toString() == "Otro"){
            otherReason.isEnabled = true;
        }
        else
            otherReason.isEnabled = false;
    }

    private fun validateForm(): Boolean {
        val entryDate = findViewById<TextView>(R.id.btn_home_entry_date).text;
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