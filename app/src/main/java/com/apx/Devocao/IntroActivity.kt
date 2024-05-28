package com.apx.Devocao

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.apx.Devocao.Fragments.Intro1Fragment

class IntroActivity : AppCompatActivity() {


    public lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val w = window // in Activity's onCreate() for instance
            w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION
            )
            w.setFlags(
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
            )
        }

        container = findViewById(R.id.container)
        loadFragment(Intro1Fragment())

    }


    public fun loadFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
            replace(R.id.container, fragment)
            disallowAddToBackStack()
            commit()
        }
    }
}