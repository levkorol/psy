package ru.harlion.psy.ui.profile.widgets

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

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    texts: List<String>
) {

    val views = RemoteViews(context.packageName, R.layout.app_widget)
    views.setTextViewText(
        R.id.appwidget_text,
        texts.toString()
    )

    appWidgetManager.updateAppWidget(appWidgetId, views)
}