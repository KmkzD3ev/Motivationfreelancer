package com.apx.motivationlite

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Utilities.idgenerater


class ForbiddenActivity : AppCompatActivity() {

    lateinit var list: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forbidden)

        list = findViewById(R.id.list)
        val linearLayoutManager = GridLayoutManager(this,1)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = linearLayoutManager



    var plusBtn = findViewById<ImageView>(R.id.plusBtn)
        val dialog = Dialog(this)
        plusBtn.setOnClickListener {
            dialog.setContentView(R.layout.dialogadd)
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
             val cancel = dialog.findViewById<Button>(R.id.Cancel)
            val save = dialog.findViewById<Button>(R.id.Save)
            val collection = dialog.findViewById<EditText>(R.id.collection)
            cancel.setOnClickListener {
                dialog.cancel()
            }
            save.setOnClickListener {
                val db = DatabaseFile(this)
                db.AddForbiddenWord(idgenerater.getId(), collection.text.toString())
                getdata()
                dialog.cancel()
            }

            dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.setCancelable(false)

            dialog.show()
        }

    }

    fun getdata(){
        var  data = ArrayList<ForbiddenModel>()
        data.clear()

        var db = DatabaseFile(this)
        data = db.forbidden
        list.adapter = ForbiddenAdapter(data)

    }

}