package de.thm.lampgame.view.player

import android.graphics.*

class DrawFirebar(screenWidth: Int, screenHeight: Int) {
    private var firebarBackgroundRect = Rect()
    private var firebarBackgroundPaint = Paint()
    private var firebarRect = Rect()
    private var firebarPaint = Paint()
    private val firebarPositionLeft = screenWidth / 40
    private val firebarPositionTop = screenHeight / 4
    private val firebarPositionRight = screenWidth / 15
    private val firebarPositionBottom = screenHeight - screenHeight / 4

    fun drawFirebar(canvas: Canvas, fire : Float) {
        firebarBackgroundRect.set(
            firebarPositionLeft,
            firebarPositionTop,
            firebarPositionRight,
            firebarPositionBottom
        )
        firebarBackgroundPaint.setARGB(90, 0, 0, 0)
        firebarRect.set(
            firebarPositionLeft,
            (((firebarPositionBottom) - fire / 50 * firebarPositionTop).toInt()),
            firebarPositionRight,
            firebarPositionBottom
        )
        firebarPaint.shader = LinearGradient(
            firebarPositionLeft.toFloat(),
            firebarPositionTop.toFloat(),
            firebarPositionTop.toFloat(),
            firebarPositionBottom.toFloat(),
            Color.YELLOW,
            Color.RED,
            Shader.TileMode.CLAMP
        )
        canvas.drawRect(firebarBackgroundRect, firebarBackgroundPaint)
        canvas.drawRect(firebarRect, firebarPaint)
    }
}