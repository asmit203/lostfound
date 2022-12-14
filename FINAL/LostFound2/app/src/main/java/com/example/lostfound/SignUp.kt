package com.example.lostfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var edtemail: EditText
    private lateinit var edtpass: EditText
    private lateinit var edtpasscnf: EditText
    private lateinit var btnsignup: Button

    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        mAuth = FirebaseAuth.getInstance()
        edtemail = findViewById(R.id.edt_email)
        edtpass = findViewById(R.id.edt_pass)
        edtpasscnf = findViewById(R.id.edt_pass_cnf)
        btnsignup = findViewById(R.id.btn_signup)
        btnsignup.setOnClickListener{
            val email = edtemail.text.toString()
            val password = edtpass.text.toString()
            if((edtemail.text.toString().contains("@iitp.ac.in")) and (edtpasscnf.text.toString()==password)) {
                signup(email, password)
            }
            else{
                Toast.makeText(this,"Enter IITP email or recheck password",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun signup(email:String,password:String)
    {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    mAuth.currentUser?.sendEmailVerification()?.addOnSuccessListener {
                        Toast.makeText(this,"Mail sent!",Toast.LENGTH_SHORT).show()
                    }
                    //jump to home page
                    val intent = Intent(this@SignUp,LogIn::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}