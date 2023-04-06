package com.apx.motivationlite.Fragments


import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.apx.motivationlite.R

class Intro1Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =  inflater.inflate(R.layout.fragment_intro1, container, false)
        val txtName = view.findViewById<EditText>(R.id.txtName)
        val continueBtn = view.findViewById<Button>(R.id.Continue)
        val skip = view.findViewById<TextView>(R.id.Skip)
        var sharedPreferences = requireActivity().getSharedPreferences(context?.packageName, MODE_PRIVATE)
        var edit = sharedPreferences.edit()
        continueBtn.setOnClickListener {
              if (txtName.text.isNotEmpty()){
                  edit.putString("USER_NAME",txtName.text.toString())
                  edit.apply()

                  val frg = Intro2Fragment()
                  val trans: FragmentTransaction = requireFragmentManager().beginTransaction()
                  trans.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
                  trans.replace(R.id.container, frg)
                  trans.commit()
              }else{

              }
        }

        return view
    }
}