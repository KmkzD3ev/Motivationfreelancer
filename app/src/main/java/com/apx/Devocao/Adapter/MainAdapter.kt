package com.apx.Devocao.Adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.apx.Devocao.DatabaseFile
import com.apx.Devocao.MainModel
import com.apx.Devocao.R
import com.apx.Devocao.Utilities.Constant
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File
import java.io.FileOutputStream

class MainAdapter( private val data:List<MainModel>):
  RecyclerView.Adapter<MainAdapter.ViewHolder>(){
   class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

       lateinit var image: ImageView
       lateinit var layout: ConstraintLayout

       var quote: String = ""

       @SuppressLint("SuspiciousIndentation")
       fun bind(list: MainModel){
           val textview = itemView.findViewById<TextView>(R.id.textView)
           val shareBtn = itemView.findViewById<ImageView>(R.id.shareBtn)
           val heartBtn = itemView.findViewById<ImageView>(R.id.heartBtn)
           layout = itemView.findViewById(R.id.layout)
           textview.text = list.title

           val typeface = Typeface.createFromAsset(itemView.context.assets, "font/" + Constant.Theme_font)
           textview.typeface = typeface

           val db: DatabaseFile = DatabaseFile(itemView.context)

           quote = list.title + " ~ " + "Jot"

           var likelist = ArrayList<MainModel>()
           likelist = db.liked
           var isLiked = false

           isLiked = likelist.contains(MainModel(list.id, list.title, list.auth, list.cate, list.lang))
           if (isLiked) {
               heartBtn.setImageResource(R.drawable.heart1)

           } else {
               heartBtn.setImageResource(R.drawable.heart)

           }

           var history = ArrayList<MainModel>()
            history = db.liked
           var ishistory  = false

           ishistory = history.contains(MainModel(list.id, list.title, list.auth, list.cate, list.lang))
//           if (ishistory){
               db.deletehistoy(list.id)
               db.Addhistory(list.id, list.title, list.auth, list.cate, list.lang)
//           }else{
//               db.Addhistory(list.id, list.title, list.auth, list.cate, list.lang)
//           }

            heartBtn.setOnClickListener {
                if (!isLiked) {
                    heartBtn.setImageResource(R.drawable.heart1)
                    db.AddLiked(list.id, list.title, list.auth, list.cate, list.lang)
                    isLiked = true
                } else {
                    heartBtn.setImageResource(R.drawable.heart)
                    db.deleteLiked(list.id)
                    isLiked = false
                }
            }

                image = itemView.findViewById<ImageView>(R.id.image)
                 image.visibility = View.GONE

           image.setImageResource(Constant.Theme_Image)

           shareBtn.setOnClickListener {
               showBottomSheetDialog();
               image.visibility = View.VISIBLE
           }
       }

       fun showBottomSheetDialog(){
           val BottomSheetDialog = BottomSheetDialog(itemView.context)
           BottomSheetDialog.setContentView(R.layout.bottom_layout_share);
            val Whatsapp = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearWhatsapp)
            val Instagam = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearInstagram)
            val Twitter = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearTwitter)
            val Facebook  = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearFacebook)
            val copy = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearDislike)
            val save = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearSaveImage)
            val more = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearMoreShare)

            BottomSheetDialog.setOnDismissListener {
                image.visibility = View.GONE
            }

           BottomSheetDialog.setOnCancelListener {
               image.visibility = View.GONE
           }


           more?.setOnClickListener(View.OnClickListener {
               image.visibility = View.VISIBLE
               layout.setDrawingCacheEnabled(true);
               layout.buildDrawingCache();
               layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
               val Bitmap = layout.drawingCache

               val root = Environment.getExternalStorageDirectory().absolutePath
                val file = File(root + "/Photos/")
               val file_name = "image.jpeg"
               val files = File(file,file_name)
               if (files.exists()) {
                   files.delete()
               }
               try {
                   val fileOutputStream = FileOutputStream(files)
                   Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                   fileOutputStream.flush()
                   fileOutputStream.close()
                   Toast.makeText(itemView.context, " Saved Successfuly",Toast.LENGTH_SHORT).show();
               }
               catch (e: java.lang.Exception) {
                    Toast.makeText(itemView.context,"Something went wrong....",Toast.LENGTH_SHORT).show();
               }
               val path = MediaStore.Images.Media.insertImage(itemView.context.contentResolver,Bitmap,"",null)
               val ImagePath = Uri.parse(path);
               val share = Intent(Intent.ACTION_SEND)
               share.setType("image/jpeg")
               share.putExtra(Intent.EXTRA_STREAM,ImagePath)
               itemView.context.startActivity(Intent.createChooser(share,"Select App"))
               image.visibility = View.GONE

           })

           Whatsapp?.setOnClickListener(View.OnClickListener {
               image.visibility = View.VISIBLE
               layout.setDrawingCacheEnabled(true);
               layout.buildDrawingCache();
               layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
               val Bitmap = layout.drawingCache

               val root = Environment.getExternalStorageDirectory().absolutePath
               val file = File(root + "/Photos/")
               val file_name = "image.jpeg"
               val files = File(file,file_name)
               if (files.exists()) {
                   files.delete()
               }
               try {
                   val fileOutputStream = FileOutputStream(files)
                   Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                   fileOutputStream.flush()
                   fileOutputStream.close()
                   Toast.makeText(itemView.context, " Saved Successfuly",Toast.LENGTH_SHORT).show();
               }
               catch (e: java.lang.Exception) {
                   Toast.makeText(itemView.context,"Something went wrong....",Toast.LENGTH_SHORT).show();
               }
               val path = MediaStore.Images.Media.insertImage(itemView.context.contentResolver,Bitmap,"",null)
               val ImagePath = Uri.parse(path);
               val share = Intent(Intent.ACTION_SEND)
               share.setType("image/jpeg")
               share.setPackage("com.whatsapp")
               share.putExtra(Intent.EXTRA_STREAM,ImagePath)
               share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
               try {
                   itemView.context.startActivity(share);
               } catch (e: java.lang.Exception){

               }
               image.visibility = View.GONE

           })

           Instagam?.setOnClickListener(View.OnClickListener {
               image.visibility = View.VISIBLE
               layout.setDrawingCacheEnabled(true);
               layout.buildDrawingCache();
               layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
               val Bitmap = layout.drawingCache

               val root = Environment.getExternalStorageDirectory().absolutePath
               val file = File(root + "/Photos/")
               val file_name = "image.jpeg"
               val files = File(file,file_name)
               if (files.exists()) {
                   files.delete()
               }
               try {
                   val fileOutputStream = FileOutputStream(files)
                   Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                   fileOutputStream.flush()
                   fileOutputStream.close()
                   Toast.makeText(itemView.context, " Saved Successfuly",Toast.LENGTH_SHORT).show();
               }
               catch (e: java.lang.Exception) {
                   Toast.makeText(itemView.context,"Something went wrong....",Toast.LENGTH_SHORT).show();
               }
               val path = MediaStore.Images.Media.insertImage(itemView.context.contentResolver,Bitmap,"",null)
               val ImagePath = Uri.parse(path);
               val share = Intent(Intent.ACTION_SEND)
               share.setType("image/jpeg")
               share.setPackage("com.instagram.android")
               share.putExtra(Intent.EXTRA_STREAM,ImagePath)
               share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
               try {
                   itemView.context.startActivity(share);
               } catch (e: java.lang.Exception){

               }
               image.visibility = View.GONE

           })

           Twitter?.setOnClickListener(View.OnClickListener {
               image.visibility = View.VISIBLE
               layout.setDrawingCacheEnabled(true);
               layout.buildDrawingCache();
               layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
               val Bitmap = layout.drawingCache

               val root = Environment.getExternalStorageDirectory().absolutePath
               val file = File(root + "/Photos/")
               val file_name = "image.jpeg"
               val files = File(file,file_name)
               if (files.exists()) {
                   files.delete()
               }
               try {
                   val fileOutputStream = FileOutputStream(files)
                   Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                   fileOutputStream.flush()
                   fileOutputStream.close()
                   Toast.makeText(itemView.context, " Saved Successfuly",Toast.LENGTH_SHORT).show();
               }
               catch (e: java.lang.Exception) {
                   Toast.makeText(itemView.context,"Something went wrong....",Toast.LENGTH_SHORT).show();
               }
               val path = MediaStore.Images.Media.insertImage(itemView.context.contentResolver,Bitmap,"",null)
               val ImagePath = Uri.parse(path);
               val share = Intent(Intent.ACTION_SEND)
               share.setType("image/jpeg")
               share.setPackage("com.twitter.android")
               share.putExtra(Intent.EXTRA_STREAM,ImagePath)
               share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
               try {
                   itemView.context.startActivity(share);
               } catch (e: java.lang.Exception){

               }
               image.visibility = View.GONE

           })

           Facebook?.setOnClickListener(View.OnClickListener {
               image.visibility = View.VISIBLE
               layout.setDrawingCacheEnabled(true);
               layout.buildDrawingCache();
               layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
               val Bitmap = layout.drawingCache

               val root = Environment.getExternalStorageDirectory().absolutePath
               val file = File(root + "/Photos/")
               val file_name = "image.jpeg"
               val files = File(file,file_name)
               if (files.exists()) {
                   files.delete()
               }
               try {
                   val fileOutputStream = FileOutputStream(files)
                   Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                   fileOutputStream.flush()
                   fileOutputStream.close()
                   Toast.makeText(itemView.context, " Saved Successfuly",Toast.LENGTH_SHORT).show();
               }
               catch (e: java.lang.Exception) {
                   Toast.makeText(itemView.context,"Something went wrong....",Toast.LENGTH_SHORT).show();
               }
               val path = MediaStore.Images.Media.insertImage(itemView.context.contentResolver,Bitmap,"",null)
               val ImagePath = Uri.parse(path);
               val share = Intent(Intent.ACTION_SEND)
               share.setType("image/jpeg")
               share.setPackage("com.facebook.katana")
               share.putExtra(Intent.EXTRA_STREAM,ImagePath)
               share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
               try {
                   itemView.context.startActivity(share);
               } catch (e: java.lang.Exception){

               }
               image.visibility = View.GONE

           })

           save?.setOnClickListener(View.OnClickListener {
               image.visibility = View.VISIBLE
               layout.isDrawingCacheEnabled = true
               layout.buildDrawingCache()
               layout.drawingCacheQuality = (View.DRAWING_CACHE_QUALITY_HIGH)
               val bitmap = layout.drawingCache
               save(bitmap);
           })

           copy?.setOnClickListener {
               val clipboardManager: ClipboardManager = itemView.context.getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
              val  ClipData = ClipData.newPlainText("Copy", quote)
               clipboardManager.setPrimaryClip(ClipData)
               Toast.makeText(itemView.context, "Copied", Toast.LENGTH_SHORT).show()
           }

           BottomSheetDialog.show()
       }

       fun save(bitmap: Bitmap) {
           val root = Environment.getExternalStorageDirectory().absolutePath
           val file = File(root + "/Photos/")
           val file_name = "image.jpeg"
           val files = File(file,file_name)
           if (files.exists()) {
               files.delete()
           }
           try {
               val fileOutputStream = FileOutputStream(files)
               bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
               fileOutputStream.flush()
               fileOutputStream.close()
               Toast.makeText(itemView.context, " Saved Successfuly",Toast.LENGTH_SHORT).show();
           }
           catch (e: java.lang.Exception) {
               Toast.makeText(itemView.context,"Something went wrong....",Toast.LENGTH_SHORT).show();
           }
       }

   }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemquote,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        (holder).bind(data[position])

    }

    override fun getItemCount(): Int {
        return data.size
    }
  }

