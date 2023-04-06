package com.apx.motivationlite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)

        Handler(Looper.getMainLooper()).postDelayed({
                                                    if (sharedPreferences.getString("USER_NAME", "").equals("")) {
                                                        startActivity(Intent(this,IntroActivity::class.java))
                                                        finish()
                                                    } else {
                                                        startActivity(Intent(this,IntroActivity::class.java))
                                                        finish()
                                                    }
        }, 3000)

    }
}