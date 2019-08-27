package com.example.serviceupdatesnotificdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //creating oneTimeRequestObject.....
        val workRequest : OneTimeWorkRequest = OneTimeWorkRequest.Builder(WorkerClass::class.java).build()

        WorkManager.getInstance(this).enqueue(workRequest)
    }
}
