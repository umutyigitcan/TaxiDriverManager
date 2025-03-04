package com.example.taxidrivercontrol

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DeleteTaxiDriverRVAdapter(var mContext:Context,var getList:ArrayList<SQLData>):RecyclerView.Adapter<DeleteTaxiDriverRVAdapter.holderCardView>() {
    inner class holderCardView(view:View):RecyclerView.ViewHolder(view){
        var taxiDriverName:TextView
        var deleteButton:TextView
        init{
            taxiDriverName=view.findViewById(R.id.taxiDriverName)
            deleteButton=view.findViewById(R.id.deleteButton)
        }
    }

    override fun getItemCount(): Int {
        return getList.size
    }

    override fun onBindViewHolder(holder: holderCardView, position: Int) {
        var myHolder=getList[position]
        holder.taxiDriverName.text=myHolder.userName

        holder.deleteButton.setOnClickListener {

            var database=FirebaseDatabase.getInstance()
            var Users=database.getReference("Users")
            Users.addValueEventListener(object :ValueEventListener{
                override fun onDataChange(ds: DataSnapshot) {

                    for(p in ds.children){
                        var kisi=p.getValue(UserData::class.java)
                        if(kisi!=null){
                            var key=p.key
                            if(kisi.userName==myHolder.userName){
                                Users.child(p.key.toString()).removeValue()
                            }

                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {

                }

            })

        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): holderCardView {
        var layout=LayoutInflater.from(mContext).inflate(R.layout.deletetaxidrivercardview,parent,false)
        return holderCardView(layout)
    }
}