package com.apx.Devocao.Adapter

import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.Fragments.Intro4Fragment
import com.apx.Devocao.Model.ThemeModel
import com.apx.Devocao.R
import com.apx.Devocao.Utilities.Constant.Theme_Image
import com.apx.Devocao.Utilities.Constant.Theme_color
import com.apx.Devocao.Utilities.Constant.Theme_font

class ThemeAdapter(private val data:List<ThemeModel>):
RecyclerView.Adapter<ThemeAdapter.ViewHolder>(){
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind (list:ThemeModel){
            val image = itemView.findViewById<ImageView>(R.id.image)
            val check = itemView.findViewById<ImageView>(R.id.check)
            val  text = itemView.findViewById<TextView>(R.id.text)
            image.setImageResource(list.image)
            text.setTextColor(Color.parseColor(list.color))

            if (Intro4Fragment.selectedTitle.equals(list.title)) {
                check.visibility = View.VISIBLE
            } else {
                check.visibility = View.GONE
            }

            var sharedPreferences = itemView.context.getSharedPreferences(itemView.context.packageName,MODE_PRIVATE)
            var edit = sharedPreferences.edit()
            itemView.setOnClickListener {
                edit.putInt("USER_THEME", list.image)
                edit.putString("USER_TITLE", list.title)
                edit.putString("USER_COLOR", list.color)
                edit.putString("USER_FONT", list.font)
                edit.apply()

                Theme_Image = list.image
                Theme_font = list.font
                Theme_color = list.color

                Intro4Fragment.isSelected = true
                Intro4Fragment.selectedTitle = list.title

                Intro4Fragment.getdata()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemtheme,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])

    }

    override fun getItemCount(): Int {
    return data.size

    }

}