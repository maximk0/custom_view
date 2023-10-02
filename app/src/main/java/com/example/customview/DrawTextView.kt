package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawTextView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val fontPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val fontSize = 100f
    private val widths: FloatArray
    private val width: Float

    var text = "DrawTextView"
    private var color = Color.GRAY

    init {
        fontPaint.textSize = fontSize
        fontPaint.style = Paint.Style.STROKE

        // ширина текста
        width = fontPaint.measureText(text)

        // посимвольная ширина
        widths = FloatArray(text.length)
        fontPaint.getTextWidths(text, widths)
    }

    override fun onDraw(canvas: Canvas) {
        val centerX = width / 2f
        val textHeight = fontPaint.descent() - fontPaint.ascent()
        val centerY = textHeight / 2f + 50

        fontPaint.color = color

        // вывод текста
        canvas.drawText(text, centerX - width / 2f, centerY, fontPaint)
    }

    fun setColor(selectedColor: SelectedColor) {
        color =  when(selectedColor) {
            SelectedColor.RED -> Color.RED
            SelectedColor.ORANGE -> Color.parseColor("#FFA500")
            SelectedColor.YELLOW -> Color.YELLOW
            SelectedColor.GREEN -> Color.GREEN
            SelectedColor.CYAN -> Color.CYAN
            SelectedColor.BLUE -> Color.BLUE
            SelectedColor.PURPLE -> Color.parseColor("#800080")
        }
    }
}

