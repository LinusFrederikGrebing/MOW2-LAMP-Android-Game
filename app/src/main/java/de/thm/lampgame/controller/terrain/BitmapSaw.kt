package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R


class BitmapSaw(context: Context, width: Int, height: Int, x: Int, y: Int) :
    Obstacles("saw", (0.1 * width).toInt(),(0.2 * height).toInt(), x, y, true) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.saw)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, this.width, this.height, true)
    }

    override fun draw(canvas: Canvas, velocityX: Int, velocityY: Int){
        changeableX -= velocityX*2
        canvas.drawBitmap(bmp, changeableX.toFloat(), changeableY.toFloat(), null)
    }
}
