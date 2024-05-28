package com.apx.Devocao.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.Model.ThemesLayoutModel
import com.apx.Devocao.Model.ThemesModel
import com.apx.Devocao.R


class ThemesLayoutAdapter(private val data:List<ThemesLayoutModel>):
RecyclerView.Adapter<ThemesLayoutAdapter.ViewHolder>(){
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(data: ThemesLayoutModel){
            val title = itemView.findViewById<TextView>(R.id.title)
            title.setText(data.title)

            val list = itemView.findViewById<RecyclerView>(R.id.list)
            val linearLayoutManager = LinearLayoutManager(itemView.context)
            linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            list.setLayoutManager(linearLayoutManager)


            var dat = ArrayList<ThemesModel>()
            dat = data.list

            list.adapter = ThemesAdapter(dat)
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