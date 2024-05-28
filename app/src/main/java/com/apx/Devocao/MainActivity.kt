package com.apx.Devocao

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.apx.Devocao.Adapter.MainAdapter
import com.apx.Devocao.Adapter.ThemesLayoutAdapter
import com.apx.Devocao.Model.ThemesLayoutModel
import com.apx.Devocao.Model.ThemesModel
import com.apx.Devocao.Utilities.Addutilities
import com.apx.Devocao.Utilities.Constant.Theme_Image
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.firestore.FirebaseFirestore
import com.onesignal.OneSignal
import com.onesignal.debug.LogLevel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    val ONESIGNAL_APP_ID = "9d90c268-b01b-44b3-bd36-0438f9df326a"


    companion object {
        lateinit var list: ViewPager2
        var categies = ""
        var isCategory = false
        fun setCategory(name: String) {
            var data = ArrayList<MainModel>()
            var db = FirebaseFirestore.getInstance()
            if (categies != "") {
                db.collection("Frases").get().addOnSuccessListener { task ->
                    for (doc in task) {
                        val category = doc.getString("cat").toString()
                        if (category == name) { // Verifica se a frase pertence à categoria selecionada
                            data.add(
                                MainModel(
                                    doc.id,
                                    doc.getString("title").toString(),
                                    doc.get("lag").toString(),
                                    doc.get("title").toString(),
                                    category
                                )
                            )
                        }
                    }
                    list.adapter = MainAdapter(data.shuffled())
                    Log.d(TAG, "Consulta ao banco de dados concluída com sucesso. Frases da categoria 25 $categies: ${data.size}")
                    // Imprime o número de frases recebidas para a categoria específica
                }
            } else {
                db.collection("Frases").whereEqualTo("cat", categies).get()
                    .addOnSuccessListener { task ->
                        for (doc in task) {
                            data.add(
                                MainModel(
                                    doc.id,
                                    doc.getString("title").toString(),
                                    doc.get("lag").toString(),
                                    doc.get("title").toString(),
                                    doc.getString("cat").toString()
                                )
                            )
                        }
                        Log.d(TAG, "Dados carregados e adaptador atualizado para a categoria $name: ${data.size}")
                        list.adapter = MainAdapter(data)
                    }
            }
        }

    }
    

    lateinit var background : ImageView

    fun setBackground() {
        background.setBackgroundResource(Theme_Image)
    }

    override fun onResume() {
        super.onResume()
        if (isCategory) {
            setCategory(categies)
            isCategory = false
        }


        setBackground()
        list.adapter?.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "Activity criada")



        background = findViewById(R.id.background)
        list = findViewById(R.id.viewPager2)
        var data = ArrayList<MainModel>()

        val banner = findViewById<LinearLayout>(R.id.bannerAdView)
        Addutilities.loadMobileAds(this)
        Addutilities.loadBannerAd(this, banner)

        var db = FirebaseFirestore.getInstance()

        db.collection("Frases").get().addOnSuccessListener {
            task ->
            for (doc in task) {
                data.add(MainModel(doc.id, doc.getString("title").toString(),doc.get("lag").toString(), doc.get("title").toString(),doc.get("cat").toString()))
            }
            list.adapter = MainAdapter(data.shuffled())
            Log.d(TAG, "onCreate: Dados criados:${data}")
        }

        val Categorie = findViewById<LinearLayout>(R.id.linearLayout2)
        Categorie.setOnClickListener {
            startActivity(Intent(this, CategorieActivity::class.java))
        }

        val themesBtn = findViewById<LinearLayout>(R.id.themesBtn)
        themesBtn.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }
        val person = findViewById<LinearLayout>(R.id.person)
        person.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
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

        OneSignal.Debug.logLevel = LogLevel.VERBOSE
        OneSignal.initWithContext(this, ONESIGNAL_APP_ID)


        CoroutineScope(Dispatchers.IO).launch {
            OneSignal.Notifications.requestPermission(true)
        }
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
        data.add(ThemesModel(R.drawable.starspexels_thumbnail,false,false,"amatic","white"))
        data.add(ThemesModel(R.drawable.blacksky_thumbnail,true,false,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.retroisland_thumbnail,false,false,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.cloudsunset_thumbnail,true,false,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.fuchsiasunset_thumbnail,false,false,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.artroom_thumbnail,true,false,"abrilfatface_regular","white"))
        data.add(ThemesModel(R.drawable.backyard_thumbnail,true,false,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.autumnwindow_thumbnail,false,false,"bangers_regular","white"))
        data.add(ThemesModel(R.drawable.beachsunsetpalm_thumbnail,true,false,"bodonimoda_italic","white"))
        data.add(ThemesModel(R.drawable.bigsur_thumbnail,false,false,"anton_regular","white"))
        data.add(ThemesModel(R.drawable.blueeyes_thumbnail,true,false,"avenirnext_bold","white"))


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