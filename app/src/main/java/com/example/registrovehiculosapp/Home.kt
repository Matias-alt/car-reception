package com.example.registrovehiculosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class Home : AppCompatActivity() {
    var formatDate = SimpleDateFormat("dd/MM/YYYY", Locale.US)
    val ruta: String = "https://www.fer-sepulveda.cl/API_PRUEBA2/api-service.php";


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        this.supportActionBar!!.title = "Ingresar nuevo registro"

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
            val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->

                val selectDate : Calendar = Calendar.getInstance()
                selectDate.set(Calendar.YEAR, i)
                selectDate.set(Calendar.MONTH, i2)
                selectDate.set(Calendar.DAY_OF_MONTH, i3)

                val date: String = formatDate.format(selectDate.time)
                findViewById<TextView>(R.id.txt_home_date).text = date

            }, getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        })
    }

    fun onSubmit(view: View){
        //Obtener variable mail
        val extras = intent.extras;

        val client = OkHttpClient();
        val mediaType: MediaType? = MediaType.parse("application/json; charset=utf-8");

        //Variables
        var patent = findViewById<TextView>(R.id.txt_home_patent).text.toString();
        var brand = findViewById<Spinner>(R.id.sp_home_brand).selectedItem.toString();
        var color = findViewById<Spinner>(R.id.sp_home_color).selectedItem.toString();
        var entryDate = findViewById<TextView>(R.id.txt_home_date).text.toString();
        var kilometers = findViewById<TextView>(R.id.txt_home_kilometers).text.toString();
        var reason = findViewById<Spinner>(R.id.sp_home_reason).selectedItem.toString();
        var reasonText = findViewById<TextView>(R.id.txt_home_other_reason).text.toString();
        var rut = findViewById<TextView>(R.id.txt_home_rut).text.toString();
        var name = findViewById<TextView>(R.id.txt_home_name).text.toString();
        var mail = extras?.getString("mail").toString();

        var json = "{\"nombreFuncion\":\"InspeccionAlmacenar\", \"parametros\": [\"" + patent + "\", \"" + brand + "\", \"" + color + "\", \"" + entryDate + "\", \"" + kilometers + "\", \"" + reason + "\", \"" + reasonText + "\", \"" + rut + "\", \"" + name + "\", \"" + mail + "\"]}"

        val body: RequestBody = RequestBody.create(mediaType,json);
        val request: Request = Request.Builder().url(ruta).post(body).build();
        val intent = Intent(this, Summary::class.java);

        if(validateForm()){
            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    println("AVR: la petición post falló");
                }

                override fun onResponse(call: Call, response: Response) {
                    //println("AVR: " + response.body()?.string());
                    val jsonData = response.body()?.string()
                    val respuesta = Json.decodeFromString<Respuesta>(jsonData.toString())

                    println("AVR: " + respuesta.result[0].RESPUESTA)

                    if (respuesta.result[0].RESPUESTA == "OK") {
                        runOnUiThread {
                            Toast.makeText(
                                applicationContext,
                                "Inspección Creada",
                                Toast.LENGTH_LONG
                            ).show();
                        }

                        val bundle = Bundle();
                        bundle.putString("patent", patent);
                        bundle.putString("brand", brand);
                        bundle.putString("color", color);
                        bundle.putString("entryDate", entryDate);
                        bundle.putString("reason", reason);
                        bundle.putString("kilometers", kilometers);
                        bundle.putString("name", name);
                        bundle.putString("rut", rut);
                        intent.putExtras(bundle);

                        startActivity(intent)
                    } else {
                        runOnUiThread {
                            Toast.makeText(
                                applicationContext,
                                "Ups, algo ocurrió",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                }
            })
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

    private fun initReasonSpinner() {
        var options = arrayOf("Servicio", "Mantención", "Otro");
        var reason = findViewById<Spinner>(R.id.sp_home_reason)
        var otherReason = findViewById<TextView>(R.id.txt_home_other_reason)

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        var spinner = findViewById<Spinner>(R.id.sp_home_reason);
        spinner.adapter = adapter;

        otherReason.isEnabled = reason.selectedItem == "Otro"
    }

    private fun validateForm(): Boolean {
        val entryDate = findViewById<TextView>(R.id.txt_home_date).text;
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