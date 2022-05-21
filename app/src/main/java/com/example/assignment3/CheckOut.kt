package com.example.assignment3

import android.app.Notification.EXTRA_NOTIFICATION_ID
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class CheckOut : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        createNotificationChannel()
        
        val bundle : Bundle? = intent.extras

        val cancelBtn : Button=findViewById(R.id.buttonCancel)
        val confirmBtn : Button=findViewById(R.id.buttonConfirm)

        cancelBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        confirmBtn.setOnClickListener {
            pendingNotification()
            Toast.makeText(this, "CHECK OUT COMPLETE", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val layout : LinearLayout =findViewById(R.id.layout1)

        val tv_names = TextView(this)
        val tv_sum=TextView(this)
        tv_names.textSize = 20f
        tv_sum.textSize= 24f
        if (bundle != null) {
            tv_names.text = bundle.getString("names")+"\n\n"
        }
        tv_names.setTextColor(Color.GREEN)
        tv_sum.setTextColor(Color.GREEN)
        if (bundle != null) {
            tv_sum.text = "\n\nTotal: " + bundle.getString("sum")+"$"
        }
        layout.addView(tv_names)
        layout.addView(tv_sum)
    }


    private val notificationId=123
    private val channelId = "App_Channel.testNotification"
    private val description = "Check Out Complete"
    private fun pendingNotification() {
    val intent = Intent(this, CheckOut::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

    val builder = NotificationCompat.Builder(this, channelId)
        .setSmallIcon(R.drawable.ic_baseline_check_24)
        .setContentTitle("Action Figure Store")
        .setContentText("Check Out Complete")
        .setContentIntent(pendingIntent)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
    with(NotificationManagerCompat.from(this)) {
        notify(notificationId, builder.build())
    }
}

    private fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val name = "test_notification"
        val descriptionText = description
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(channelId, name, importance).apply {
            description = descriptionText
        }
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}


}