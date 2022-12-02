package com.example.lostfound

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lostfound.databinding.ActivityLostItemsBinding
import com.example.lostfound.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class lost_items : AppCompatActivity() {
    private lateinit var binding : ActivityLostItemsBinding
    private lateinit var database: DatabaseReference
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLostItemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        val email = mAuth.currentUser?.email.toString()
        binding.editTextTextEmailAddress.text = email
        binding.submit.setOnClickListener {
        val name = binding.itemname.text.toString()
        val date = binding.editTextDate.text.toString()
        val phnnum = binding.editTextPhone.text.toString()
        val address = binding.editTextTextPostalAddress.text.toString()
        val pht_url = binding.photourl.text.toString()
        database = FirebaseDatabase.getInstance().getReference("LostItems")
        val LostItems = LostItems(name, date, phnnum, address, email, pht_url)

        database.child(name).setValue(LostItems).addOnSuccessListener {
            binding.itemname.text.clear()
            binding.editTextDate.text.clear()
            binding.editTextPhone.text.clear()
            binding.editTextTextPostalAddress.text.clear()
//            binding.editTextTextEmailAddress.text.clear()
            binding.photourl.text.clear()

            Toast.makeText(this,"successfully saved", Toast.LENGTH_SHORT).show()

        }.addOnFailureListener{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }

    }

    }
}