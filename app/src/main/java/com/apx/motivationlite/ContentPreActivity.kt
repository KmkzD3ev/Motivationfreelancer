package com.apx.motivationlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Adapter.PreferenceAdapter
import com.apx.motivationlite.Model.PreferenceModel

class ContentPreActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content_pre)

        val list = findViewById<RecyclerView>(R.id.list)

        val linearLayoutManager = GridLayoutManager(this,2)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = linearLayoutManager

        var data = ArrayList<PreferenceModel>()
        data.add(PreferenceModel("Self Care"))
        data.add(PreferenceModel("Personal Growth"))
        data.add(PreferenceModel("Stress and Anxiety"))
        data.add(PreferenceModel("Body Positivity"))
        data.add(PreferenceModel("Positive Thinking"))
        data.add(PreferenceModel("Relationships"))
        data.add(PreferenceModel("Happiness"))
        data.add(PreferenceModel("Gratitude"))

        list.adapter = PreferenceAdapter(data)



    }

}