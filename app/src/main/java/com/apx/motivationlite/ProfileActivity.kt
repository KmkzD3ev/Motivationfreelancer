package com.apx.motivationlite

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.apx.motivationlite.Utilities.Addutilities
import com.apx.motivationlite.Utilities.Constant
import com.google.api.ResourceDescriptor.History

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        val banner = findViewById<LinearLayout>(R.id.bannerAdView)
        Addutilities.loadMobileAds(this)
        Addutilities.loadBannerAd(this, banner)
        val purchase = findViewById<LinearLayout>(R.id.purchase)
        if (Constant.isPremium)
            purchase.visibility = View.GONE
        purchase.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }

        val fav = findViewById<LinearLayout>(R.id.fav)
        fav.setOnClickListener {
            startActivity(Intent(this,LikedActivity::class.java))
        }


        val widget = findViewById<LinearLayout>(R.id.widget)
        widget.setOnClickListener {
            startActivity(Intent(this, WidgetsActivity::class.java))
        }

        val History = findViewById<LinearLayout>(R.id.pastAff)
        History .setOnClickListener {
            startActivity(Intent(this,HistoryActivity::class.java))
        }
        val OwnBtn = findViewById<LinearLayout>(R.id.OwnBtn)
        OwnBtn.setOnClickListener {
            startActivity(Intent(this,OwnActivity::class.java))
        }
        val collActivity = findViewById<LinearLayout>(R.id.collection)
        collActivity.setOnClickListener {
            startActivity(Intent(this,CollActivity::class.java))
        }
        val General = findViewById<LinearLayout>(R.id.General)
        General .setOnClickListener {
            startActivity(Intent(this,GeneralActivity::class.java))
        }

        val reminder = findViewById<LinearLayout>(R.id.reminder)
        reminder.setOnClickListener {
            startActivity(Intent(this,RemidersActivity::class.java))
        }

        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_in_down)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_down_reverse, R.anim.slide_up_reverse)
    }


}