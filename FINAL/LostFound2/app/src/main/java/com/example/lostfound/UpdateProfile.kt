package com.example.lostfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.lostfound.databinding.ActivityLostItemsBinding
import com.example.lostfound.databinding.ActivityUpdateProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateProfile : AppCompatActivity() {
    private lateinit var binding : ActivityUpdateProfileBinding
    private lateinit var database: DatabaseReference
    private lateinit var mAuth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAuth = FirebaseAuth.getInstance()
        val email = mAuth.currentUser?.email.toString()
        binding.email.text = email

//        setContentView(R.layout.activity_update_profile)
        binding.submitProfile.setOnClickListener {
            val name = binding.name.text.toString()
            val rollnum = binding.rollnum.text.toString()
            val phnnum = binding.phonenum.text.toString()
//            val address = binding.editTextTextPostalAddress.text.toString()
//            val pht_url = binding.photourl.text.toString()
            database = FirebaseDatabase.getInstance().getReference("User")
            val UID = mAuth.uid.toString()
            val useritem = User(name, phnnum, rollnum, UID)

            database.child(UID).setValue(useritem).addOnSuccessListener {
                binding.name.text.clear()
                binding.rollnum.text.clear()
                binding.phonenum.text.clear()
//                binding.editTextTextPostalAddress.text.clear()
//            binding.editTextTextEmailAddress.text.clear()
//                binding.photourl.text.clear()

                Toast.makeText(this,"successfully Updated", Toast.LENGTH_SHORT).show()

            }.addOnFailureListener{
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }

        }

    }
}