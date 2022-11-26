package de.thm.lampgame.view.item

import android.content.Context
import android.graphics.*
import de.thm.lampgame.R

class ActiveItem(val context: Context, val screenWidth: Int, val screenHeight: Int) {
    private var unsizedBmp: Bitmap? = null
    var bmp: Bitmap? = null
    var activetexture = 0
    private val backgroundPaint: Paint = Paint()
    private val myPaint: Paint = Paint()

    var speed: Float = 0.0F

    companion object {
        var texture = R.drawable.himmel
        var speedMultiplyer: Float = 360F
    }

    init {
        backgroundPaint.color = Color.WHITE
        activetexture = texture
        unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        bmp = Bitmap.createScaledBitmap(unsizedBmp!!, screenHeight / 9, screenHeight / 9, true)
    }

    fun draw(canvas: Canvas) {
        if (texture != activetexture) {
            unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
            bmp = Bitmap.createScaledBitmap(unsizedBmp!!, screenHeight / 9, screenHeight / 9, true)
            activetexture = texture
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
        speedMultiplyer -= speed

        myPaint.color = if (speedMultiplyer > 180) Color.GREEN else if (speedMultiplyer < 60) Color.RED else Color.YELLOW

        canvas.drawArc(
            screenWidth * 0.01.toFloat(),
            screenHeight * 0.8.toFloat(),
            screenWidth * 0.1.toFloat(),
            screenHeight * 0.98.toFloat(),
            270F,
            speedMultiplyer,
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