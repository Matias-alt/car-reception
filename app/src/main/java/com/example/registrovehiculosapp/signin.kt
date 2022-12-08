package com.example.registrovehiculosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import okhttp3.*
import java.io.IOException


class signin : AppCompatActivity(){
    val ruta: String = "https://www.fer-sepulveda.cl/API_PRUEBA2/api-service.php";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        this.supportActionBar!!.title = "Car Reception"
    }
    fun goLogin(view: View){
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent);
    }

    fun signIn(view: View) {

        val client = OkHttpClient();
        val mediaType: MediaType? = MediaType.parse("application/json; charset=utf-8");

        val mail =
            findViewById<EditText>(R.id.txt_signin_email).text.toString();
        val pass =
            findViewById<EditText>(R.id.txt_signin_password).text.toString();
        val nombre =
            findViewById<EditText>(R.id.txt_signin_nombre).text.toString();
        val apellido =
            findViewById<EditText>(R.id.txt_signin_apellido).text.toString();


        var json = "{\"nombreFuncion\":\"UsuarioAlmacenar\", \"parametros\": [\"" + mail + "\", \"" + pass + "\", \"" + nombre + "\", \"" + apellido + "\"]}"
        val body: RequestBody = RequestBody.create(mediaType,json);
        val request: Request = Request.Builder().url(ruta).post(body).build();

        client.newCall(request).enqueue(object:Callback{
            override fun onFailure(call: Call, e: IOException) {
                println("AVR: la petición post falló");
            }

            override fun onResponse(call: Call, response: Response) {
                //println("AVR: " + response.body()?.string());
                val jsonData = response.body()?.string()
                val respuesta = Json.decodeFromString<Respuesta>(jsonData.toString())

                println("AVR: " + respuesta.result[0].RESPUESTA)

                if (respuesta.result[0].RESPUESTA == "OK"){
                    runOnUiThread{
                        Toast.makeText(applicationContext, "Usuario Registrado correctamente", Toast.LENGTH_LONG).show();
                    }
                    startActivity(intent);
                }

                if (respuesta.result[0].RESPUESTA == "ERR01"){
                    runOnUiThread{
                        Toast.makeText(applicationContext, "Ups, algo ocurrió", Toast.LENGTH_LONG).show()
                    }
                }
            }
        })

    }



}