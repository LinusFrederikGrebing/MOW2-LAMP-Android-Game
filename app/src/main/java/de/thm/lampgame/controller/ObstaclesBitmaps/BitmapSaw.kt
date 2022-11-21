package de.thm.lampgame.controller.ObstaclesBitmaps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.ObstacleNames


class BitmapSaw(context: Context, width: Int, height: Int, x: Int, y: Int) :
    Obstacles(ObstacleNames.SAW, (0.08 * width).toInt(),(0.15 * height).toInt(), x, y, true) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.saw)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, this.width, this.height, true)
    }

    override fun draw(canvas: Canvas, velocityX: Int, velocityY: Int){
        changeableX -= (velocityX*1.5).toInt()
        canvas.drawBitmap(bmp, changeableX.toFloat(), changeableY.toFloat(), null)
    }
}
