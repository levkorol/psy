package ru.harlion.psy.utils.custom_view

import android.content.Context
import android.graphics.Canvas
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Property
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.graphics.withRotation
import androidx.core.graphics.withTranslation
import ru.harlion.psy.R
import kotlin.math.max


class EndangeredTextFrameLayout(context: Context, attr: AttributeSet?) :
    FrameLayout(context, attr) {

    private var endangeredTextProgress = 0F
        set(value) {
            field = value
            findViewById<TextView>(R.id.text_endangered).visibility = if (value == 0F) View.VISIBLE else View.INVISIBLE
            invalidate()
        }

    private var paint = TextPaint()

    init {
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas) {
        val view = findViewById<TextView>(R.id.text_endangered)
        if (view.visibility == View.VISIBLE) {
            return
        }
        val textLayout = view.layout ?: return
        val text = textLayout.text
        paint.set(view.paint)
        paint.alpha = (paint.alpha * (1 - endangeredTextProgress)).toInt()
        val progressFraction = 1F / textLayout.lineCount
        canvas.withTranslation(view.x + view.compoundPaddingLeft, view.y + view.totalPaddingTop) {
            repeat(textLayout.lineCount) {
                val progress = max(0F, endangeredTextProgress - progressFraction * it)
                canvas.withRotation(
                    40 * progress,
                    textLayout.width.toFloat() * 1.5F
                ) {
                    paint.setShadowLayer(
                        paint.textSize * progress,
                        0F,
                        0F,
                        paint.color
                    )
                    canvas.drawText(
                        text,
                        textLayout.getLineStart(it),
                        textLayout.getLineStart(it + 1),
                        textLayout.getLineLeft(it),
                        textLayout.getLineBaseline(it).toFloat(),
                        paint
                    )
                }
            }
        }
    }

    companion object {
        val ENDANGERED_TEXT_PROGRESS = object :
            Property<EndangeredTextFrameLayout, Float>(
                Float::class.java,
                "endangeredTextProgress"
            ) {
            override fun get(`object`: EndangeredTextFrameLayout): Float {
                return `object`.endangeredTextProgress
            }

            override fun set(`object`: EndangeredTextFrameLayout, value: Float) {
                `object`.endangeredTextProgress = value
            }
        }
    }

}