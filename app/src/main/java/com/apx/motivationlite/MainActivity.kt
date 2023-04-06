package com.apx.motivationlite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.apx.motivationlite.Adapter.CategorieLayoutAdapter
import com.apx.motivationlite.Adapter.MainAdapter
import com.apx.motivationlite.Adapter.ThemesLayoutAdapter
import com.apx.motivationlite.Model.CategorieLayoutModel
import com.apx.motivationlite.Model.CategorieModel
import com.apx.motivationlite.Model.ThemesLayoutModel
import com.apx.motivationlite.Model.ThemesModel
import com.apx.motivationlite.Utilities.Constant.Theme_Image
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var list: ViewPager2
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

    fun setBackground(){
        background.setBackgroundResource(Theme_Image)

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        background = findViewById(R.id.background)
        list = findViewById(R.id.viewPager2)
        var data = ArrayList<MainModel>()

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
            showCategory()
        }

        val themesBtn = findViewById<LinearLayout>(R.id.themesBtn)
        themesBtn.setOnClickListener {
            showthemes()
        }
        val person = findViewById<LinearLayout>(R.id.person)
        person.setOnClickListener {
            startActivity(Intent(this,ProfileActivity::class.java))
        }


        setBackground()
    }

    fun showCategory() {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.activity_categorie, null)

        val list = view.findViewById<RecyclerView>(R.id.list)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.setLayoutManager(linearLayoutManager)

        var data = ArrayList<CategorieModel>()
        data.clear()

        data.add(CategorieModel(R.drawable.wealth,"Mainfest wealth", true))
        data.add(CategorieModel( R.drawable.hard_times,"Overcome hard times",true))
        data.add(CategorieModel( R.drawable.morning,"Start your day",true))
        data.add(CategorieModel( R.drawable.depression,"Fight depression",false))
        data.add(CategorieModel( R.drawable.love_relationships,"Love yourself",false))
        data.add(CategorieModel( R.drawable.favorites,"Cherish Loved Ones",false))
        data.add(CategorieModel(R.drawable.parenting,"Parenting",true))
        data.add(CategorieModel(R.drawable.body_positivity,"Body Positivty",true))

        val _list =  ArrayList<CategorieLayoutModel>()
        _list.add(CategorieLayoutModel("Thought Thinking", data))
        list.adapter = CategorieLayoutAdapter(_list)
        dialog.setContentView(view)

        dialog.show()
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


        dialog.setContentView(view)
        dialog.setOnDismissListener {
            setBackground()
        }
        dialog.show()
    }


}