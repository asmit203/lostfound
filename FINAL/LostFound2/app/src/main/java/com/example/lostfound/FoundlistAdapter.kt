package com.example.lostfound

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class FoundlistAdapter(private val foundlist: ArrayList<LostItems>
                        ): RecyclerView.Adapter<FoundlistAdapter.MyViewHolder>() {
    private lateinit var flistener : onClickListener
    interface onClickListener{
        fun onItemClick(position: Int)
    }

    fun setonclicklistener(listener: onClickListener)
    {
        flistener = listener
    }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int): MyViewHolder {

            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lost_items_card,
                parent,false)
            return FoundlistAdapter.MyViewHolder(itemView,flistener)

        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

            val currentitem = foundlist[position]

            holder.name.text = currentitem.name
            holder.phnnum.text = currentitem.phnnum
            holder.pht_url.text = currentitem.pht_url
            holder.address.text = currentitem.address
//            holder.founditcard.setOnClickListener{
//                clickListener.onClick()
//            }

        }

        override fun getItemCount(): Int {
            return foundlist.size
        }
    class MyViewHolder(itemView: View, listener: onClickListener) : RecyclerView.ViewHolder(itemView){
        val founditcard = itemView.findViewById<CardView>(R.id.founditcard)
        val name : TextView = itemView.findViewById(R.id.name)
        val phnnum : TextView = itemView.findViewById(R.id.phnnum)
        val pht_url : TextView = itemView.findViewById(R.id.pht_url)
        val address : TextView = itemView.findViewById(R.id.address)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(position = adapterPosition)
            }
        }

    }
    }