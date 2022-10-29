package de.thm.lampgame.controller.terrain

import  android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class BitmapGround(context: Context, screenWidth: Int, screenHeight: Int) : Obstacles(
    context,
    (0.13 * screenHeight).toInt(),
    screenWidth,
    0,
    (0.80 * screenHeight).toInt(),
    false
) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.bodengras)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true)
    }

    override fun draw(canvas: Canvas, velocity: Int) {
        changeableX -= velocity
        canvas.drawBitmap(bmp, changeableX.toFloat(), this.y.toFloat(), null)
    }
}