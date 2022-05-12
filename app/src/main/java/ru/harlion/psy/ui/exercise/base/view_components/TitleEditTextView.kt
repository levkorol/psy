package ru.harlion.psy.ui.exercise.base.view_components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.harlion.psy.R
import ru.harlion.psy.databinding.ComponentTitleEditTextBinding

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
                android.R.attr.title,
                android.R.attr.hint,
                android.R.attr.textOn,
                android.R.attr.lines
            )
        )
        if (params.hasValue(0)) {
            titleQuestion.text = params.getText(0)
        }
        if (params.hasValue(1)) {
            answer.hint = params.getText(1)
        }
        if (params.hasValue(2)) {
            textInfo = params.getText(2)
        }
        if (params.hasValue(3)) {
            lines = params.getInt(3, 6)
        }
        params.recycle()

        imageInfo.setOnClickListener {
            MaterialAlertDialogBuilder(context).apply {
                setTitle(textInfo)
                // setText()
                setPositiveButton("ok") { _, _ -> }
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

