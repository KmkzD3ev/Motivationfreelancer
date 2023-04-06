package com.apx.motivationlite.Fragments

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Adapter.ThemeAdapter
import com.apx.motivationlite.Model.ThemeModel
import com.apx.motivationlite.R


class Intro4Fragment : Fragment() {

    companion object {
        lateinit var list: RecyclerView
        var isSelected: Boolean = false
        var selectedTitle: String = ""

        fun getdata () {
            var data = ArrayList<ThemeModel>()
            data.clear()
            data.add(ThemeModel(R.drawable.blacksky_thumbnail, "Black Sky", "", "#ffffff"))
            data.add(ThemeModel(R.drawable.cloudsunset_thumbnail,"cloud","","#ffffff"))
            data.add(ThemeModel(R.drawable.fuchsiasunset_thumbnail,"thumbnail","","#ffffff"))
            data.add(ThemeModel(R.drawable.retroisland_thumbnail,"retroisland","","#000000"))
            data.add(ThemeModel(R.drawable.starspexels_thumbnail,"stars","","#ffffff"))
            data.add(ThemeModel(R.drawable.wavessunset_thumbnail,"waves","","#ffffff"))

            list.adapter = ThemeAdapter(data)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_intro4, container, false)

        list = view.findViewById(R.id.list)

        val linearLayoutManager = GridLayoutManager(context,3)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.setLayoutManager(linearLayoutManager)

        getdata()

        var gotit = view.findViewById<Button>(R.id.gotit)
        gotit.setOnClickListener {
            if (isSelected){
                transfer()
            }else{
                Toast.makeText(requireContext(),"select theme",Toast.LENGTH_SHORT).show()


            }
        }

        return view

    }
    fun transfer() {
        val frg = Intro5Fragment()
        val trans: FragmentTransaction = requireFragmentManager().beginTransaction()
        trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        trans.replace(R.id.container, frg)
        trans.commit()
    }

}