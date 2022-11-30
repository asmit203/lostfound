package com.example.lostfound

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lostfound.databinding.ActivityFoundItemBinding
import com.example.lostfound.databinding.ActivityLostItemsBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class claim_items : AppCompatActivity(){
    private lateinit var binding : ActivityFoundItemBinding
    private lateinit var bdlst : ActivityLostItemsBinding
    private lateinit var database: DatabaseReference
    //    database = Firebase.database.reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoundItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = bdlst.itemname.text.toString()
        val date = bdlst.editTextDate.text.toString()
        val phnnum = bdlst.editTextPhone.text.toString()
        val address = bdlst.editTextTextPostalAddress.text.toString()
        val email = bdlst.editTextTextEmailAddress.text.toString()
        val pht_url = bdlst.photourl.text.toString()

        database = FirebaseDatabase.getInstance().getReference("LostItems")
        val FoundItem = FoundItems(name, date, phnnum, address, email, pht_url)

        database.child(name).setValue(FoundItem).addOnSuccessListener {

                Toast.makeText(this,"successfully saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
            }



    }
}