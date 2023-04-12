package com.apx.motivationlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class OwnActivity : AppCompatActivity() {
    lateinit var RecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_own)
        RecyclerView = findViewById(R.id.RecView)

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        RecyclerView.layoutManager = layoutManager

        var back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        var AddBtn = findViewById<Button>(R.id.AddBtn)
        AddBtn.setOnClickListener {
            startActivity(Intent(this, AddnewActivity::class.java))



        }
        getData()
    }

    fun getData() {
        val db = DatabaseFile(this)
        var list = db.getown()
        RecyclerView.adapter = OwnAdapter (list)

    }

    override fun onResume() {
        super.onResume()
        getData()
    }

}