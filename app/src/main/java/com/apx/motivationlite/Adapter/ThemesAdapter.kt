package com.apx.motivationlite.Adapter

import android.content.Context.MODE_PRIVATE
import android.content.res.Resources.Theme
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Fragments.Intro4Fragment
import com.apx.motivationlite.Model.ThemeModel
import com.apx.motivationlite.Model.ThemesModel
import com.apx.motivationlite.R
import com.apx.motivationlite.Utilities.Constant.*

class ThemesAdapter(private val data:List<ThemesModel>):
RecyclerView.Adapter<ThemesAdapter.ViewHolder>(){
    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind (list: ThemesModel){
            val image = itemView.findViewById<ImageView>(R.id.image)
            val check = itemView.findViewById<ImageView>(R.id.check)
            val  text = itemView.findViewById<TextView>(R.id.text)
            val lock = itemView.findViewById<ImageView>(R.id.lock)
            val Ads = itemView.findViewById<ImageView>(R.id.Ads)
            image.setImageResource(list.image)
            text.setTextColor(Color.parseColor(list.color))

            if (list.isAds){
                Ads.visibility = View.VISIBLE
            }else {
                Ads.visibility = View.GONE
            }

            if (list.isLocked){
                lock.visibility = View.VISIBLE
            }else{
                lock.visibility = View.GONE
            }


            var sharedPreferences = itemView.context.getSharedPreferences(itemView.context.packageName,MODE_PRIVATE)
            var edit = sharedPreferences.edit()
            itemView.setOnClickListener {
                edit.putInt("USER_THEME", list.image)
                edit.putString("USER_COLOR", list.color)
                edit.putString("USER_FONT", list.font)
                edit.apply()

                Theme_Image = list.image
                Theme_color = list.color
                Theme_font = list.font

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemthemes, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])

    }

    override fun getItemCount(): Int {
    return data.size

    }

}