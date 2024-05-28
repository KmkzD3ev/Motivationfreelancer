package com.apx.Devocao

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.apx.Devocao.Utilities.Constant

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

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
        val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)

        Constant.Theme_Image = sharedPreferences.getInt("USER_THEME",  R.drawable.people1)
        Constant.Theme_color = sharedPreferences.getString("USER_COLOR",  "white")
        Constant.Theme_font = sharedPreferences.getString("USER_FONT",  "Anton-Regular.ttf")

        Handler(Looper.getMainLooper())
            .postDelayed({
                if (sharedPreferences.getString("USER_NAME", "").equals("")) {
                    startActivity(Intent(this, IntroActivity::class.java))
                    finish()
                } else {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            }, 3000)

    }
}