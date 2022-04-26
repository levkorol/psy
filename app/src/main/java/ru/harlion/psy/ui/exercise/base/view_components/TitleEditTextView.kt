package ru.harlion.psy.ui.exercise.base.view_components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
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

    val text
        get() = answer.text

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
            intArrayOf(android.R.attr.title, android.R.attr.hint, android.R.attr.textOn)
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
        params.recycle()

        imageInfo.setOnClickListener {
           BottomSheetDialog(context).apply {
               setContentView(TextView(context).apply {
                 text = textInfo
               })
           }.show()
        }
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>?) {
        container?.put(id, answer.onSaveInstanceState())
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>?) {
        answer.onRestoreInstanceState(container?.get(id))
    }

}

