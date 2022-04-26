package ru.harlion.psy.ui.exercise.base.view_components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcelable
import android.util.AttributeSet
import android.util.SparseArray
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.slider.Slider
import ru.harlion.psy.R

@SuppressLint("ResourceType")
class TitleSliderValue(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        orientation = VERTICAL
        inflate(context, R.layout.component_title_seek_bar_value, this)
    }

    private val titleQuestion = findViewById<TextView>(R.id.question_slider).also {
        it.id = NO_ID
    }

    private val slider = findViewById<Slider>(R.id.slider).also {
        it.id = NO_ID
    }

    val countSlider
        get() = slider.value

    init {
        val params = context.obtainStyledAttributes(
            attrs,
            intArrayOf(android.R.attr.text)
        )
        if (params.hasValue(0)) {
            titleQuestion.text = params.getText(0)
        }
        params.recycle()
    }

    override fun dispatchSaveInstanceState(container: SparseArray<Parcelable>?) {
        val sliderId = slider.id
        slider.id = id
        slider.saveHierarchyState(container)
        slider.id = sliderId
    }

    override fun dispatchRestoreInstanceState(container: SparseArray<Parcelable>?) {
        val sliderId = slider.id
        slider.id = id
        slider.restoreHierarchyState(container)
        slider.id = sliderId
    }

}