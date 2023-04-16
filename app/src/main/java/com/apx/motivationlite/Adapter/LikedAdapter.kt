package com.apx.motivationlite.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.*
import com.google.android.material.bottomsheet.BottomSheetDialog

class LikedAdapter (val data:List<MainModel>):
RecyclerView.Adapter<LikedAdapter.ViewHolder>() {

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
    fun  bind(list: MainModel){
        val textView = itemView.findViewById<TextView>(R.id.quote)
        val heartBtn = itemView.findViewById<ImageView>(R.id.heartBtn)
        val collectionBtn = itemView.findViewById<ImageView>(R.id.collectionBtn)
        val shareBtn = itemView.findViewById<ImageView>(R.id.shareBtn)
        textView.text = list.title

        shareBtn.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context, ShareActivity::class.java)
                .putExtra("id",  list.id)
                .putExtra("title", list.title)
                .putExtra("auth",list.auth)
                .putExtra("cate",list.cate)
                .putExtra("lang",list.lang)
            )
        }



        val db: DatabaseFile = DatabaseFile(itemView.context)

        var likedlist = ArrayList<String>()
        likedlist = db.getlike()
        var isLiked = true

        isLiked = likedlist.contains(list.id)
        if (isLiked){
            heartBtn.setImageResource(R.drawable.heart1)
        }else{
            heartBtn.setImageResource(R.drawable.heart)
        }


        heartBtn.setOnClickListener {
            if (!isLiked){

                heartBtn.setImageResource(R.drawable.heart1)
                db.AddLiked(list.id, list.title, list.auth, list.cate, list.lang)
                isLiked = true
            }else{

                heartBtn.setImageResource(R.drawable.heart)
                db.deleteLiked(list.id)
                isLiked = false
            }
        }

        collectionBtn.setOnClickListener {
            itemView.context.startActivity(Intent(itemView.context,CollectionActivity::class.java)
                .putExtra("id",list.id).putExtra("title",list.title).putExtra("auth",list.auth).putExtra("cate",list.cate).putExtra("lang",list.lang))


        }

    }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =       LayoutInflater.from(parent.context).inflate(R.layout.itemfavorite,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size

    }
}