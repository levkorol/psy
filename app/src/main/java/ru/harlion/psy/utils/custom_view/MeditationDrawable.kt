package ru.harlion.psy.utils.custom_view

import android.graphics.*
import android.graphics.drawable.Drawable
import android.util.Property

class MeditationDrawable : Drawable() {

    var progress = 0F
        set(value) {
            if (value != field) {
                field = value
                gradients.fill(null)
                invalidateSelf()
            }
        }
    val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    val gradients = arrayOfNulls<RadialGradient>(4)

    var bottomPadding = 0
        set(value) {
            if (value != field) {
                field = value
                invalidateSelf()
            }
        }

    var color
        set(value) {
            paint.color = value
            gradients.fill(null)
            invalidateSelf()
        }
        get() = paint.color

    override fun draw(canvas: Canvas) {
        val cx = bounds.centerX()
        val cy = bounds.bottom - bottomPadding
        val radius = cx / 2 + cx / 2 * progress
        val radius_5 = radius * 2.6F
        val radius_4 = radius * 2.2F
        val radius_3 = radius * 1.8F
        val radius_2 = radius * 1.4F

        if(gradients[0] == null) {
            paint.alpha = 0
            val transparent = paint.color
            paint.alpha = (0.8 * 255).toInt()
            gradients[0] = RadialGradient(cx.toFloat(), cy.toFloat(), radius_2, transparent, paint.color, Shader.TileMode.REPEAT)
            paint.alpha = (0.6 * 255).toInt()
            gradients[1] = RadialGradient(cx.toFloat(), cy.toFloat(), radius_3, transparent, paint.color, Shader.TileMode.REPEAT)
            paint.alpha = (0.4 * 255).toInt()
            gradients[2] = RadialGradient(cx.toFloat(), cy.toFloat(), radius_4, transparent, paint.color, Shader.TileMode.REPEAT)
            paint.alpha = (0.2 * 255).toInt()
            gradients[3] = RadialGradient(cx.toFloat(), cy.toFloat(), radius_5, transparent, paint.color, Shader.TileMode.REPEAT)
            paint.alpha = 255
        }

        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius_5, paint.also { paint.shader = gradients[3] })
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius_4, paint.also { paint.shader = gradients[2] })
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius_3, paint.also { paint.shader = gradients[1] })
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius_2, paint.also { paint.shader = gradients[0] })
        canvas.drawCircle(cx.toFloat(), cy.toFloat(), radius, paint.also { paint.shader = null })
    }

    override fun onBoundsChange(bounds: Rect?) {
        super.onBoundsChange(bounds)
        gradients.fill(null)
    }

    override fun setAlpha(alpha: Int) {}

    override fun setColorFilter(colorFilter: ColorFilter?) {}

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }


    companion object {
        val PROGRESS = object :
            Property<MeditationDrawable, Float>(
                Float::class.java,
                "progress"
            ) {
            override fun get(obj: MeditationDrawable): Float {
                return obj.progress
            }

            override fun set(obj: MeditationDrawable, value: Float) {
                obj.progress = value
            }
        }
    }
}