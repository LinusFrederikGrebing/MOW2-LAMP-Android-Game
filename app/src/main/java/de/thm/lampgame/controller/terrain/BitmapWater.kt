package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class BitmapWater(context: Context, width: Int, height: Int, x: Int, y: Int) :
    Obstacles("water", width,(0.13 * height).toInt(), x, (0.8 * y).toInt(), true) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.wasser)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, this.width, this.height, true)
    }

    override fun draw(canvas: Canvas, velocity: Int) {
        changeableX -= velocity
        canvas.drawBitmap(bmp, changeableX.toFloat(), y.toFloat(), null)
    }
}
