package com.apx.Devocao.Adapter

import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.CategorieActivity
import com.apx.Devocao.MainActivity.Companion.categies
import com.apx.Devocao.MainActivity.Companion.isCategory
import com.apx.Devocao.Model.CategorieModel
import com.apx.Devocao.PurchaseActivity
import com.apx.Devocao.R


class CategorieAdapter(private val data:List<CategorieModel>, private val activity: CategorieActivity):
    RecyclerView.Adapter<CategorieAdapter.ViewHolder>(){
    class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(list: CategorieModel, activity: CategorieActivity){
            val image = itemView.findViewById<ImageView>(R.id.image)
            val title = itemView.findViewById<TextView>(R.id.title)
            val lock = itemView.findViewById<ImageView>(R.id.lock)
            image.setImageResource(list.image)
            title.setText(list.title)
            if (list.premium){
                lock.visibility = View.VISIBLE
            }else{
                lock.visibility = View.GONE
            }
            itemView.setOnClickListener {
                if (list.premium){
                    itemView.context.startActivity(Intent(itemView.context,PurchaseActivity::class.java))
                } else {
                    categies = list.title
                    isCategory = true
                    Log.d(TAG, "Item clicado: ${list.title}")
                    activity.consultarFrases(listOf(list.title))

                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemcategorie, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position], activity)
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
