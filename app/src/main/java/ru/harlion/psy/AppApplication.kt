package ru.harlion.psy

import android.app.Application
import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import com.google.gson.Gson
import ru.harlion.psy.data.Repository
import ru.harlion.psy.data.billing.BillingClientWrapper
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.models.user.User
import ru.harlion.psy.ui.profile.widgets.Widget
import java.io.File
import java.io.IOException
import java.util.concurrent.Executors

val Fragment.app
    get() = requireActivity().application as AppApplication

class AppApplication : Application() {

    lateinit var exTexts: LiveData<List<String>>

    val clientWrapper: BillingClientWrapper by lazy {
        BillingClientWrapper(this)
    }

    val user = MutableLiveData(User.emptyUser)
    val io = Executors.newSingleThreadExecutor()

    override fun onCreate() {
        super.onCreate()
        Repository.initialize(this)

        val ex = Repository.get().getExList(TypeEx.POSITIVE_BELIEFS)

        exTexts = ex.map {
            it.map {
                it.listString.firstOrNull() ?: "add"
            }
        }
        exTexts.observeForever {
            val intent = Intent(this, Widget::class.java)
            intent.action = AppWidgetManager.ACTION_APPWIDGET_UPDATE
            val ids = AppWidgetManager.getInstance(this)
                .getAppWidgetIds(
                    ComponentName(
                        this,
                        Widget::class.java
                    )
                )
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids)
            sendBroadcast(intent)
        }

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