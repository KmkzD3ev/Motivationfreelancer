package com.apx.Devocao

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.apx.Devocao.Adapter.LikedAdapter
import com.apx.Devocao.Utilities.Addutilities

class LikedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_liked)

        val banner = findViewById<LinearLayout>(R.id.bannerAdView)
        Addutilities.loadMobileAds(this)
        Addutilities.loadBannerAd(this, banner)

        var back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }



        var listRcv = findViewById<RecyclerView>(R.id.listRcv)

        var layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        listRcv.layoutManager = layoutManager
        val db = DatabaseFile(this)
        var list = db.liked
        listRcv.adapter = LikedAdapter (list)

    }

}