package com.apx.motivationlite

import android.app.Dialog
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import com.apx.motivationlite.Fragments.Intro4Fragment.Companion.getdata
import com.apx.motivationlite.Utilities.Addutilities

class GeneralActivity : AppCompatActivity() {

    val CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome";
    private var mCustomTabsServiceConnection: CustomTabsServiceConnection? = null
    private var mClient: CustomTabsClient?= null
    private var mCustomTabsSession: CustomTabsSession? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_general)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }


        var Preference = findViewById<LinearLayout>(R.id.Preference)
        Preference.setOnClickListener {
            startActivity(Intent(this,ContentPreActivity::class.java))
        }
        var Genderid = findViewById<LinearLayout>(R.id.Genderid)
        Genderid.setOnClickListener {
            startActivity(Intent(this,GenderActivity::class.java))
        }

        var forbidden = findViewById<LinearLayout>(R.id.forbidden)
        forbidden.setOnClickListener {
            startActivity(Intent(this,ForbiddenActivity::class.java))
        }

        mCustomTabsServiceConnection = object : CustomTabsServiceConnection(){
            override fun onCustomTabsServiceConnected(componentName: ComponentName,customTabsClient: CustomTabsClient)
            {
                mClient = customTabsClient
                mClient?.warmup(0L)
                mCustomTabsSession = mClient?.newSession(null)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                mClient = null
            }

        }
        CustomTabsClient.bindCustomTabsService(this, CUSTOM_TAB_PACKAGE_NAME,
        mCustomTabsServiceConnection as CustomTabsServiceConnection
        );

        var shareBtn = findViewById<LinearLayout>(R.id.shareBtn)
        shareBtn.setOnClickListener{

            val shareInent = Intent()
            shareInent.action = Intent.ACTION_SEND
            shareInent.putExtra(Intent.EXTRA_TEXT,"Hey there! Check out this app for positive daily affirmations that’ll transform your day: https://play.google.com/store/apps/details?id=com.apx.motivationlite")
            shareInent.type = "text/plain"


            startActivity(Intent.createChooser(shareInent,"send to"))
        }
        var MoreApps = findViewById<LinearLayout>(R.id.MoreApps)
        MoreApps.setOnClickListener{

            val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
                .setToolbarColor(Color.BLACK)
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(this, Uri.parse("https://play.google.com/store/apps/dev?id=7171315800975327701"))
        }
        var Leave_Review = findViewById<LinearLayout>(R.id.Leave_Review)
        Leave_Review.setOnClickListener{

            val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
                .setToolbarColor(Color.BLACK)
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(this, Uri.parse("https://play.google.com/store/apps/details?id=com.apx.motivationlite"))
        }

        var help = findViewById<LinearLayout>(R.id.help)
        help.setOnClickListener{

            val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
                .setToolbarColor(Color.BLACK)
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(this, Uri.parse("mailto:dev.gurjotsingh@gmail.com"))
        }

        var Policy_Policy = findViewById<LinearLayout>(R.id.Policy_Policy)
        Policy_Policy.setOnClickListener{

            val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
                .setToolbarColor(Color.BLACK)
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(this, Uri.parse("https://ezenstudio.blogspot.com/2022/07/jobline-privacy-policy.html"))
        }

        var Terms = findViewById<LinearLayout>(R.id.Terms)
        Terms.setOnClickListener{

            val customTabsIntent = CustomTabsIntent.Builder(mCustomTabsSession)
                .setToolbarColor(Color.BLACK)
                .setShowTitle(true)
                .build()
            customTabsIntent.launchUrl(this, Uri.parse("https://ezenstudio.blogspot.com/2022/07/jobline-privacy-policy.html"))
        }





        var person = findViewById<LinearLayout>(R.id.person)
        person.setOnClickListener {
            val dialog = Dialog(this)
            dialog.setContentView(R.layout.dialogadd)
            dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val cancel = dialog.findViewById<Button>(R.id.Cancel)
            val save = dialog.findViewById<Button>(R.id.Save)
            val collection = dialog.findViewById<EditText>(R.id.collection)
            val newcollection = dialog.findViewById<TextView>(R.id.newcollection)
            val collectiondis = dialog.findViewById<TextView>(R.id.collectiondis)
            cancel.setOnClickListener{
                dialog.cancel()
            }
            var sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)

            collection.setHint(sharedPreferences.getString("USER_NAME", ""))
            newcollection.text = "Edit your name"
            collectiondis.text = "Change your name"
            save.setOnClickListener{
                var edit = sharedPreferences.edit()

                edit.putString("USER_NAME", collection.text.toString())
                edit.apply()

                dialog.cancel()
            }

            dialog.getWindow()?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialog.setCancelable(false)
            dialog.show()

        }

    }


}