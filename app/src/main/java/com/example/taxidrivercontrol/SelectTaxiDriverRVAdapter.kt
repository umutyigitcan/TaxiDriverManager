package com.example.taxidrivercontrol

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SelectTaxiDriverRVAdapter(var mContext:Context,var getData:ArrayList<SelectTaxiDriverData>):RecyclerView.Adapter<SelectTaxiDriverRVAdapter.holder>() {
    inner class holder(view:View):RecyclerView.ViewHolder(view){
        var taxiDriverName:TextView
        var chatButton:Button
        init {
            taxiDriverName=view.findViewById(R.id.taxiDriverName)
            chatButton=view.findViewById(R.id.chatButton)
        }
    }

    override fun getItemCount(): Int {
        return getData.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holder {
        var layout=LayoutInflater.from(mContext).inflate(R.layout.selectdrivercardview,parent,false)
        return holder(layout)

    }

    override fun onBindViewHolder(holder: holder, position: Int) {
        var myHolder=getData[position]
        holder.taxiDriverName.text=myHolder.taxiDriverName

    }
}