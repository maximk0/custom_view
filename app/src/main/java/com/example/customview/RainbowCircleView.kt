package com.example.customview

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator

class RainbowCircleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint()
    private val animator: ObjectAnimator

    init {
        paint.style = Paint.Style.FILL
        animator = ObjectAnimator.ofFloat(this, "rotation", 0f, getRandomRotation())
        animator.interpolator = LinearInterpolator()
        animator.duration = 1000
    }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2f
        val centerY = height / 2f
        val radius = minOf(centerX, centerY)
        val sweepAngle = 360f / colors.size

        for (i in colors.indices) {
            paint.color = colors[i]
            canvas.drawArc(
                centerX - radius, centerY - radius, centerX + radius, centerY + radius,
                i * sweepAngle, sweepAngle, true, paint
            )
        }
        super.onDraw(canvas)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN && !animator.isRunning) {
            animator.setFloatValues(rotation, rotation + getRandomRotation())
            animator.start()
        }
        return super.onTouchEvent(event)
    }


    fun spin() {
        invalidate()
    }

    private fun getRandomRotation(): Float = (200..720).random().toFloat()

    fun getColor() = SelectedColor.values().random()

    companion object {
        private const val ORANGE = "#FFA500"
        private const val PURPLE = "#800080"
        private val colors = arrayOf(
            Color.RED,
            Color.parseColor(ORANGE),
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.BLUE,
            Color.parseColor(PURPLE)
        )

    }
}


