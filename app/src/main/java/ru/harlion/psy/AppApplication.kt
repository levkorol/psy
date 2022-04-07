package ru.harlion.psy

import android.app.Application
import com.google.gson.Gson
import ru.harlion.psy.data.Repository

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)
    }

    companion object {
        val gson = Gson()
    }
}