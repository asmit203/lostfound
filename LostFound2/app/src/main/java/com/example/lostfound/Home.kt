package com.example.lostfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.core.view.View

class Home : AppCompatActivity() {
    private lateinit var logoutbtn: Button
    private lateinit var lostitem : Button
    private lateinit var founditem : Button
    private lateinit var claimpage : Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var claimbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mAuth = FirebaseAuth.getInstance()
        setContentView(R.layout.activity_home)
        logoutbtn = findViewById(R.id.logout)
        logoutbtn.setOnClickListener {
            signOut();
        }
        lostitem = findViewById(R.id.lostbtn)
        lostitem.setOnClickListener{
            val intent = Intent(this,lost_items::class.java)
            startActivity(intent)
        }
        founditem = findViewById(R.id.foundbtn)
        founditem.setOnClickListener{
            val intent = Intent(this,found_item::class.java)
            startActivity(intent)
        }
        claimpage = findViewById(R.id.claim_page)
        claimpage.setOnClickListener{
            val intent = Intent(this,claim_items::class.java)
            startActivity(intent)
        }


    }

    private fun signOut() {
        //TODO("Not yet implemented")
        mAuth.signOut();
        val intent = Intent(this@Home,MainActivity::class.java)
        startActivity(intent)
    }

}