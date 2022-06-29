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
        val prefs = Prefs(context)
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(
                context,
                appWidgetManager,
                appWidgetId,
                if (prefs.exIdForWidget.value!! <= 0) {
                    context.getString(R.string.info_widgets_in)
                } else {
                    (context.applicationContext as AppApplication).exTexts.value?.randomOrNull()
                        ?: context.getString(R.string.empty_list_)
                }
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
    texts: String,
) {
    val prefs = Prefs(context)

    appWidgetManager.getAppWidgetInfo(appWidgetId)
    val views = RemoteViews(context.packageName, R.layout.app_widget)
    views.setTextViewText(
        R.id.text,
        texts
    )

    val widget = SetWidgetFragment.listWidgets.elementAt(prefs.widgetPosition.value!!)
    views.setImageViewResource(
        R.id.image,
        widget.bg
    )

    appWidgetManager.updateAppWidget(appWidgetId, views)
}