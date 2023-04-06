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
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Adapter.CollectionAdapter
import com.apx.motivationlite.Model.CollectionModel
import com.apx.motivationlite.Utilities.idgenerater

class CollectionActivity : AppCompatActivity() {
    lateinit var list: RecyclerView
    companion object {
        var id: String = ""
        var title: String = ""
        var auth: String = ""
        var lang: String = ""
        var cate: String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layoutcollection)

         list = findViewById(R.id.list)
        val linearLayoutManager = GridLayoutManager(this,1)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        list.layoutManager = linearLayoutManager

        getdata()
        id = intent.getStringExtra("id").toString()
        title = intent.getStringExtra("title").toString()
        auth = intent.getStringExtra("auth").toString()
        cate = intent.getStringExtra("cate").toString()
        lang = intent.getStringExtra("lang").toString()

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
                db.AddCollections(idgenerater.getId(), collection.text.toString())
                getdata()
                dialog.cancel()
            }


            dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.setCancelable(false)

            dialog.show()
        }
    }
    fun getdata(){
        var data = ArrayList<CollectionModel>()
        data.clear()

        var db = DatabaseFile(this)
        data = db.getcollection()
        list.adapter = CollectionAdapter(data)

    }
    public fun setdata(Collectionid:String){
        var db = DatabaseFile(this)


     }

}