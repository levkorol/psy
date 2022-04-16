package ru.harlion.psy.ui.main.diary_emotions.adapter

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class NonTouchableRecyclerView(context : Context, attrs: AttributeSet?): RecyclerView(context, attrs) {

    override fun onTouchEvent(e: MotionEvent?): Boolean {
        return false
    }
}