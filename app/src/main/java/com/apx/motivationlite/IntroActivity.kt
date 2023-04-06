package com.apx.motivationlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.apx.motivationlite.Fragments.Intro1Fragment

class IntroActivity : AppCompatActivity() {


    public lateinit var container: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

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