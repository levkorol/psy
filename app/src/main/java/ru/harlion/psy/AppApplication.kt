package ru.harlion.psy

import android.app.Application
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.user.User
import java.io.File
import java.io.IOException
import java.util.concurrent.Executors
import kotlin.concurrent.thread

val Fragment.app
    get() = requireActivity().application as AppApplication

class AppApplication : Application() {

    val user = MutableLiveData(User.emptyUser)
    val io = Executors.newSingleThreadExecutor()

    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)

        io.execute {
            val file = File(this.filesDir, "user.json")
            if (file.exists()) {
                try {
                    user.postValue(file.inputStream().reader().use { stream ->
                        gson.fromJson(stream, User::class.java)
                    })
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            Handler(Looper.getMainLooper()).post {
                user.observeForever {
                    io.execute {
                        try {
                            file.outputStream().writer().use { stream ->
                                gson.toJson(it, stream)
                            }
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