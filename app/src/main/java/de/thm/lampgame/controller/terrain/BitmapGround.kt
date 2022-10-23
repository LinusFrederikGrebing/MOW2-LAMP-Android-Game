package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class BitmapGround(context: Context,width: Int) : Obstacles(context,200,width,0,840) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.gras2)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true) }

    override fun draw(canvas: Canvas, velocity : Int){
        x -= velocity
        canvas.drawBitmap(bmp, x.toFloat(), this.y.toFloat(), null)
    }
}