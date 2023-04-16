package com.apx.motivationlite.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.CollItemActivity
import com.apx.motivationlite.DatabaseFile
import com.apx.motivationlite.Model.CollectionModel
import com.apx.motivationlite.R

class CollAdapter (private val data:List<CollectionModel>):
RecyclerView.Adapter<CollAdapter.ViewHolder>(){
    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        fun bind(list:CollectionModel){
            val title = itemView.findViewById<TextView>(R.id.title)
            val item = itemView.findViewById<TextView>(R.id.item)
            title.setText(list.name)
            val db = DatabaseFile(item.context)
            item.text = "" + db.getcollectionitem(list.id).size + " affirmations"
            itemView.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context,CollItemActivity::class.java).putExtra("id",list.id).putExtra("title", list.name))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemcollection,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}