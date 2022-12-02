package com.example.lostfound

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.lostfound.databinding.ActivityHomeBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthRegistrar
import com.google.firebase.database.*
import com.google.firebase.database.core.view.View
import com.google.firebase.ktx.Firebase

class Home : AppCompatActivity() {
    private lateinit var binding : ActivityHomeBinding
    private lateinit var logoutbtn: Button
    private lateinit var lostitem : Button
    private lateinit var founditem : Button
    private lateinit var claimpage : Button
    private lateinit var profilepage : Button
    private lateinit var updateprofile : Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var dbref : DatabaseReference
    private lateinit var claimbtn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.username.text = mAuth.currentUser?.email.toString()
        dbref = FirebaseDatabase.getInstance().getReference("User")
        dbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (itemsnapshot in snapshot.children){
                        val item = itemsnapshot.getValue(User::class.java)
                        if (item?.UID == mAuth.uid.toString()){
//                            binding.name.text = item.name.toString()
                            binding.phnnum.text = item.phonenum.toString()
//                            binding.rollnum.text = item.rollnum.toString()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        super.onCreate(savedInstanceState)


//        setContentView(R.layout.activity_home)
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
        profilepage = findViewById(R.id.profilepage)
        profilepage.setOnClickListener{
            val intent = Intent(this,ProfilePage::class.java)
            startActivity(intent)
        }
        updateprofile = findViewById(R.id.updateprofile)
        updateprofile.setOnClickListener{
            val intent = Intent(this,UpdateProfile::class.java)
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