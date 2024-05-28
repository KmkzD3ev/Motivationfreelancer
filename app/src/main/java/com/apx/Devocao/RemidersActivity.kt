package com.apx.Devocao

import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class RemidersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remiders2)

        val Switch = findViewById<SwitchMaterial>(R.id.Switch)
        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        val sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        val edit = sharedPreferences.edit()
        Switch.isChecked = sharedPreferences.getBoolean("isNotification",false)

        Switch.setOnCheckedChangeListener { buttonView, isChecked ->
            edit.putBoolean("isNotification",isChecked)
            edit.apply()
            if (isChecked) {
                Toast.makeText(this, "Notificações desativada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notificações ativadas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
