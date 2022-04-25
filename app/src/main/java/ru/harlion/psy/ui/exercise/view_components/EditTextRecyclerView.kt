package ru.harlion.psy.ui.exercise.view_components


import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.harlion.psy.R

class EditTextRecyclerView (context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs){

    init {
        orientation = VERTICAL
        inflate(context, R.layout.component_edit_text_recycler_view, this)
    }

    private val titleQuestion = findViewById<TextView>(R.id.answer_c3).also {
        it.id = NO_ID
    }

    private val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_c3).also {
        it.id = NO_ID
    }

    init {
        val params = context.obtainStyledAttributes(
            attrs,
            intArrayOf(android.R.attr.hint)
        )
        if (params.hasValue(0)) {
            titleQuestion.hint = params.getText(0)
        }
        params.recycle()
    }
}