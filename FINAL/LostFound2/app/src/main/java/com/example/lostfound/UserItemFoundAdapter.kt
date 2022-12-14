package com.example.lostfound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserItemFoundAdapter(private val claim: ArrayList<FoundItems>): RecyclerView.Adapter<UserItemFoundAdapter.MyViewHolderclaim>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderclaim {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.claim_found_item_card,
            parent,false)
        return UserItemFoundAdapter.MyViewHolderclaim(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolderclaim, position: Int) {
        val currentitem = claim[position]

        holder.name.text = currentitem.name
//        holder.phnnum.text = currentitem.phnnum
//        holder.whofound.text = currentitem.whofound
    }

    override fun getItemCount(): Int {
        return claim.size
    }
    class MyViewHolderclaim(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name)
        val phnnum : TextView = itemView.findViewById(R.id.phnnum)
        val whofound : TextView = itemView.findViewById(R.id.whofound)

    }

}