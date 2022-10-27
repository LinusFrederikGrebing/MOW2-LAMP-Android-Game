package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R


class BitmapTerrain(context: Context, width: Int, screenHeight: Int, x: Int, y: Int) : Obstacles(context, (0.10*screenHeight).toInt(),width, x,y, false) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.plattform2)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true) }

    override fun draw(canvas: Canvas, velocity : Int){
        x -= velocity
        canvas.drawBitmap(bmp, x.toFloat(), this.y.toFloat(), null)
    }

}