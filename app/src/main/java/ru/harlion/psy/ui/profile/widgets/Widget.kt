package ru.harlion.psy.ui.profile.widgets

import android.annotation.SuppressLint
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import ru.harlion.psy.AppApplication
import ru.harlion.psy.R
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.TypeEx
import ru.harlion.psy.utils.Prefs


class Widget : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId,
                (context.applicationContext as AppApplication).exTexts.value ?: listOf("")
            )
        }
    }

    override fun onEnabled(context: Context) {}

    override fun onDisabled(context: Context) {}
}

@SuppressLint("RemoteViewLayout")
internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    texts: List<String>
) {
    val prefs = Prefs(context)

    appWidgetManager.getAppWidgetInfo(appWidgetId)
    val views = RemoteViews(context.packageName, R.layout.app_widget)
    views.setTextViewText(
        R.id.text,
        texts.toString()
    )

    val widget = SetWidgetFragment.listWidgets.elementAt(prefs.widgetPosition)
    views.setImageViewResource(
        R.id.image,
        widget.bg
    )
   // views.setBoolean(R.id.image, "setClipToOutline", true)

    appWidgetManager.updateAppWidget(appWidgetId, views)
}