package com.rtm.notification1

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.FirebaseMessaging

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        FirebaseMessaging.getInstance().subscribeToTopic("uzb")
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            if (it.isSuccessful) {
                Log.d("FCM",it.result)
            } else {
                Log.d("FCM",it.exception.toString())
            }
        }


    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val chanel =
                NotificationChannel(
                    getString(R.string.id),
                    getString(R.string.channel_name),
                    NotificationManager.IMPORTANCE_HIGH
                )
            chanel.setShowBadge(true)
            val notificationManager = getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(chanel)
        }
    }
}