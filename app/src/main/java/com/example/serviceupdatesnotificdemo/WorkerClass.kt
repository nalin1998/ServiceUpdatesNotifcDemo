package com.example.serviceupdatesnotificdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

const val Channel_ID = "random_ID"
const val Channel_Name = "random_Name"
const val Channel_Importance = NotificationManager.IMPORTANCE_DEFAULT

class WorkerClass(private val context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    override fun doWork(): Result {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //creating notification Manager
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            //declaring notification channel
            val notificationChannel = NotificationChannel(Channel_ID, Channel_Name, Channel_Importance)

            //creating notification channel
            notificationManager.createNotificationChannel(notificationChannel)

            //building a notification
            val notificationBuilder = NotificationCompat.Builder(applicationContext, Channel_ID)
                .setOngoing(true)//sticky notification
                .setContentTitle("Sticky Notification")
                .setSmallIcon(R.mipmap.ic_launcher)

            for (i in 1..10) {
                //displaying the notification
                notificationBuilder.setContentText("$i")
                notificationManager.notify(1, notificationBuilder.build())
                Thread.sleep(1000)
            }
            notificationBuilder.setOngoing(false)
            notificationManager.notify(1, notificationBuilder.build())

        }


        return Result.success()
    }



}