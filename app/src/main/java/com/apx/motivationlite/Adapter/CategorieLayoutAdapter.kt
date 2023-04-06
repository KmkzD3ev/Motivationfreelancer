package com.apx.motivationlite.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Model.CategorieLayoutModel
import com.apx.motivationlite.Model.CategorieModel
import com.apx.motivationlite.R

class CategorieLayoutAdapter(private val data:List<CategorieLayoutModel>):
RecyclerView.Adapter<CategorieLayoutAdapter.ViewHolder>(){
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(data: CategorieLayoutModel){
            val title = itemView.findViewById<TextView>(R.id.title)
            title.setText(data.title)

            val list = itemView.findViewById<RecyclerView>(R.id.list)
            val linearLayoutManager = LinearLayoutManager(itemView.context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            list.setLayoutManager(linearLayoutManager)

            var dat = ArrayList<CategorieModel>()
            dat = data.list

            list.adapter = CategorieAdapter(dat)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layoutcategorie, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}