package de.thm.lampgame.controller.item

import android.content.Context
import android.graphics.*

class ActiveItem(val context: Context, val screenWidth: Int, val screenHeight: Int, val texture: Int, itemDuration: Int, private val newPosition: Float) {
    private var unsizedBmp: Bitmap
    var bmp: Bitmap
    // the paint is used to change the color of the object to be drawn
    private val backgroundPaint: Paint = Paint()
    private val myPaint: Paint = Paint()
    // the speed describes the item speed in relation to a circle -> 360 degrees.
    // Depending on the item duration, the item progress bar must run at different speeds
    private var speed: Float = (360 / itemDuration.toFloat())
   // proportion describes the current proportion of the circle
   private var proportion: Float = 360F

    // for the background arc, the color is set to white and the object texture is determined by the texture parameter
    init {
        backgroundPaint.color = Color.WHITE
        unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, screenHeight / 9, screenHeight / 9, true)
    }

    fun resetProportion() {
        proportion = 360F
    }

    // draws the item
    // newPosition represents a changed position on the x-axis and is set upon ActiveItem initialization
    private fun drawItem(canvas: Canvas) {
        canvas.drawBitmap(
            bmp,
            screenWidth * 0.45.toFloat() + newPosition,
            screenHeight * 0.08.toFloat(),
            null
        )
    }

    // draws the arc progress bar and an icon background
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
        drawItem(canvas)
    }
}