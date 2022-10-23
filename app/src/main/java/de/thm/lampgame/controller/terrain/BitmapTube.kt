package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class BitmapTube(context: Context, x: Int, y: Int) : Obstacles(context,400,150,x,y) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.bottomtube)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true)
    }

    override fun draw(canvas: Canvas,  velocity : Int){
            x -= velocity
            canvas.drawBitmap(bmp, x.toFloat(), y.toFloat(), null)
    }
}
