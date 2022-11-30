package de.thm.lampgame.view.player

import android.content.Context
import android.graphics.*
import de.thm.lampgame.R

class DrawTorchCount(context: Context, val screenWidth : Int, val screenHeight : Int) {
    var unsizedBmp: Bitmap
    var bmp: Bitmap
    private val paint = Paint()

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.torch)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, (screenWidth*0.05).toInt(), (screenHeight*0.1).toInt(), true)
        paint.color = Color.BLACK
        paint.textSize = screenHeight*0.075.toFloat()
    }

    fun draw(canvas: Canvas, coinsperRound : Int){
        canvas.drawText(coinsperRound.toString(),  (screenWidth*0.02).toFloat(), (screenHeight*0.175).toFloat(), paint)
        canvas.drawBitmap(bmp, (screenWidth*0.05).toFloat(), (screenHeight*0.1).toFloat(), null)
    }
}