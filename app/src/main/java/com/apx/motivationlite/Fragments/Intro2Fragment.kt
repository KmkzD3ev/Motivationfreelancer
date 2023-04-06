package com.apx.motivationlite.Fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.apx.motivationlite.R


class Intro2Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_intro2, container, false)
        val femalebtn = view.findViewById<Button>(R.id.femaleBtn)
        val malebtn = view.findViewById<Button>(R.id.maleBtn)
        val otherbtn = view.findViewById<Button>(R.id.otherBtn)
        val btn = view.findViewById<Button>(R.id.btn_pns)
        var sharedPreferences = requireActivity().getSharedPreferences(context?.packageName,MODE_PRIVATE)
        var edit = sharedPreferences.edit()

        femalebtn.setOnClickListener {
            edit.putString("USER_GENDER", "Female")
            edit.apply()
            transfer()
        }
        malebtn.setOnClickListener {
            edit.putString("USER_GENDER", "Male")
            edit.apply()
            transfer()
        }
        otherbtn.setOnClickListener {
            edit.putString("USER_GENDER", "Other")
            edit.apply()
            transfer()
        }
        btn.setOnClickListener {
            edit.putString("USER_GENDER", "Prefer not to say")
            edit.apply()
            transfer()
        }


        return view

    }

    fun transfer(){
        val frg = Intro3Fragment()
        val trans: FragmentTransaction = requireFragmentManager().beginTransaction()
        trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        trans.replace(R.id.container, frg)
        trans.commit()
    }
}