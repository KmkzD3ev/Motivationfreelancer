package com.apx.Devocao

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.Adapter.PreferenceAdapter
import com.apx.Devocao.Model.PreferenceModel

class ContentPreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_pre)

        val list = findViewById<RecyclerView>(R.id.list)

        val linearLayoutManager = GridLayoutManager(this,2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = linearLayoutManager

        var back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        var data = ArrayList<PreferenceModel>()
        data.add(PreferenceModel("Palavra do dia"))
        data.add(PreferenceModel("Reflexao e Motivacao"))
        data.add(PreferenceModel("Humildade e Perdao"))
        data.add(PreferenceModel("Forca e Coragem"))
        data.add(PreferenceModel("Gratidao"))
        data.add(PreferenceModel("Fe e Confianca"))
        data.add(PreferenceModel("Gratidao"))
        data.add(PreferenceModel("Sabedoria"))

        list.adapter = PreferenceAdapter(data)

    }


}