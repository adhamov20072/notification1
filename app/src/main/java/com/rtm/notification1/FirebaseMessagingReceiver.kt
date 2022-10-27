package com.rtm.notification1

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseMessagingReceiver : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        triggerNotification(
            message.notification?.title.toString(),
            message.notification?.body.toString()
        )
    }

    fun triggerNotification(title: String, text: String) {
        val intent = Intent(this, MainActivity2::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        val notification = NotificationCompat.Builder(this, getString(R.string.id))
            .setSmallIcon(R.drawable.ic_baseline_10k_24)
            .setContentTitle(title)
            .setContentText(text)
            .setChannelId(getString(R.string.id))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .build()
        val notificationCompatManager = NotificationManagerCompat.from(this)
        notificationCompatManager.notify(11, notification)
    }
}