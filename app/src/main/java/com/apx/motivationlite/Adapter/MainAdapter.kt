package com.apx.motivationlite.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.DatabaseFile
import com.apx.motivationlite.MainModel
import com.apx.motivationlite.R

class MainAdapter( private val data:List<MainModel>):
  RecyclerView.Adapter<MainAdapter.ViewHolder>(){
   class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
       fun bind(list: MainModel){
           val textview = itemView.findViewById<TextView>(R.id.textView)
           val shareBtn = itemView.findViewById<ImageView>(R.id.shareBtn)
           val heartBtn = itemView.findViewById<ImageView>(R.id.heartBtn)
           textview.text = list.title

           val db: DatabaseFile = DatabaseFile(itemView.context)

           var likelist = ArrayList<MainModel>()
           likelist = db.liked
           var isLiked = false

           isLiked = likelist.contains(MainModel(list.id, list.title, list.auth, list.cate, list.lang))
           if (isLiked) {
               heartBtn.setImageResource(R.drawable.heart1)

           } else {
               heartBtn.setImageResource(R.drawable.heart)

           }

           var history = ArrayList<MainModel>()
            history = db.liked
           var ishistory  = false

           ishistory = history.contains(MainModel(list.id, list.title, list.auth, list.cate, list.lang))
//           if (ishistory){
               db.deletehistoy(list.id)
               db.Addhistory(list.id, list.title, list.auth, list.cate, list.lang)
//           }else{
//               db.Addhistory(list.id, list.title, list.auth, list.cate, list.lang)
//           }

            heartBtn.setOnClickListener {
                if (!isLiked) {
                    heartBtn.setImageResource(R.drawable.heart1)
                    db.AddLiked(list.id, list.title, list.auth, list.cate, list.lang)
                    isLiked = true
                } else {
                    heartBtn.setImageResource(R.drawable.heart)
                    db.deleteLiked(list.id)
                    isLiked = false
                }
            }
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemquote,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }
  }

