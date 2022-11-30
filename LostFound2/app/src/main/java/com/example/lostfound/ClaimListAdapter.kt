package com.example.lostfound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClaimListAdapter(private val claim: ArrayList<FoundItems>): RecyclerView.Adapter<ClaimListAdapter.MyViewHolderclaim>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClaimListAdapter.MyViewHolderclaim {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.claim_found_item_card,
            parent,false)
        return ClaimListAdapter.MyViewHolderclaim(itemView)
    }

    override fun onBindViewHolder(holder: ClaimListAdapter.MyViewHolderclaim, position: Int) {
        val currentitem = claim[position]
        holder.name.text = currentitem.name
        holder.phnnum.text = currentitem.phnnum
        holder.pht_url.text = currentitem.pht_url
//            holder.founditcard.setOnClickListener{
//                clickListener.onClick()
//            }
    }

    override fun getItemCount(): Int {
        return claim.size
    }
    class MyViewHolderclaim(itemView : View) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name)
        val phnnum : TextView = itemView.findViewById(R.id.phnnum)
        val pht_url : TextView = itemView.findViewById(R.id.pht_url)

    }

}