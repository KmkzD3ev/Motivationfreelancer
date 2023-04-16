package com.apx.motivationlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Adapter.CategorieAdapter
import com.apx.motivationlite.Adapter.CategorieLayoutAdapter
import com.apx.motivationlite.Fragments.Intro4Fragment
import com.apx.motivationlite.Model.CategorieLayoutModel
import com.apx.motivationlite.Model.CategorieModel
import com.apx.motivationlite.Model.ThemeModel

class CategorieActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categorie)

        val list = findViewById<RecyclerView>(R.id.list)


        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.setLayoutManager(linearLayoutManager)

        var data = ArrayList<CategorieModel>()
        data.clear()

        data.add(CategorieModel(R.drawable.wealth,"Manifest wealth", true))
        data.add(CategorieModel( R.drawable.hard_times,"Overcome hard times",true))
        data.add(CategorieModel( R.drawable.morning,"Start your day",true))
        data.add(CategorieModel( R.drawable.depression,"Fight depression",false))
        data.add(CategorieModel( R.drawable.love_relationships,"Love yourself",false))
        data.add(CategorieModel( R.drawable.favorites,"Cherish Loved Ones",false))
        data.add(CategorieModel(R.drawable.parenting,"Parenting",true))
        data.add(CategorieModel(R.drawable.body_positivity,"Body Positivity",true))

        val _list =  ArrayList<CategorieLayoutModel>()
        _list.add(CategorieLayoutModel("Thought Thinking", data))
        list.adapter = CategorieLayoutAdapter(_list)

        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_in_down)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_down_reverse, R.anim.slide_up_reverse)
    }


}