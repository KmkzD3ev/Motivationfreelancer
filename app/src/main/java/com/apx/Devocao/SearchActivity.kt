package com.apx.Devocao

import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SearchActivity : AppCompatActivity() {

    lateinit var list: RecyclerView
    lateinit var search: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search2)

        search = findViewById(R.id.search)
        var backBtn = findViewById<ImageView>(R.id.backBtn)
        list = findViewById(R.id.list)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = linearLayoutManager

        backBtn.setOnClickListener {
            onBackPressed()
        }

        search.doOnTextChanged { text, start, before, count ->
            getData()
        }

    }

    fun getData() {
        val db = DatabaseFile(this)
        var data = db.getown(search.text.toString())
        list.adapter = OwnAdapter(data)
    }
}