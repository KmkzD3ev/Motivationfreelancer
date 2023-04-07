package com.apx.motivationlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout

class GeneralActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)

        var Preference = findViewById<LinearLayout>(R.id.Preference)
        Preference.setOnClickListener {
            startActivity(Intent(this,ContentPreActivity::class.java))

        }
    }
}