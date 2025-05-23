package com.apx.Devocao

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.apx.Devocao.Adapter.CollAdapter
import com.apx.Devocao.Model.CollectionModel
import com.apx.Devocao.Utilities.Addutilities
import com.apx.Devocao.Utilities.idgenerater

class CollActivity : AppCompatActivity() {
    lateinit var list:RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coll)

        val banner = findViewById<LinearLayout>(R.id.bannerAdView)
        Addutilities.loadMobileAds(this)
        Addutilities.loadBannerAd(this, banner)


        list = findViewById(R.id.list)
        val linearLayoutManager = GridLayoutManager(this,1)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = linearLayoutManager

        getdata()

        var back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        var plusBtn = findViewById<ImageView>(R.id.plusBtn)
        val dialog = Dialog(this)
        plusBtn.setOnClickListener {
            dialog.setContentView(R.layout.dialogadd)
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val cancel = dialog.findViewById<Button>(R.id.Cancel)
            val save = dialog.findViewById<Button>(R.id.Save)
            val collection = dialog.findViewById<EditText>(R.id.collection)
            cancel.setOnClickListener {
                dialog.cancel()

            }
            save.setOnClickListener {
                val db = DatabaseFile(this)
                db.AddCollections(idgenerater.getId(), collection.text.toString())
                getdata()
                dialog.cancel()
            }


            dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.setCancelable(false)

            dialog.show()
        }
    }

    fun getdata(){
        var data = ArrayList<CollectionModel>()
        data.clear()

        var db = DatabaseFile(this)
        data = db.getcollection()
        list.adapter = CollAdapter(data)

    }


}