package ru.harlion.psy.ui.exercise.view_components

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ru.harlion.psy.R

@SuppressLint("ResourceType")
class TitleEditTextView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.component_title_edit_text, this)
    }

    private val titleQuestion = findViewById<TextView>(R.id.question)

    private val answer = findViewById<TextView>(R.id.answer)

    val text
        get() = answer.text

    init {
        val params = context.obtainStyledAttributes(
            attrs,
            intArrayOf(android.R.attr.text, android.R.attr.hint)
        )
        if (params.hasValue(0)) {
            titleQuestion.text = params.getText(0)
        }
        if (params.hasValue(1)) {
            answer.hint = params.getText(1)
        }
        params.recycle()
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(): Parcelable? {
        return answer.onSaveInstanceState()
    }

    @SuppressLint("MissingSuperCall")
    override fun onRestoreInstanceState(state: Parcelable?) {
        answer.onRestoreInstanceState(state)
    }

    override fun restoreHierarchyState(container: SparseArray<Parcelable>?) {}

    override fun saveHierarchyState(container: SparseArray<Parcelable>?) {}
}

