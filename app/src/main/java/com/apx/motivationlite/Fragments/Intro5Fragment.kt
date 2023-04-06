package com.apx.motivationlite.Fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Adapter.PreferenceAdapter
import com.apx.motivationlite.MainActivity
import com.apx.motivationlite.Model.PreferenceModel
import com.apx.motivationlite.R


class Intro5Fragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_intro5, container, false)
        val list = view.findViewById<RecyclerView>(R.id.List)

        val linearLayoutManage = GridLayoutManager(context,2)
        linearLayoutManage.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = linearLayoutManage

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

        var gotit = view.findViewById<Button>(R.id.gotit)
        gotit.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))

        }

        return view
    }
}
