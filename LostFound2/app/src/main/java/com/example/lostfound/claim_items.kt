package com.example.lostfound

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.databinding.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class claim_items : AppCompatActivity(), ClaimListAdapter.onClickListener {
    private lateinit var binding: ActivityFoundItemBinding
    private lateinit var dbref: DatabaseReference
    private lateinit var mAuth : FirebaseAuth
    private lateinit var claimitemrecycle: RecyclerView
    private lateinit var claimitemlist: ArrayList<FoundItems>


    override fun onCreate(savedInstanceState: Bundle?) {
        mAuth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_claim_found_item)
        claimitemrecycle = findViewById(R.id.claimlist)
        claimitemrecycle.layoutManager = LinearLayoutManager(this)
        claimitemrecycle.setHasFixedSize(true)
        claimitemlist = arrayListOf<FoundItems>()
        getfounditemdata()
    }

    private fun getfounditemdata() {
        dbref = FirebaseDatabase.getInstance().getReference("FoundItems")
        dbref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (itemsnapshot in snapshot.children) {
                        val item = itemsnapshot.getValue(FoundItems::class.java)
                        claimitemlist.add(item!!)
                    }
                    var adapter = ClaimListAdapter(claimitemlist)
                    claimitemrecycle.adapter = adapter
                    adapter.setonclicklistener(object : ClaimListAdapter.onClickListener {
                        override fun onItemClick(position: Int) {
                            val item_del = claimitemlist[position].name.toString()
//                            Toast.makeText(this@claim_items, "$item_del", Toast.LENGTH_SHORT).show()
                            delteitem(item_del,claimitemlist[position])
                        }

                    })
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


//        database = FirebaseDatabase.getInstance().getReference("LostItems")
//        val FoundItem = FoundItems(name, date, phnnum, address, email, pht_url)

//        database.child(name).setValue(FoundItem).addOnSuccessListener {
//
//                Toast.makeText(this,"successfully saved", Toast.LENGTH_SHORT).show()
//            }.addOnFailureListener{
//                Toast.makeText(this,"Failed", Toast.LENGTH_SHORT).show()
//            }


    }

    private fun delteitem(itemDel: String, item: FoundItems) {
        if (item.email.toString() == mAuth.currentUser?.email.toString()) {
            dbref.child(itemDel).removeValue()
            Toast.makeText(this,"Thanks for using our services !",Toast.LENGTH_SHORT).show()
        }
        else
            Toast.makeText(this,"You are not owner",Toast.LENGTH_SHORT).show()

    }

    override fun onItemClick(position: Int) {

    }
}

//class ActivityClaimItemBinding(layoutInflater: LayoutInflater) {
//
//    val root: Int
//}
