package com.example.glanceemoji.notification

import android.Manifest
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.glanceemoji.HELLO_WORLD
import com.example.glanceemoji.MainActivity
import com.example.glanceemoji.R

private const val CHANNEL_ID = "sample_notification_channel"
private const val CHANNEL_NAME = "Notification Channel"

class SampleNotificationManager(
    private val context: Context
) {

    private val notificationManager = NotificationManagerCompat.from(context)

    fun showNotification(): Boolean {
        if (!notificationManager.areNotificationsEnabled()) {
            return false
        }

        val channel =
            NotificationChannelCompat.Builder(CHANNEL_ID, NotificationManagerCompat.IMPORTANCE_HIGH)
                .setName(CHANNEL_NAME)
                .build()
        notificationManager.createNotificationChannel(channel)

        val contentIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            contentIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_stat_notification)
            .setColor(ContextCompat.getColor(context, R.color.teal_700))
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setContentText(HELLO_WORLD)

        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(1, builder.build())
            true
        } else {
            false
        }
    }
}