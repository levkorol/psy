package ru.harlion.psy.ui.exercise.view_components

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.material.slider.Slider
import ru.harlion.psy.R

class TitleSliderValue(context: Context, attrs: AttributeSet?) : LinearLayout(context, attrs) {

    init {
        orientation = VERTICAL
        inflate(context, R.layout.component_title_seek_bar_value, this)
    }

    private val titleQuestion = findViewById<TextView>(R.id.question_slider).also {
        it.id = NO_ID
    }

    private val countSlider = findViewById<TextView>(R.id.count_slider).also {
        it.id = NO_ID
    }

    private val slider = findViewById<Slider>(R.id.slider).also {
        it.id = NO_ID
    }


}