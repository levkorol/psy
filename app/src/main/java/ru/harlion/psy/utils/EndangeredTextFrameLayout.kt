package ru.harlion.psy.utils

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

class EndangeredTextFrameLayout(context: Context, attr: AttributeSet?) :
    FrameLayout(context, attr) {

    private var endangeredTextProgress = 0F
        set(value) {
            field = value
            getChildAt(0).visibility = if (value == 0F) View.VISIBLE else View.INVISIBLE
            invalidate()
        }

    private var paint = TextPaint()

    init {
        setWillNotDraw(false)
    }

    override fun onDraw(canvas: Canvas) {
        val view = getChildAt(0) as TextView
        if (view.visibility == View.VISIBLE) {
            return
        }
        val textLayout = view.layout ?: return
        val text = textLayout.text
        paint.set(view.paint)
        paint.alpha = (paint.alpha * (1 - endangeredTextProgress)).toInt()
        val progressFraction = endangeredTextProgress / textLayout.lineCount
        canvas.withTranslation(view.x + view.compoundPaddingLeft, view.y + view.totalPaddingTop) {
            repeat(textLayout.lineCount) {
              //  val progress =
                canvas.withRotation(
                    40 * endangeredTextProgress,
                    textLayout.width.toFloat() * 1.5F
                ) {
                    paint.setShadowLayer(
                        paint.textSize * endangeredTextProgress,
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