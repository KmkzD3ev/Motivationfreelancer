package com.apx.Devocao

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.apx.Devocao.Utilities.Constant

import com.google.android.material.bottomsheet.BottomSheetDialog
import java.io.File
import java.io.FileOutputStream

class ShareActivity : AppCompatActivity() {

    lateinit var layout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share2)

        var textView = findViewById<TextView>(R.id.textView)
        var shareBtn = findViewById<ImageView>(R.id.shareBtn)
        var heartBtn = findViewById<ImageView>(R.id.heartBtn)
        var image = findViewById<ImageView>(R.id.image)

        layout = findViewById(R.id.layout)

        textView.text = intent.getStringExtra("title")
        image.setImageResource(Constant.Theme_Image)
        val db = DatabaseFile(this)

        val typeface = Typeface.createFromAsset(assets, "font/" + Constant.Theme_font)
        textView.typeface = typeface

        var isLiked = false

        isLiked = db.getLiked().contains(
            MainModel(
                intent.getStringExtra("id").toString(),
                intent.getStringExtra("title").toString(),
                intent.getStringExtra("auth").toString(),
                intent.getStringExtra("cate").toString(),
                intent.getStringExtra("lang").toString()
            ))
        if (isLiked){
            heartBtn.setImageResource(R.drawable.heart1)
        }else{
            heartBtn.setImageResource(R.drawable.heart)
        }


        heartBtn.setOnClickListener {
            if(!isLiked) {
                heartBtn.setImageResource(R.drawable.heart1)
                db.AddLiked(intent.getStringExtra("id"),intent.getStringExtra("title"),intent.getStringExtra("auth"),intent.getStringExtra("cate"),intent.getStringExtra("lang"))
                isLiked = true
            }else{
                heartBtn.setImageResource(R.drawable.heart)
                db.deleteLiked(intent.getStringExtra("id"))
                isLiked = false
            }
        }

        shareBtn.setOnClickListener {
            showBottomSheetDialog()
        }

    }


    fun showBottomSheetDialog() {
        val BottomSheetDialog = BottomSheetDialog(this)
        BottomSheetDialog.setContentView(R.layout.bottom_layout_share);
        val Whatsapp = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearWhatsapp)
        val Instagam = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearInstagram)
        val Twitter = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearTwitter)
        val Facebook = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearFacebook)
        val copy = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearDislike)
        val save = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearSaveImage)
        val more = BottomSheetDialog.findViewById<LinearLayout>(R.id.linearMoreShare)

        BottomSheetDialog.setOnDismissListener {

        }

        BottomSheetDialog.setOnCancelListener {

        }


        more?.setOnClickListener(View.OnClickListener {

            layout.setDrawingCacheEnabled(true);
            layout.buildDrawingCache();
            layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
            val Bitmap = layout.drawingCache

            val root = Environment.getExternalStorageDirectory().absolutePath
            val file = File(root + "/Photos/")
            val file_name = "image.jpeg"
            val files = File(file, file_name)
            if (files.exists()) {
                files.delete()
            }
            try {
                val fileOutputStream = FileOutputStream(files)
                Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                Toast.makeText(this, " Saved Successfuly", Toast.LENGTH_SHORT).show();
            } catch (e: java.lang.Exception) {
                Toast.makeText(this, "Something went wrong....", Toast.LENGTH_SHORT)
                    .show();
            }
            val path = MediaStore.Images.Media.insertImage(
                contentResolver,
                Bitmap,
                "",
                null
            )
            val ImagePath = Uri.parse(path);
            val share = Intent(Intent.ACTION_SEND)
            share.setType("image/jpeg")
            share.putExtra(Intent.EXTRA_STREAM, ImagePath)
            startActivity(Intent.createChooser(share, "Select App"))


        })

        Whatsapp?.setOnClickListener(View.OnClickListener {

            layout.setDrawingCacheEnabled(true);
            layout.buildDrawingCache();
            layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
            val Bitmap = layout.drawingCache

            val root = Environment.getExternalStorageDirectory().absolutePath
            val file = File(root + "/Photos/")
            val file_name = "image.jpeg"
            val files = File(file, file_name)
            if (files.exists()) {
                files.delete()
            }
            try {
                val fileOutputStream = FileOutputStream(files)
                Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                Toast.makeText(this, " Saved Successfuly", Toast.LENGTH_SHORT).show();
            } catch (e: java.lang.Exception) {
                Toast.makeText(this, "Something went wrong....", Toast.LENGTH_SHORT)
                    .show();
            }
            val path = MediaStore.Images.Media.insertImage(
                this.contentResolver,
                Bitmap,
                "",
                null
            )
            val ImagePath = Uri.parse(path);
            val share = Intent(Intent.ACTION_SEND)
            share.setType("image/jpeg")
            share.setPackage("com.whatsapp")
            share.putExtra(Intent.EXTRA_STREAM, ImagePath)
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            try {
                this.startActivity(share);
            } catch (e: java.lang.Exception) {

            }


        })

        Instagam?.setOnClickListener(View.OnClickListener {

            layout.setDrawingCacheEnabled(true);
            layout.buildDrawingCache();
            layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
            val Bitmap = layout.drawingCache

            val root = Environment.getExternalStorageDirectory().absolutePath
            val file = File(root + "/Photos/")
            val file_name = "image.jpeg"
            val files = File(file, file_name)
            if (files.exists()) {
                files.delete()
            }
            try {
                val fileOutputStream = FileOutputStream(files)
                Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                Toast.makeText(this, " Saved Successfuly", Toast.LENGTH_SHORT).show();
            } catch (e: java.lang.Exception) {
                Toast.makeText(this, "Something went wrong....", Toast.LENGTH_SHORT)
                    .show();
            }
            val path = MediaStore.Images.Media.insertImage(
                this.contentResolver,
                Bitmap,
                "",
                null
            )
            val ImagePath = Uri.parse(path);
            val share = Intent(Intent.ACTION_SEND)
            share.setType("image/jpeg")
            share.setPackage("com.instagram.android")
            share.putExtra(Intent.EXTRA_STREAM, ImagePath)
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            try {
                this.startActivity(share);
            } catch (e: java.lang.Exception) {

            }


        })

        Twitter?.setOnClickListener(View.OnClickListener {

            layout.setDrawingCacheEnabled(true);
            layout.buildDrawingCache();
            layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
            val Bitmap = layout.drawingCache

            val root = Environment.getExternalStorageDirectory().absolutePath
            val file = File(root + "/Photos/")
            val file_name = "image.jpeg"
            val files = File(file, file_name)
            if (files.exists()) {
                files.delete()
            }
            try {
                val fileOutputStream = FileOutputStream(files)
                Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                Toast.makeText(this, " Saved Successfuly", Toast.LENGTH_SHORT).show();
            } catch (e: java.lang.Exception) {
                Toast.makeText(this, "Something went wrong....", Toast.LENGTH_SHORT)
                    .show();
            }
            val path = MediaStore.Images.Media.insertImage(
                this.contentResolver,
                Bitmap,
                "",
                null
            )
            val ImagePath = Uri.parse(path);
            val share = Intent(Intent.ACTION_SEND)
            share.setType("image/jpeg")
            share.setPackage("com.twitter.android")
            share.putExtra(Intent.EXTRA_STREAM, ImagePath)
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            try {
                this.startActivity(share);
            } catch (e: java.lang.Exception) {

            }


        })

        Facebook?.setOnClickListener(View.OnClickListener {

            layout.setDrawingCacheEnabled(true);
            layout.buildDrawingCache();
            layout.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
            val Bitmap = layout.drawingCache

            val root = Environment.getExternalStorageDirectory().absolutePath
            val file = File(root + "/Photos/")
            val file_name = "image.jpeg"
            val files = File(file, file_name)
            if (files.exists()) {
                files.delete()
            }
            try {
                val fileOutputStream = FileOutputStream(files)
                Bitmap.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fileOutputStream)
                fileOutputStream.flush()
                fileOutputStream.close()
                Toast.makeText(this, " Saved Successfuly", Toast.LENGTH_SHORT).show();
            } catch (e: java.lang.Exception) {
                Toast.makeText(this, "Something went wrong....", Toast.LENGTH_SHORT)
                    .show();
            }
            val path = MediaStore.Images.Media.insertImage(
                this.contentResolver,
                Bitmap,
                "",
                null
            )
            val ImagePath = Uri.parse(path);
            val share = Intent(Intent.ACTION_SEND)
            share.setType("image/jpeg")
            share.setPackage("com.facebook.katana")
            share.putExtra(Intent.EXTRA_STREAM, ImagePath)
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            try {
                this.startActivity(share);
            } catch (e: java.lang.Exception) {

            }


        })

        save?.setOnClickListener(View.OnClickListener {

            layout.isDrawingCacheEnabled = true
            layout.buildDrawingCache()
            layout.drawingCacheQuality = (View.DRAWING_CACHE_QUALITY_HIGH)
            val bitmap = layout.drawingCache
            save(bitmap);
        })

        copy?.setOnClickListener {
            val clipboardManager: ClipboardManager = this.getSystemService(
                CLIPBOARD_SERVICE
            ) as ClipboardManager
            val ClipData = ClipData.newPlainText("Copy", intent.getStringExtra("title"))
            clipboardManager.setPrimaryClip(ClipData)
            Toast.makeText(this, "Copied", Toast.LENGTH_SHORT).show()
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
            Toast.makeText(this, " Saved Successfuly",Toast.LENGTH_SHORT).show();
        }
        catch (e: java.lang.Exception) {
            Toast.makeText(this,"Something went wrong....",Toast.LENGTH_SHORT).show();
        }
    }

}