package de.thm.lampgame.controller.item

import android.content.Context
import android.graphics.*

class ActiveItem(val context: Context, val screenWidth: Int, val screenHeight: Int, val texture: Int, itemDuration: Int, val newPosition: Float) {
    private var unsizedBmp: Bitmap
    var bmp: Bitmap
    private val backgroundPaint: Paint = Paint()
    private val myPaint: Paint = Paint()
    private var speed: Float = (360 / itemDuration.toFloat())

    var proportion: Float = 360F


    init {
        backgroundPaint.color = Color.WHITE
        unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, screenHeight / 9, screenHeight / 9, true)
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(
            bmp,
            screenWidth * 0.45.toFloat() + newPosition,
            screenHeight * 0.08.toFloat(),
            null
        )
    }

    fun drawCircle(canvas: Canvas) {
        proportion -= speed
        myPaint.color =
            if (proportion > 180) Color.GREEN else if (proportion < 60) Color.RED else Color.YELLOW

        canvas.drawArc(
            screenWidth * 0.433.toFloat() + newPosition,
            screenHeight * 0.05.toFloat(),
            screenWidth * 0.523.toFloat() + newPosition,
            screenHeight * 0.22.toFloat(),
            270F,
            proportion,
            true,
            myPaint
        )
        canvas.drawArc(
            screenWidth * 0.443.toFloat() + newPosition,
            screenHeight * 0.07.toFloat(),
            screenWidth * 0.513.toFloat() + newPosition,
            screenHeight * 0.20.toFloat(),
            0F,
            360F,
            true,
            backgroundPaint
        )
        draw(canvas)
    }
}