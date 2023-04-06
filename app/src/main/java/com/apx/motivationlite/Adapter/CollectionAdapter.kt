package com.apx.motivationlite.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.CollectionActivity
import com.apx.motivationlite.DatabaseFile
import com.apx.motivationlite.Model.CategorieModel
import com.apx.motivationlite.Model.CollectionModel
import com.apx.motivationlite.R

class CollectionAdapter (private val data:List<CollectionModel>):
RecyclerView.Adapter<CollectionAdapter.ViewHolder>() {
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind (list: CollectionModel){
            val title = itemView.findViewById<TextView>(R.id.title)
            val item = itemView.findViewById<TextView>(R.id.item)
            title.setText(list.name)

            itemView.setOnClickListener {
                val collection  = DatabaseFile(itemView.context)
//                if (collection.getcollections().contains(CollectionActivity.id)){
                collection.AddCollectionItem(CollectionActivity.id,
                        CollectionActivity.auth, CollectionActivity.title,
                        CollectionActivity.cate, CollectionActivity.lang, list.id)
                        Toast.makeText(itemView.context,"Saved successfully",Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(itemView.context,"Already present in this collection ", Toast.LENGTH_SHORT).show()
//                }

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemcollection,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CollectionAdapter.ViewHolder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}