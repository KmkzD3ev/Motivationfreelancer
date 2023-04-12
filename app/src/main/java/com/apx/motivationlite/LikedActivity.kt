package com.apx.motivationlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Adapter.CollectionAdapter
import com.apx.motivationlite.Adapter.LikedAdapter
import com.apx.motivationlite.Model.CollectionModel
import com.apx.motivationlite.Utilities.idgenerater
import com.google.android.material.bottomsheet.BottomSheetDialog

class LikedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liked)

        var back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }



        var listRcv = findViewById<RecyclerView>(R.id.listRcv)

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        listRcv.layoutManager = layoutManager
        val db = DatabaseFile(this)
        var list = db.liked
        listRcv.adapter = LikedAdapter (list)
    }

}