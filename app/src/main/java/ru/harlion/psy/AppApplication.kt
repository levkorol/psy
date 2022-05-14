package ru.harlion.psy

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.user.User
import java.io.File
import java.io.IOException
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class AppApplication : Application() {

    val user = MutableLiveData<User>()
    val io = Executors.newSingleThreadExecutor()

    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)

       io.execute {
            val file = File(this.filesDir, "user.json")
            if (file.exists()) {
                try {
                    user.postValue(gson.fromJson(file.inputStream().reader(), User::class.java))
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
           Handler(Looper.getMainLooper()).post {
               user.observeForever {
                   io.execute {
                       try {
                           gson.toJson(it, file.outputStream().writer())
                       } catch (e: IOException) {
                           e.printStackTrace()
                       }
                   }
               }
           }
        }
    }

    companion object {
        val gson = Gson()
    }
}