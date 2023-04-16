package com.apx.motivationlite

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.apx.motivationlite.Adapter.MainAdapter
import com.apx.motivationlite.Adapter.ThemesLayoutAdapter
import com.apx.motivationlite.Model.ThemesLayoutModel
import com.apx.motivationlite.Model.ThemesModel
import com.apx.motivationlite.Utilities.Addutilities
import com.apx.motivationlite.Utilities.Constant.Theme_Image
import com.apx.motivationlite.Utilities.Constant.isPremium
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var list: ViewPager2
        var categies = ""
        fun setCategory(name: String) {
            var data = ArrayList<MainModel>()
            var db = FirebaseFirestore.getInstance()
            db.collection("qout").whereEqualTo("cate", name).get().addOnSuccessListener {
                    task ->
                for (doc in task) {
                    data.add(MainModel(doc.id, doc.getString("title").toString(),doc.get("lang").toString(), doc.get("title").toString(),doc.get("cate").toString()))
                }
                list.adapter = MainAdapter(data)
            }
        }
    }

    lateinit var background : ImageView

    fun setBackground() {
        background.setBackgroundResource(Theme_Image)
    }

    override fun onResume() {
        super.onResume()
        setCategory(categies)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        background = findViewById(R.id.background)
        list = findViewById(R.id.viewPager2)
        var data = ArrayList<MainModel>()

        val banner = findViewById<LinearLayout>(R.id.bannerAdView)
        Addutilities.loadMobileAds(this)
        Addutilities.loadBannerAd(this, banner)

        var db = FirebaseFirestore.getInstance()

        db.collection("qout").get().addOnSuccessListener {
            task ->
            for (doc in task) {
                data.add(MainModel(doc.id, doc.getString("title").toString(),doc.get("lang").toString(), doc.get("title").toString(),doc.get("cate").toString()))
            }
            list.adapter = MainAdapter(data.shuffled())
        }

        val Categorie = findViewById<LinearLayout>(R.id.linearLayout2)
        Categorie.setOnClickListener {
            startActivity(Intent(this,CategorieActivity::class.java))
        }

        val themesBtn = findViewById<LinearLayout>(R.id.themesBtn)
        themesBtn.setOnClickListener {
            showthemes()
        }
        val person = findViewById<LinearLayout>(R.id.person)
        person.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }

        val purchase = findViewById<LinearLayout>(R.id.purchase)
        if (isPremium)
            purchase.visibility = View.GONE
        purchase.setOnClickListener {
            startActivity(Intent(this, PurchaseActivity::class.java))
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            )
            w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }
        setBackground()
    }

    fun showthemes() {
        val dialog = BottomSheetDialog(this)
        val view = LayoutInflater.from(this).inflate(R.layout.activity_categorie, null)

        val list = view.findViewById<RecyclerView>(R.id.list)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.setLayoutManager(linearLayoutManager)

        var data = ArrayList<ThemesModel>()
        data.clear()

        data.add(ThemesModel(R.drawable.wavessunset_thumbnail, false, false, "cochin", "White"))
        data.add(ThemesModel(R.drawable.starspexels_thumbnail,true,true,"amatic","white"))
        data.add(ThemesModel(R.drawable.blacksky_thumbnail,true,true,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.retroisland_thumbnail,false,false,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.cloudsunset_thumbnail,true,true,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.fuchsiasunset_thumbnail,false,false,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.artroom_thumbnail,true,true,"abrilfatface_regular","white"))
        data.add(ThemesModel(R.drawable.backyard_thumbnail,true,true,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.autumnwindow_thumbnail,false,false,"bangers_regular","white"))
        data.add(ThemesModel(R.drawable.beachsunsetpalm_thumbnail,true,true,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.bigsur_thumbnail,false,false,"anton_regular","white"))
        data.add(ThemesModel(R.drawable.blueeyes_thumbnail,true,true,"avenirnext_bold","white"))


        val _list =  ArrayList<ThemesLayoutModel>()
        _list.add(ThemesLayoutModel("Abstract", data))
        _list.add(ThemesLayoutModel("Abstract", data))
        _list.add(ThemesLayoutModel("Abstract", data))
        _list.add(ThemesLayoutModel("Abstract", data))
        _list.add(ThemesLayoutModel("Abstract", data))
        list.adapter = ThemesLayoutAdapter(_list)

        dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

        dialog.setContentView(view)
        dialog.setOnDismissListener {
            setBackground()
        }


        dialog.show()
    }


}