package com.example.registrovehiculosapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val ruta: String = "https://www.fer-sepulveda.cl/API_PRUEBA2/api-service.php";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.title = "Registro Vehículos App"
    }

    fun login(view: View) {

        val client = OkHttpClient();
        val mediaType: MediaType? = MediaType.parse("application/json; charset=utf-8");

        val mail =
            findViewById<EditText>(R.id.txt_mail).text.toString();
        val pass =
            findViewById<EditText>(R.id.txt_pass).text.toString();


        var json = "{\"nombreFuncion\":\"UsuarioLogin\", \"parametros\": [\"" + mail + "\", \"" + pass + "\"]}"

        val body: RequestBody = RequestBody.create(mediaType,json);
        val request: Request = Request.Builder().url(ruta).post(body).build();

        val intent = Intent(this, Home::class.java);

        client.newCall(request).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("AVR: la petición post falló");
            }

            override fun onResponse(call: Call, response: Response) {
                //println("AVR: " + response.body()?.string());
                val jsonData = response.body()?.string()
                val respuesta = Json.decodeFromString<Respuesta>(jsonData.toString())

                println("AVR: " + respuesta.result[0].RESPUESTA)

                if (respuesta.result[0].RESPUESTA == "LOGIN OK"){
                    runOnUiThread{
                        Toast.makeText(applicationContext, "Ingreso Exitoso", Toast.LENGTH_LONG).show();
                    }
                }

                if (respuesta.result[0].RESPUESTA == "LOGIN NOK"){
                    runOnUiThread{
                        Toast.makeText(applicationContext, "Ups, algo ocurrió", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })
    }


    fun goSignIn(view:View){
        val intent = Intent(this, signin::class.java);
        startActivity(intent);
    }
}