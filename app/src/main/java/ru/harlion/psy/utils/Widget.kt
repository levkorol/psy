package ru.harlion.psy.utils

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import ru.harlion.psy.R
import ru.harlion.psy.data.Repository
import ru.harlion.psy.models.TypeEx

class Widget : AppWidgetProvider() {

    val ex = Repository.get().getExList(TypeEx.POSITIVE_BELIEFS)

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val texts = mutableListOf<String>()
         ex.value?.map {
            texts.add(it.fieldTwo)
        }

        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, texts)
        }
    }

    override fun onEnabled(context: Context) {

    }

    override fun onDisabled(context: Context) {

    }
}

internal fun updateAppWidget(
    context: Context,
    appWidgetManager: AppWidgetManager,
    appWidgetId: Int,
    texts : List<String>
) {

    val views = RemoteViews(context.packageName, R.layout.app_widget)
    views.setTextViewText(R.id.appwidget_text, "Test test test test test test test test test test test")

    appWidgetManager.updateAppWidget(appWidgetId, views)
}