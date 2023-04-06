package com.apx.motivationlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.google.api.ResourceDescriptor.History

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val fav = findViewById<LinearLayout>(R.id.fav)
        fav.setOnClickListener {
            startActivity(Intent(this,LikedActivity::class.java))
        }

        val History = findViewById<LinearLayout>(R.id.pastAff)
        History .setOnClickListener {
            startActivity(Intent(this,HistoryActivity::class.java))
        }
        val OwnBtn = findViewById<LinearLayout>(R.id.OwnBtn)
        OwnBtn.setOnClickListener {
            startActivity(Intent(this,OwnActivity::class.java))
        }

    }
}