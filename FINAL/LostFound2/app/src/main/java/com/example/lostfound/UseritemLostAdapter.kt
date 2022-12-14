package com.example.lostfound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class UseritemLostAdapter(private val foundlist: ArrayList<LostItems>
): RecyclerView.Adapter<UseritemLostAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lost_items_card,
            parent,false)
        return UseritemLostAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = foundlist[position]

        holder.name.text = currentitem.name
//        holder.phnnum.text = currentitem.phnnum
//        holder.pht_url.text = currentitem.pht_url
    }

    override fun getItemCount(): Int {
        return foundlist.size
    }
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name)
        val phnnum : TextView = itemView.findViewById(R.id.phnnum)
        val pht_url : TextView = itemView.findViewById(R.id.pht_url)

    }
}