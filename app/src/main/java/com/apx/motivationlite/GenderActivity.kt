package com.apx.motivationlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class GenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gender)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        val maleBtn = findViewById<Button>(R.id.maleBtn)
        val femaleBtn = findViewById<Button>(R.id.femaleBtn)
        val otherBtn = findViewById<Button>(R.id.otherBtn)
        var sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        var edit = sharedPreferences.edit()

        if (sharedPreferences.getString("USER_GENDER", "").toString().equals("Male")) {
            maleBtn.setBackgroundResource(R.drawable.bg_prefer_selected)
        }else if (sharedPreferences.getString("USER_GENDER", "").toString().equals("Female")){
            femaleBtn.setBackgroundResource(R.drawable.bg_prefer_selected)
        }else if (sharedPreferences.getString("USER_GENDER","").toString().equals("Other"))
            otherBtn.setBackgroundResource(R.drawable.bg_prefer_selected)


        maleBtn.setOnClickListener {
            edit.putString("USER_GENDER","Male")
            edit.apply()
            onBackPressed()
        }

        femaleBtn.setOnClickListener {
            edit.putString("USER_GENDER","Female")
            edit.apply()
            onBackPressed()
        }
        otherBtn.setOnClickListener {
            edit.putString("USER_GENDER","Other")
            edit.apply()
            onBackPressed()
        }

    }
}