package com.apx.Devocao

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.Adapter.ThemesLayoutAdapter
import com.apx.Devocao.Model.ThemesLayoutModel
import com.apx.Devocao.Model.ThemesModel

class ThemeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theme2)

        val list = findViewById<RecyclerView>(R.id.list)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }





        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.setLayoutManager(linearLayoutManager)

        val textures = ArrayList<ThemesModel>()
        val sunset = ArrayList<ThemesModel>()
        val abstract = ArrayList<ThemesModel>()
        val cozy = ArrayList<ThemesModel>()
        val illustration = ArrayList<ThemesModel>()
        val people = ArrayList<ThemesModel>()
        val seasonal = ArrayList<ThemesModel>()
     //feito
        textures.add(ThemesModel(R.drawable.cristo_1_, true, false, "Anton-Regular.ttf", "#232D31"))
        textures.add(ThemesModel(R.drawable.cristo_2_,false,false,"BebasNeue-Regular.ttf","#303030"))
        textures.add(ThemesModel(R.drawable.cristo__12_,false, false,"Caveat-VariableFont_wght.ttf","#303030"))
        textures.add(ThemesModel(R.drawable.cristo_10_,false,false,"Caveat-VariableFont_wght.ttf","#303030"))
        textures.add(ThemesModel(R.drawable.cristo_16_,false,false,"ChivoMono-VariableFont_wght.ttf","#F8F9FA"))
        textures.add(ThemesModel(R.drawable.cristo__9_,true,false,"DancingScript-VariableFont_wght.ttf","#010101"))
        textures.add(ThemesModel(R.drawable.cristo_4_,false,false,"DynaPuff-VariableFont_wdth,wght.ttf","#303030"))
        textures.add(ThemesModel(R.drawable.cristo_15_,true,false,"FjallaOne-Regular.ttf","#303030"))
        textures.add(ThemesModel(R.drawable.cristo_17_,false,false,"IBMPlexMono-Medium.ttf","#010101"))
      //feito
        sunset.add(ThemesModel(R.drawable.b__8_,false,false,"IndieFlower-Regular.ttf","#ffffff"))
        sunset.add(ThemesModel(R.drawable.b__3_,true,false,"JosefinSans-VariableFont_wght.ttf","#000000"))
        sunset.add(ThemesModel(R.drawable.b__14_,false,false,"PTSansNarrow-Bold.ttf","#ffffff"))
        sunset.add(ThemesModel(R.drawable.b__7_,false,false,"NovaOval-Regular.ttf","#ffffff"))
        sunset.add(ThemesModel(R.drawable.b__9_,true,false,"Oswald-VariableFont_wght.ttf","#ffffff"))
        sunset.add(ThemesModel(R.drawable.b__6_,false,false,"Pacifico-Regular.ttf","#ffffff"))
        sunset.add(ThemesModel(R.drawable.b__2_,false,false,"PlayfairDisplay-VariableFont_wght.ttf","#ffffff"))
        sunset.add(ThemesModel(R.drawable.b__1_,true,false,"Prompt-Regular.ttf","#ffffff"))
        sunset.add(ThemesModel(R.drawable.b__10_,false,false,"PTSansNarrow-Bold.ttf","#000000"))
          //feito
        abstract.add(ThemesModel(R.drawable.cruz__14_, false, false, "Righteous-Regular.ttf", "#ffffff"))
        abstract.add(ThemesModel(R.drawable.cruz__13_, true, false, "RobotoMono-VariableFont_wght.ttf", "#000000"))
        abstract.add(ThemesModel(R.drawable.cruz__11_, false, false, "Teko-Regular.ttf", "#ffffff"))  // Alterada a fonte para "Satisfy-Regular.ttf"
        abstract.add(ThemesModel(R.drawable.cruz__3_, false, false, "Satisfy-Regular.ttf", "#ffffff"))
        abstract.add(ThemesModel(R.drawable.cruz__10_, true, false, "SedgwickAveDisplay-Regular.ttf", "#000000"))
        abstract.add(ThemesModel(R.drawable.cruz__1_, false, false, "ShadowsIntoLight-Regular.ttf", "#ffffff"))
          //feito
        cozy.add(ThemesModel(R.drawable.leao__17_,false,false,"Teko-Regular.ttf","#000000"))
        cozy.add(ThemesModel(R.drawable.leao__2_,false,false,"DancingScript-VariableFont_wght.ttf","#ffffff"))
        cozy.add(ThemesModel(R.drawable.leao__6_,false,false,"Anton-Regular.ttf","#ffffff"))
        cozy.add(ThemesModel(R.drawable.leao__7_,true,false,"BebasNeue-Regular.ttf","#000000"))
        cozy.add(ThemesModel(R.drawable.leao__11_,false,false,"Caveat-VariableFont_wght.ttf","#ffffff"))
        cozy.add(ThemesModel(R.drawable.leao__6_,false,false,"DancingScript-VariableFont_wght.ttf","#000000"))
        cozy.add(ThemesModel(R.drawable.leao__9_,true,false,"DynaPuff-VariableFont_wdth,wght.ttf","#000000"))
        cozy.add(ThemesModel(R.drawable.leao__15_,false,false,"FjallaOne-Regular.ttf","#ffffff"))
        cozy.add(ThemesModel(R.drawable.leao__16_,false,false,"IBMPlexMono-Medium.ttf","#000000"))

        illustration.add(ThemesModel(R.drawable.grad__18_,false,false,"IndieFlower-Regular.ttf","#000000"))
        illustration.add(ThemesModel(R.drawable.grad__7_,false,false,"JosefinSans-VariableFont_wght.ttf","#000000"))
        illustration.add(ThemesModel(R.drawable.grad__10_,true,false,"Lobster-Regular.ttf","#000000"))
        illustration.add(ThemesModel(R.drawable.grad__14_,false,false,"NovaOval-Regular.ttf","#000000"))
        illustration.add(ThemesModel(R.drawable.grad__15_,false,false,"Oswald-VariableFont_wght.ttf","#000000"))
        illustration.add(ThemesModel(R.drawable.grad__7_,true,false,"Pacifico-Regular.ttf","#000000"))
        illustration.add(ThemesModel(R.drawable.grad__17_,false,false,"PlayfairDisplay-VariableFont_wght.ttf","#ffffff"))
        illustration.add(ThemesModel(R.drawable.grad__16_,true,false,"Prompt-Regular.ttf","#000000"))

        people.add(ThemesModel(R.drawable.pais__15_,false,false,"PTSansNarrow-Bold.ttf","#ffffff"))
        people.add(ThemesModel(R.drawable.pais__14_,true,false,"DancingScript-VariableFont_wght.ttf","#000000"))
        people.add(ThemesModel(R.drawable.pais__7_,false,false,"RobotoMono-VariableFont_wght.ttf","#ffffff"))
        people.add(ThemesModel(R.drawable.pais__4_,false,false,"Prompt-Regular.ttf","#ffffff"))
        people.add(ThemesModel(R.drawable.pais__13_,true,false,"Satisfy-Regular.ttf","#ffffff"))
        people.add(ThemesModel(R.drawable.pais__11_,false,false,"SedgwickAveDisplay-Regular.ttf","#000000"))
        people.add(ThemesModel(R.drawable.pais__3_,true,false,"ShadowsIntoLight-Regular.ttf","#000000"))
        people.add(ThemesModel(R.drawable.pais__12_,false,false,"Teko-Regular.ttf","#000000"))

        seasonal.add(ThemesModel(R.drawable.neutro__13_,false,false,"PlayfairDisplay-VariableFont_wght.ttf","#000000"))
        seasonal.add(ThemesModel(R.drawable.neutro__16_,true,false,"Anton-Regular.ttf","#000000"))
        seasonal.add(ThemesModel(R.drawable.neutro__18_,false,false,"BebasNeue-Regular.ttf","#ffffff"))
        seasonal.add(ThemesModel(R.drawable.neutro__5_,false,false,"Caveat-VariableFont_wght.ttf","#ffffff"))
        seasonal.add(ThemesModel(R.drawable.neutro__6_,false,false,"DancingScript-VariableFont_wght.ttf","#ffffff"))

        val _list =  ArrayList<ThemesLayoutModel>()
        _list.add(ThemesLayoutModel("Biblia", sunset))
        _list.add(ThemesLayoutModel("Cristo", textures))
        _list.add(ThemesLayoutModel("Cruz", abstract))
        _list.add(ThemesLayoutModel("Leão de Judá", cozy))
        _list.add(ThemesLayoutModel("Gradiente", illustration))
        _list.add(ThemesLayoutModel("Paisagem", people))
        _list.add(ThemesLayoutModel("Neutro", seasonal))

        list.adapter = ThemesLayoutAdapter(_list)

        overridePendingTransition(R.anim.slide_in_top, R.anim.slide_in_down)
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_down_reverse, R.anim.slide_up_reverse)
    }
}