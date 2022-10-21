package com.example.registrovehiculosapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.supportActionBar!!.title = "Registro Vehículos App"
    }

    fun login(view: View) {
        val mail =
            findViewById<EditText>(R.id.txt_mail).text.toString();
        val pass =
            findViewById<EditText>(R.id.txt_pass).text.toString();

        val bundle = Bundle();
        bundle.putString("mail", mail);
        bundle.putString("pass", pass);

        if(pass == "admin") {
            val intent = Intent(this, Home::class.java);
            intent.putExtras(bundle);

            startActivity(intent);
        } else {
            val toast = Toast.makeText(
                applicationContext,
                "Credenciales Inválidas",
                Toast.LENGTH_LONG
            )
            toast.show()
        }
    }


    fun goSignIn(view:View){
        val intent = Intent(this, signin::class.java);
        startActivity(intent);
    }
}