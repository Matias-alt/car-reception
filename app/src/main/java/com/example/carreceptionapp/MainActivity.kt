package com.example.carreceptionapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(view: View) {
        val user = findViewById<EditText>(R.id.txt_user).text.toString();
        val contrasena = findViewById<EditText>(R.id.txt_password).text.toString();

        if(contrasena.equals("admin")) {
            val intent = Intent(this, Home::class.java);

            val bundle = Bundle();
            bundle.putString("usuario", user);

            intent.putExtras(bundle);
            startActivity(intent);
        } else {
            val toast = Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}