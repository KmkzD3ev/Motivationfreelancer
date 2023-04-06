package com.apx.motivationlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class OwnActivity : AppCompatActivity() {

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_own)

        var AddBtn = findViewById<Button>(R.id.AddBtn)
        AddBtn.setOnClickListener {
            startActivity(Intent(this,AddnewActivity::class.java))

        }
    }

}