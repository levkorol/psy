package ru.harlion.psy.utils

import android.content.Context

class Prefs(val context: Context) {

    private val sharedPrefs = context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    var isShowOnBoarding: Boolean
        get() = sharedPrefs.getBoolean("ON_BOARDING_SHOW", false)
        set(value) = sharedPrefs.edit().putBoolean("ON_BOARDING_SHOW", value)
            .apply()
}