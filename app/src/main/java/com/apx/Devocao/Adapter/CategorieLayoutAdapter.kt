package com.apx.Devocao.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.apx.Devocao.CategorieActivity
import com.apx.Devocao.Model.CategorieLayoutModel
import com.apx.Devocao.Model.CategorieModel
import com.apx.Devocao.R

class CategorieLayoutAdapter(private val data:List<CategorieLayoutModel>, private val activity: CategorieActivity):
    RecyclerView.Adapter<CategorieLayoutAdapter.ViewHolder>() {
    inner class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(data: CategorieLayoutModel){
            val title = itemView.findViewById<TextView>(R.id.title)
            title.setText(data.title)

            var dat = ArrayList<CategorieModel>()
            dat = data.list

            val list = itemView.findViewById<RecyclerView>(R.id.list)
            val linearLayoutManager = GridLayoutManager(itemView.context, 1)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            list.setLayoutManager(linearLayoutManager)

            list.adapter = CategorieAdapter(dat, activity)
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
