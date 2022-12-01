package de.thm.lampgame.controller.item

import android.content.Context
import android.graphics.*
import de.thm.lampgame.R

class ActiveItem(val context: Context, val screenWidth: Int, val screenHeight: Int) {
    private var unsizedBmp: Bitmap? = null
    var bmp: Bitmap? = null
    private var activeTexture = 0
    private val backgroundPaint: Paint = Paint()
    private val myPaint: Paint = Paint()
    private var speed: Float = 0.0F

    companion object {
        var texture = R.drawable.himmel
        var speedMultiplier: Float = 360F
    }

    init {
        backgroundPaint.color = Color.WHITE
        activeTexture = texture
        unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        bmp = Bitmap.createScaledBitmap(unsizedBmp!!, screenHeight / 9, screenHeight / 9, true)
    }

    fun draw(canvas: Canvas) {
        if (texture != activeTexture) {
            unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
            bmp = Bitmap.createScaledBitmap(unsizedBmp!!, screenHeight / 9, screenHeight / 9, true)
            activeTexture = texture
        }
        canvas.drawBitmap(
            bmp!!,
            screenWidth * 0.025.toFloat(),
            screenHeight * 0.835.toFloat(),
            null
        )
    }

    fun drawCircle(canvas: Canvas, duration: Int) {
        speed = (360 / duration.toFloat())
        speedMultiplier -= speed

        myPaint.color = if (speedMultiplier > 180) Color.GREEN else if (speedMultiplier < 60) Color.RED else Color.YELLOW

        canvas.drawArc(
            screenWidth * 0.01.toFloat(),
            screenHeight * 0.8.toFloat(),
            screenWidth * 0.1.toFloat(),
            screenHeight * 0.98.toFloat(),
            270F,
            speedMultiplier,
            true,
            myPaint
        )
        canvas.drawArc(
            screenWidth * 0.02.toFloat(),
            screenHeight * 0.82.toFloat(),
            screenWidth * 0.09.toFloat(),
            screenHeight * 0.96.toFloat(),
            0F,
            360F,
            true,
            backgroundPaint
        )
        draw(canvas)
    }
}