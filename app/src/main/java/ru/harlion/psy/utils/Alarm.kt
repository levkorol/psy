package ru.harlion.psy.utils


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.PowerManager
import android.util.Log
import android.widget.Toast
import ru.harlion.psy.AppActivity


class Alarm : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {

        Toast.makeText(context, "Alarm !!!!!!!!!!", Toast.LENGTH_LONG).show() // For example
        Log.d("Alarm", "text")
    }

    companion object {
        fun setAlarm(context: Context) {

            val am = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (Build.VERSION.SDK_INT >= 31) {
                if(am.canScheduleExactAlarms()) {

                }
            }
            val i = Intent(context, AppActivity::class.java)
            val pi = PendingIntent.getActivity(context, 0, i, 0)
            am.setRepeating(
                AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 1000,
                (1000 * 60).toLong(),
                pi
            )
        }

        fun cancelAlarm(context: Context) {
            val intent = Intent(context, Alarm::class.java)
            val sender = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(sender)
        }
    }
}