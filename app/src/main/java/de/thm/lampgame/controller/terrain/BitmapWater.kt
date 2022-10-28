package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class BitmapWater(context: Context, newWidth : Int, newHeight : Int, x: Int, y: Int) : Obstacles(context,newHeight, newWidth ,x,y, true) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.wasser)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true)
    }

    override fun draw(canvas: Canvas,  velocity : Int){
        changeableX -= velocity
            canvas.drawBitmap(bmp, changeableX.toFloat(), y.toFloat(), null)
    }
}
