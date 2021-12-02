package com.example.phone_brodcasrricever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.nfc.Tag
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private val TAG : String? = MainActivity ::class.simpleName
    private var levelbattery : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        levelbattery = findViewById(R.id.level_Battery)
        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryBroadcastReceiver ,intentFilter)

    }
    private val batteryBroadcastReceiver : BroadcastReceiver = object : BroadcastReceiver (){
        override fun onReceive(context: Context?, intent: Intent?) {
            if (intent?.action == "android.intent.BATTERY_CHANGED"){
                val level = intent.getIntArrayExtra(BatteryManager.EXTRA_LEVEL)
                Log.d(TAG, "onReceive: battery level $level")
             levelbattery?.post {
                 levelbattery?.text = level.toString().plus("").plus("%")
             }

            }
        }




     }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryBroadcastReceiver)
    }

    }
