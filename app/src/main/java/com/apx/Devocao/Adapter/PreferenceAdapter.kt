package com.apx.Devocao.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.DatabaseFile
import com.apx.Devocao.Model.PreferenceModel
import com.apx.Devocao.R

class PreferenceAdapter(private val data:List<PreferenceModel>):
RecyclerView.Adapter<PreferenceAdapter.Viewholder>(){
    class Viewholder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind (list:PreferenceModel) {
            val text: TextView = itemView.findViewById(R.id.Text)
            val linearLayout = itemView.findViewById<LinearLayout>(R.id.background)
            text.text = list.titleString
            var isSelected = false
            var db = DatabaseFile(itemView.context)
            if (db.getpreference().contains(list.titleString)){

                linearLayout.setBackgroundResource(
                    R.drawable.bg_prefer_selected
                )
                isSelected = true

            }else{
                    linearLayout.setBackgroundResource(
                        R.drawable.bg_prefer
                    )
                isSelected = false

            }

            itemView.setOnClickListener {
                if (isSelected){
                    linearLayout.setBackgroundResource(
                        R.drawable.bg_prefer
                    )
                    db.deletePreference(list.titleString)
                    isSelected = false
                }else{
                    linearLayout.setBackgroundResource(
                        R.drawable.bg_prefer_selected
                    )
                    isSelected = true

                    db.AddPreference(list.titleString)

                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PreferenceAdapter.Viewholder {
val view = LayoutInflater.from(parent.context).inflate(R.layout.item_preference,parent,false)
    return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}