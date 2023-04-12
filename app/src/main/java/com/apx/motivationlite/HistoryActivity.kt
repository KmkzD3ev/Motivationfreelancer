package com.apx.motivationlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Adapter.LikedAdapter

class HistoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        var back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        var listRcv = findViewById<RecyclerView>( R.id.RecView)

        var layoutManager = LinearLayoutManager(this)
         layoutManager.orientation= LinearLayoutManager.VERTICAL
        listRcv.layoutManager = layoutManager
        val db = DatabaseFile(this)
        val list = db.history
        listRcv.adapter = HistoryAdapter (list)



     }
}