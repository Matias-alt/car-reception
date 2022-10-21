package com.example.registrovehiculosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
    }

    fun signIn(view: View) {
        val mail =
            findViewById<EditText>(R.id.txt_signin_email).text.toString();
        val pass =
            findViewById<EditText>(R.id.txt_signin_password).text.toString();

        val bundle = Bundle();
        bundle.putString("mail", mail);
        bundle.putString("pass", pass);

        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent);

        val toast = Toast.makeText(
            applicationContext,
            "Registro Correcto",
            Toast.LENGTH_LONG
        )
        toast.show()
    }


    fun goLogin(view: View){
        val intent = Intent(this, MainActivity::class.java);
        startActivity(intent);
    }
}