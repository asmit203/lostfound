package com.example.lostfound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ClaimListAdapter(private val claim: ArrayList<FoundItems>): RecyclerView.Adapter<ClaimListAdapter.MyViewHolderclaim>()
{
    private lateinit var clistener : onClickListener
    interface onClickListener{
        fun onItemClick(position: Int)
    }

    fun setonclicklistener(listener: onClickListener)
    {
        clistener = listener
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClaimListAdapter.MyViewHolderclaim{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.claim_found_item_card,
            parent,false)
        return ClaimListAdapter.MyViewHolderclaim(itemView, clistener)
    }

    override fun onBindViewHolder(holder: ClaimListAdapter.MyViewHolderclaim, position: Int) {
        val currentitem = claim[position]

        holder.name.text = currentitem.name
        holder.phnnum.text = currentitem.phnnum
        holder.whofound.text = currentitem.whofound
//        holder.pht_url.text = currentitem.pht_url

//            holder.founditcard.setOnClickListener{
//                clickListener.onClick()
//            }
    }

    override fun getItemCount(): Int {
        return claim.size
    }
    class MyViewHolderclaim(itemView : View, listener: onClickListener) : RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.name)
        val phnnum : TextView = itemView.findViewById(R.id.phnnum)
        val whofound : TextView = itemView.findViewById(R.id.whofound)
//        val cardviewclaim = itemView.findViewById<CardView>(R.id.cardclaim)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(position = adapterPosition)
            }
        }

    }


}
