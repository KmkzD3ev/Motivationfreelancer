package com.apx.motivationlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Adapter.CollectionItemAdapter

class CollItemActivity : AppCompatActivity() {
    var id: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coll_item)
        id = intent.getStringExtra("id").toString()

        var RecyclerView = findViewById<RecyclerView>(R.id.list)

        val title = findViewById<TextView>(R.id.title)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        title.setText(intent.getStringExtra("title").toString())

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        RecyclerView.layoutManager = layoutManager
        val db = DatabaseFile(this)
        var list = db.getcollectionitem(id)
         RecyclerView.adapter = CollectionItemAdapter(list)

    }
}