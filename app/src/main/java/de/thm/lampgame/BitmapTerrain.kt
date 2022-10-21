package de.thm.lampgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import java.util.*

class BitmapTerrain(context: Context,width: Int, x: Int, y: Int) : Obstacles(context,100,width,x,y) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.gras2)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true) }

    override fun draw(canvas: Canvas, velocity : Int){
        x -= velocity
        canvas.drawBitmap(bmp, x.toFloat(), this.y.toFloat(), null)
    }

}