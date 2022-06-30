package ru.harlion.psy.ui.exercise.base.view_components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import ru.harlion.psy.R
import ru.harlion.psy.databinding.ComponentTitleEditTextBinding
import ru.harlion.psy.utils.assertSorted
import ru.harlion.psy.utils.dialogs.EditTextDialog

@SuppressLint("ResourceType")
class TitleEditTextView(context: Context, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    init {
        inflate(context, R.layout.component_title_edit_text, this)
    }

    private val binding = ComponentTitleEditTextBinding.bind(this)

    private val titleQuestion = binding.question

    private val imageInfo = binding.info

    private val answer = binding.answer

    var title
        get() = titleQuestion.text
        set(value) {
            titleQuestion.text = value
        }

    var hint
        get() = answer.hint
        set(value) {
            answer.hint = value
        }

    var text : CharSequence
        get() = answer.text
        set(value) {
            answer.setText(value)
        }

    var lines
        get() = answer.minLines
        set(value) {
            answer.setLines(value)
        }

    var textInfo: CharSequence? = null
        set(value) {
            field = value
            if (value != null) {
                imageInfo.visibility = View.VISIBLE
            } else {
                imageInfo.visibility = View.GONE
            }
        }

    init {
        val params = context.obtainStyledAttributes(
            attrs,
            intArrayOf(
                android.R.attr.textOn,
                android.R.attr.hint,
                android.R.attr.lines,
                android.R.attr.title,
            ).assertSorted()
        )
        if (params.hasValue(0)) {
            textInfo = params.getText(0)
        }
        if (params.hasValue(1)) {
            answer.hint = params.getText(1)
        }
        if (params.hasValue(2)) {
            lines = params.getInt(4, 6)
        }
        if (params.hasValue(3)) {

            titleQuestion.text = params.getText(3)
        }
        params.recycle()

        imageInfo.setOnClickListener {
            EditTextDialog(context).apply {
                setText(textInfo.toString())
                setPositiveButton("OK") {}
            }.show()
        }
    }

    fun setText(string: String) {
        answer.setText(string)
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>?) {
        container?.put(id, answer.onSaveInstanceState())
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>?) {
        answer.onRestoreInstanceState(container?.get(id))
    }

    fun addListener( listener : TextWatcher) {
        answer.addTextChangedListener(listener)
    }

}

