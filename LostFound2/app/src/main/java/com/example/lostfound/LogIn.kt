package com.example.lostfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogIn : AppCompatActivity() {
    private lateinit var edtemail: EditText
    private lateinit var edtpass: EditText
    private lateinit var btnlogin: Button
    private lateinit var btnsignup: Button

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()

        edtemail = findViewById(R.id.edt_email)
        edtpass = findViewById(R.id.edt_pass)
        btnlogin = findViewById(R.id.btn_login)
        btnsignup = findViewById(R.id.btn_signup)
        btnsignup.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
        }
        btnlogin.setOnClickListener {
            val email = edtemail.text.toString()
            val password = edtpass.text.toString()

            login(email,password)
        }

    }

    private fun login(email: String,password:String)
    {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@LogIn,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@LogIn,"User Doesn't Exists",Toast.LENGTH_SHORT).show()
                }
            }

    }
}