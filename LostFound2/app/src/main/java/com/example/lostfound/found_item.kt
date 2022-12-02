package com.example.lostfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.databinding.ActivityLostItemsBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class found_item : AppCompatActivity(),FoundlistAdapter.onClickListener
{
//    private lateinit var binding : ActivityFoundItemBinding
    private lateinit var binding : ActivityLostItemsBinding
    private lateinit var foundit_card : CardView
    private lateinit var dbref: DatabaseReference
    private lateinit var newdbref: DatabaseReference
    private lateinit var userdb : DatabaseReference
    private lateinit var mAuth: FirebaseAuth
    private lateinit var founditemrecycler : RecyclerView
    private lateinit var lostitemlist : ArrayList<LostItems>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_found_item)
        mAuth = FirebaseAuth.getInstance()
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
                    var adapter = FoundlistAdapter(lostitemlist)
                    founditemrecycler.adapter = adapter
                    adapter.setonclicklistener(object : FoundlistAdapter.onClickListener {
                        override fun onItemClick(position: Int) {
                            val item_del = lostitemlist[position].name.toString()
//                            Toast.makeText(this@found_item,"$item_del",Toast.LENGTH_SHORT).show()
                            change_place(item_del,lostitemlist[position])
                        }
                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun change_place(itemDel: String, node : LostItems) {
        userdb = FirebaseDatabase.getInstance().getReference("User")
        val name = node.name.toString()
        val date = node.date.toString()
        val phnnum = node.phnnum.toString()
        val address = node.address.toString()
        val email = node.email.toString()
        val pht_url = node.pht_url.toString()
        val whofound = mAuth.currentUser?.email.toString()

        newdbref = FirebaseDatabase.getInstance().getReference("FoundItems")
        val fitem = FoundItems(name, date, phnnum, address, email, pht_url,whofound)
        newdbref.child(itemDel).setValue(fitem).addOnSuccessListener {
            Toast.makeText(this,"successfully found", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
        }
        dbref.child(itemDel).removeValue()
    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }


}