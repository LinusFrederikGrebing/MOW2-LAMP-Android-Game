package de.thm.lampgame.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Rect
import de.thm.lampgame.R

class PauseButton(context: Context, val screenWidth: Int, val screenHeight: Int) {
    private var unsizedBmp: Bitmap
    var bmp: Bitmap
    var hitbox: Rect

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources,  R.drawable.pausebutton)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, screenHeight / 10, screenHeight / 10, true)
        hitbox = Rect(screenWidth - screenWidth / 16,screenWidth / 16 - screenHeight / 10,(screenWidth - screenWidth / 16)+(screenHeight / 10),(screenWidth / 16 - screenHeight / 10)+(screenHeight / 10))
    }

    fun draw(canvas: Canvas) {
        canvas.drawBitmap(bmp, (screenWidth - screenWidth / 16).toFloat(), (screenWidth / 16 - screenHeight / 10).toFloat(), null)
    }

    fun checkIfClicked(x: Float, y: Float) : Boolean {
        val clickedRect = Rect(x.toInt(),y.toInt(),x.toInt(),y.toInt())
        return Rect.intersects(clickedRect,hitbox)
    }
}