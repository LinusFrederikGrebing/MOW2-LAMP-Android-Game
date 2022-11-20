package de.thm.lampgame.controller

import android.content.Context
import android.graphics.*
import de.thm.lampgame.R

class ActiveItem(val context: Context, val screenWidth: Int, val screenHeight: Int) {
    private var unsizedBmp: Bitmap? = null
    var bmp: Bitmap? = null
    private var unsizedbgBmp: Bitmap? = null
    var bmpbg: Bitmap? = null
    var activetexture = 0
    companion object{
        var texture = R.drawable.himmel
    }
init {
    activetexture = texture
    unsizedbgBmp = BitmapFactory.decodeResource(context.resources,  R.drawable.itembg)
    bmpbg = Bitmap.createScaledBitmap(unsizedbgBmp!!, screenHeight / 6, screenHeight / 6, true)
    unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
    bmp = Bitmap.createScaledBitmap(unsizedBmp!!, screenHeight / 7, screenHeight / 7, true)
}

    fun drawbg(canvas: Canvas){
        canvas.drawBitmap(bmpbg!!, screenWidth*0.01.toFloat(), screenHeight*0.8.toFloat(), null)
    }

    fun draw(canvas: Canvas) {
        if(texture != activetexture ) {
            unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
            bmp = Bitmap.createScaledBitmap(unsizedBmp!!, screenHeight / 7, screenHeight / 7, true)
            activetexture = texture
        }
        canvas.drawBitmap(bmp!!, screenWidth*0.015.toFloat(), screenHeight*0.82.toFloat(), null)
    }

}