package com.apx.motivationlite

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.switchmaterial.SwitchMaterial

class RemidersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remiders)
        createNotificationsChannels()

        var Switch = findViewById<SwitchMaterial>(R.id.Switch)
        ReminderManager.startReminder(this)

        val back = findViewById<ImageView>(R.id.back)
        back.setOnClickListener {
            onBackPressed()
        }

        var sharedPreferences = getSharedPreferences(packageName, MODE_PRIVATE)
        var edit = sharedPreferences.edit()
        Switch.isChecked = sharedPreferences.getBoolean("isNotification",false)

        Switch.setOnCheckedChangeListener { buttonView, isChecked ->
            edit.putBoolean("isNotification",isChecked)
            edit.apply()
            if (
                isChecked
            )
                ReminderManager.startReminder(this)
            else
                ReminderManager.stopReminder(this)
        }

    }

    private fun createNotificationsChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                getString(R.string.reminders_notification_channel_id),
                "Daily Reminder",
                NotificationManager.IMPORTANCE_HIGH
            )
            ContextCompat.getSystemService(this, NotificationManager::class.java)
                ?.createNotificationChannel(channel)
        }
    }
}