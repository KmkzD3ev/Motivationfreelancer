package com.apx.Devocao.Fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.Adapter.PreferenceAdapter
import com.apx.Devocao.MainActivity
import com.apx.Devocao.Model.PreferenceModel
import com.apx.Devocao.R


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
        data.add(PreferenceModel("Palavra do Dia"))
        data.add(PreferenceModel("Motivação e Refelxão"))
        data.add(PreferenceModel("Fé e Confiança"))
        data.add(PreferenceModel("Humildade e Perdão"))
        data.add(PreferenceModel("Força e Coragem"))
        data.add(PreferenceModel("Gratidão"))
        data.add(PreferenceModel("Sabedoria"))
        data.add(PreferenceModel("Esperança e Renovação"))

        list.adapter = PreferenceAdapter(data)

        var gotit = view.findViewById<Button>(R.id.gotit)
        gotit.setOnClickListener {
            startActivity(Intent(requireContext(),MainActivity::class.java))

        }

        return view
    }
}
