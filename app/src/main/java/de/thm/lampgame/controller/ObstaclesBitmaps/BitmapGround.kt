package de.thm.lampgame.controller.ObstaclesBitmaps

import  android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class BitmapGround(context: Context, width: Int, height: Int) : Obstacles("ground",
    width,
    (0.13 * height).toInt(),
    0,
    (0.80 * height).toInt(),
    false
) {
    companion object {
        var texture = R.drawable.tilesground
    }

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, this.width, this.height, true)
    }

    override fun draw(canvas: Canvas, velocityX: Int, velocityY: Int) {
        changeableX -= velocityX
        canvas.drawBitmap(bmp, changeableX.toFloat(), changeableY.toFloat(), null)
    }
}