package com.apx.motivationlite.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Model.PreferenceModel
import com.apx.motivationlite.R
import org.w3c.dom.Text

class PreferenceAdapter(private val data:List<PreferenceModel>):
RecyclerView.Adapter<PreferenceAdapter.Viewholder>(){
    class Viewholder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind (list:PreferenceModel) {
            val text: TextView = itemView.findViewById(R.id.Text)
            val linearLayout = itemView.findViewById<LinearLayout>(R.id.background)
            text.text = list.titleString
            var isSelected = false
            itemView.setOnClickListener {
                if (isSelected){
                    linearLayout.setBackgroundResource(
                        R.drawable.bg_prefer
                    )
                    isSelected = false
                }else{
                    linearLayout.setBackgroundResource(
                        R.drawable.bg_prefer_selected
                    )
                    isSelected = true
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