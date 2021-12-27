package com.example.phone_brodcasrricever

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.BatteryState
import android.nfc.Tag
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

  private val TAG: String? = MainActivity::class.simpleName

class MainActivity : AppCompatActivity() {


    private var levelbattery: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        levelbattery = findViewById(R.id.level_Battery)


        val intentFilter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(batteryBroadcastReceiver, intentFilter)

        Log.e("TAG", "main: last")
    }

    private val batteryBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {



//            if (intent?.action == "android.intent.action.BATTERY_CHANGED") {
//                val level = intent.getBooleanExtra(BatteryManager.EXTRA_STATUS, false)
//                levelbattery?.post {
//                    levelbattery?.text = level.toString().plus("").plus("%")
//                }
//            }


            val batteryStatus: Intent? = IntentFilter(Intent.ACTION_BATTERY_CHANGED).let {
                    ifilter -> context!!.registerReceiver(null, ifilter)
            }
            val status: Int = batteryStatus?.getIntExtra(BatteryManager.EXTRA_STATUS, -1) ?: -1


            val isCharging: Boolean = status == BatteryManager.BATTERY_STATUS_CHARGING
                    || status == BatteryManager.BATTERY_STATUS_FULL


            Toast.makeText(context, "$isCharging", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(batteryBroadcastReceiver)
    }

}
