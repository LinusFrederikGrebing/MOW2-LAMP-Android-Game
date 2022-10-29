package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class BitmapTube(context: Context, screenWidth: Int, screenHeight: Int, x: Int, y: Int) :
    Obstacles(context, (0.40 * screenHeight).toInt(), (0.08 * screenWidth).toInt(), x, y, false) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.bottomtube)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true)
    }

    override fun draw(canvas: Canvas, velocity: Int) {
        changeableX -= velocity
        canvas.drawBitmap(bmp, changeableX.toFloat(), y.toFloat(), null)
    }
}
