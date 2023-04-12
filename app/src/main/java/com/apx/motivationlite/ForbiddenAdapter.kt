package com.apx.motivationlite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForbiddenAdapter ( val data:List<ForbiddenModel>):
RecyclerView.Adapter<ForbiddenAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(list: ForbiddenModel){
            val textView = itemView.findViewById<TextView>(R.id.CSK)
            val imageView = itemView.findViewById<ImageView>(R.id.More)

            val db: DatabaseFile = DatabaseFile(itemView.context)

            textView.text = list.word


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemforbidden,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}