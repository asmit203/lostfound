package com.example.lostfound

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var welcome : Button
    lateinit var bottomNav : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        welcome = findViewById(R.id.welcome)
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            welcome.setOnClickListener {
                val intent = Intent(this, LogIn::class.java)
                startActivity(intent)
            }
        } else {
            welcome.setOnClickListener {
                val intent = Intent(this, Home::class.java)
                startActivity(intent)
            }
        }

    }
    override fun onBackPressed() {
        // super.onBackPressed();
        return
    }
}