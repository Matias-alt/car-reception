package com.example.registrovehiculosapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val extras = intent.extras;
        var mail = "";
        var pass = "";

        if(extras != null) {
            mail = extras.getString("mail").toString();
            pass = extras.getString("pass").toString();

            this.supportActionBar!!.title = mail;
            this.supportActionBar!!.setDisplayHomeAsUpEnabled(true);
            this.supportActionBar!!.setDisplayShowHomeEnabled(true);
        }

    }
}