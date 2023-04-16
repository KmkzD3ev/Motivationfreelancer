package com.apx.motivationlite

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.apx.motivationlite.Utilities.idgenerater

class ForbiddenAdapter ( val data:List<ForbiddenModel>):
RecyclerView.Adapter<ForbiddenAdapter.ViewHolder>() {
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        fun bind(list: ForbiddenModel){
            val textView = itemView.findViewById<TextView>(R.id.CSK)
            val imageView = itemView.findViewById<ImageView>(R.id.More)


            val db: DatabaseFile = DatabaseFile(itemView.context)

            textView.text = list.word

            imageView.setOnClickListener {
               val dialog  = Dialog(itemView.context)
                dialog.setContentView(R.layout.dialoglayout)

                var Edit = dialog.findViewById<TextView>(R.id.Edit)
                var Remove = dialog.findViewById<TextView>(R.id.Remove)
                Remove.setOnClickListener{
                    db.deleteForbiddenWord(list.id)
                    Toast.makeText(itemView.context, "Removed Successfully", Toast.LENGTH_SHORT).show()
                    dialog.hide()
                }

                Edit.setOnClickListener {
                    dialog.hide()
                    editName(list.id)
                }

                dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)


                dialog.show()
            }
        }

        fun editName (id: String) {
            val dialog  = Dialog(itemView.context)
            dialog.setContentView(R.layout.dialogadd)
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val cancel = dialog.findViewById<Button>(R.id.Cancel)
            val save = dialog.findViewById<Button>(R.id.Save)
            val collection = dialog.findViewById<EditText>(R.id.collection)
            val newcollection = dialog.findViewById<TextView>(R.id.newcollection)
            val collectiondis = dialog.findViewById<TextView>(R.id.collectiondis)
            newcollection.text = "Edit Forbidden Word"
            collectiondis.text = ""
            collection.hint = "Word"

            cancel.setOnClickListener {
                dialog.cancel()
            }

            save.setOnClickListener {
                val db = DatabaseFile(itemView.context)
                db.updateForbiddenWord(id, collection.text.toString())
                dialog.cancel()
            }

            dialog.getWindow()?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            dialog.setCancelable(false)

            dialog.show()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemforbidden,parent,false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }
}