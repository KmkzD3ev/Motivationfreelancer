package com.apx.motivationlite

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val notificationManager = ContextCompat.getSystemService(
            context,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendReminderNotification(
            applicationContext = context,
            ChannelId = context.getString(R.string.reminders_notification_channel_id)
        )
    }
}
    fun NotificationManager.sendReminderNotification(
        applicationContext: Context,
        ChannelId: String,
    ) {
        val contentIntent = Intent(applicationContext,MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            1,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val builder = NotificationCompat.Builder(applicationContext, ChannelId)
            .setContentTitle("Hello World!!!")
            .setContentText("Hello World 234")
            .setSmallIcon(R.drawable.logo)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Hey!!!!!!!!!@")
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        notify(NOTIFICATION_ID, builder.build())
    }

const val NOTIFICATION_ID = 1