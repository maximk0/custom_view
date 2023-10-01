package com.example.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class DrawTextView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private val fontPaint: Paint
    private val redPaint: Paint = Paint()
    private val text = "Test width text"
    private val fontSize = 100f
    private val widths: FloatArray
    private val width: Float

    init {
        redPaint.color = Color.RED
        fontPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        fontPaint.textSize = fontSize
        fontPaint.style = Paint.Style.STROKE
        fontPaint.color = Color.RED

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

        // вывод текста
        canvas.drawText(text, centerX - width / 2f, centerY, fontPaint)
    }
}

