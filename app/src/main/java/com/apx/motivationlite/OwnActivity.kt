package com.apx.motivationlite

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Utilities.Addutilities

class OwnActivity : AppCompatActivity() {
    lateinit var RecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_own)
        RecyclerView = findViewById(R.id.RecView)


        val banner = findViewById<LinearLayout>(R.id.bannerAdView)
        Addutilities.loadMobileAds(this)
        Addutilities.loadBannerAd(this, banner)

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        RecyclerView.layoutManager = layoutManager

        var search  =  findViewById<ImageView>(R.id.search)
        search.setOnClickListener{
            startActivity(Intent(this, SearchActivity::class.java))
        }

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