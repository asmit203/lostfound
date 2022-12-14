package com.example.lostfound

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.lostfound.databinding.ActivityProfilePageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class ProfilePage : AppCompatActivity() {
    private lateinit var binding: ActivityProfilePageBinding
    private lateinit var dbref: DatabaseReference
    private lateinit var cdbref: DatabaseReference
    private lateinit var fdbref: DatabaseReference
    private lateinit var mAuth : FirebaseAuth
    private lateinit var claimitemrecycleruser: RecyclerView
    private lateinit var claimitemlist: ArrayList<FoundItems>
    private lateinit var founditemrecycleruser : RecyclerView
    private lateinit var lostitemlist : ArrayList<LostItems>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfilePageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        dbref = FirebaseDatabase.getInstance().getReference("User")
        fdbref = FirebaseDatabase.getInstance().getReference("LostItems")
        cdbref = FirebaseDatabase.getInstance().getReference("FoundItems")


        claimitemrecycleruser = findViewById(R.id.useritemfound)
        claimitemrecycleruser.layoutManager = LinearLayoutManager(this)
        claimitemrecycleruser.setHasFixedSize(true)
        claimitemlist = arrayListOf<FoundItems>()

        founditemrecycleruser = findViewById(R.id.useritemlost)
        founditemrecycleruser.layoutManager = LinearLayoutManager(this)
        founditemrecycleruser.setHasFixedSize(true)
        lostitemlist = arrayListOf<LostItems>()

        binding.email.text = mAuth.currentUser?.email.toString()

        dbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (itemsnapshot in snapshot.children){
                        val item = itemsnapshot.getValue(User::class.java)
                        if (item?.UID == mAuth.uid.toString()){
                            binding.name.text = item.name.toString()
                            binding.phonenum.text = item.phonenum.toString()
                            binding.rollnum.text = item.rollnum.toString()
                        }
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
        getfounditemuserdata()
        getlostitemuserdata()

    }

    private fun getlostitemuserdata() {
        fdbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (itemsnapshot in snapshot.children){
                        val item = itemsnapshot.getValue(LostItems::class.java)
                        if(item!!.email.toString()==mAuth.currentUser!!.email.toString()) {
                            lostitemlist.add(item!!)
                        }
                        }
                    var adapter = UseritemLostAdapter(lostitemlist)
                    founditemrecycleruser.adapter = adapter
                    }
                }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        }


    private fun getfounditemuserdata() {
        cdbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (itemsnapshot in snapshot.children) {
                        val item = itemsnapshot.getValue(FoundItems::class.java)
                        if(item!!.email.toString()==mAuth.currentUser!!.email.toString()) {
                            claimitemlist.add(item!!)
                        }
                    }
                    var adptcl = UserItemFoundAdapter(claimitemlist)
                    claimitemrecycleruser.adapter = adptcl
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })


    }


}
