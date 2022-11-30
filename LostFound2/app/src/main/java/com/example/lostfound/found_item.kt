package com.example.lostfound

import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.lostfound.databinding.ActivityFoundItemBinding
import com.example.lostfound.databinding.ActivityLostItemsBinding
import com.google.firebase.database.*

class found_item : AppCompatActivity()
{
    private lateinit var binding : ActivityFoundItemBinding
//    private lateinit var binding : ActivityFoundItemBinding

    private lateinit var dbref: DatabaseReference
    private lateinit var founditemrecycler : RecyclerView
    private lateinit var lostitemlist : ArrayList<LostItems>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_item)
        founditemrecycler = findViewById(R.id.lostitemlist)
        founditemrecycler.layoutManager = LinearLayoutManager(this)
        founditemrecycler.setHasFixedSize(true)
        lostitemlist = arrayListOf<LostItems>()
        getfounditemdata()
        }

    private fun getfounditemdata(){
        dbref = FirebaseDatabase.getInstance().getReference("LostItems")
        dbref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (itemsnapshot in snapshot.children){
                        val item = itemsnapshot.getValue(LostItems::class.java)
                       lostitemlist.add(item !!)
                    }
                    founditemrecycler.adapter = FoundlistAdapter(lostitemlist)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }


}