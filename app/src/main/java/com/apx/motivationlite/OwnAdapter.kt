package com.apx.motivationlite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class OwnAdapter (val data: List<OwnModel>):
RecyclerView.Adapter<OwnAdapter.ViewHolder>() {

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(list:OwnModel){
            val textView = itemView.findViewById<TextView>(R.id.quote)
            val heartBtn = itemView.findViewById<ImageView>(R.id.heartBtn)
            val collectBtn = itemView.findViewById<ImageView>(R.id.collectionBtn)
            val shareBtn = itemView.findViewById<ImageView>(R.id.shareBtn)
            textView.text = list.title

            shareBtn.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context, ShareActivity::class.java)
                    .putExtra("id",  list.id)
                    .putExtra("title", list.title)
                    .putExtra("auth","")
                    .putExtra("cate","")
                    .putExtra("lang","")



                )
            }


            val db: DatabaseFile = DatabaseFile(itemView.context)

            var likedlist = ArrayList<String>()
            likedlist = db.getlike()
            var isliked = true

            isliked = likedlist.contains(list.id)
            if (isliked){
                heartBtn.setImageResource(R.drawable.heart1)
            }else{
                heartBtn.setImageResource(R.drawable.heart)
            }

            heartBtn.setOnClickListener {
                if (!isliked){
                    heartBtn.setImageResource(R.drawable.heart1)
                    db.AddLiked(list.id,list.title,"","","")
                    isliked = true
                }else{
                    heartBtn.setImageResource(R.drawable.heart)
                    db.deleteLiked(list.id)
                    isliked = false
                }
            }

            collectBtn.setOnClickListener {
                itemView.context.startActivity(Intent(itemView.context,CollectionActivity::class.java)
                    .putExtra("id",list.id).putExtra("title", list.title).putExtra("auth","").putExtra("cate","")
                    .putExtra("lang","")
                )
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemfavorite,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size

    }
}