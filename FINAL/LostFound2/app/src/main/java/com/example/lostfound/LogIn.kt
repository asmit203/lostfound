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
            if((email?.isNotEmpty() ?: false) and (password?.isNotEmpty() ?: false)) {
                    login(email, password)

            }
            else{
                Toast.makeText(this,"Enter Details",Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun login(email: String,password:String)
    {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    if (mAuth.currentUser!!.isEmailVerified) {
                        val intent = Intent(this@LogIn,MainActivity::class.java)
                        startActivity(intent)
                    }
                    else{
                        Toast.makeText(this,"Not verified",Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(this@LogIn,"User Doesn't Exists",Toast.LENGTH_SHORT).show()
                }
            }

    }
}