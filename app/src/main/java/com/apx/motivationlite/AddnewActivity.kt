package com.apx.motivationlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import com.apx.motivationlite.Utilities.idgenerater

class AddnewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addnew)

        var NewQuote = findViewById<EditText>(R.id.NewQuote)
        var SaveBtn = findViewById<Button>(R.id.SaveBtn)
        SaveBtn.setOnClickListener {
            val db = DatabaseFile(this)
            db.AddOwnQuotes(idgenerater.getId(), NewQuote.text.toString(), "")
            onBackPressed()
        }

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_in_down)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_down_reverse, R.anim.slide_up_reverse)
    }
}