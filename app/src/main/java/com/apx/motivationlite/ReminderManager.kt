package com.apx.motivationlite

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.*

object ReminderManager {
    const val REMINDER_NOTIFICATION = 123
    fun startReminder(

        context: Context,
        reminderTime: String = "16:22",
        reminderid: Int = REMINDER_NOTIFICATION
    ) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val (hours,min)= reminderTime.split(":").map{it.toInt()}
        val intent = 
            Intent(context.applicationContext,AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(
                    context.applicationContext,
                    reminderid,
                    intent,
                    PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
                )
            }

        val calendar: Calendar = Calendar.getInstance(Locale.ENGLISH).apply {
            set(Calendar.HOUR_OF_DAY,hours)
            set(Calendar.MINUTE,min)
        }

        if (Calendar.getInstance(Locale.ENGLISH)
                .apply { add(Calendar.MINUTE,1) }.timeInMillis - calendar.timeInMillis>0
        ) {
            calendar.add(Calendar.DATE,1)
        }
        alarmManager.setAlarmClock(
            AlarmManager.AlarmClockInfo(calendar.timeInMillis,intent),
            intent
        )
    }   
    
    fun stopReminder(

        context: Context,
        reminderid: Int = REMINDER_NOTIFICATION
    ) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val intent = Intent(context,AlarmReceiver::class.java).let { intent ->
                PendingIntent.getBroadcast(
                    context,
                    reminderid,
                    intent,
                    0
                )
            }
        alarmManager.cancel(intent)
    }
}