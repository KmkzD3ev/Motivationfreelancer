package com.apx.Devocao.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.apx.Devocao.R

class Intro3Fragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_intro3, container, false)
        var gotit = view.findViewById<Button>(R.id.gotit)
        gotit.setOnClickListener {
            transfer()
        }
        return view

    }
    fun transfer() {
        val frg = Intro4Fragment()
        val trans: FragmentTransaction = requireFragmentManager().beginTransaction()
        trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        trans.replace(R.id.container, frg)
        trans.commit()
    }


}