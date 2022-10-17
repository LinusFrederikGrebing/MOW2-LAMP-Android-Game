package de.thm.lampgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import java.util.*

class Player(context: Context, val screenHeight : Int, val screenWidth : Int) {
    var char = arrayOfNulls<Bitmap>(3)
    var rechar = arrayOfNulls<Bitmap>(3)
    var charX : Int = 0
    var charY : Int = 0
    var charframe = 0
    var velocity = 0
    var gravity = 5
    var jumpCount = 0
    var jumpState = false
    var birdsneek = false
        get() = field
        set(value){ field = value }

   init {
       // init char-Bitmap
       char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.birdcrack)
       char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.birdcrack2)
       char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.birdsneek)
       // resize char
       rechar[0] = char[0]?.let { Bitmap.createScaledBitmap(it, 200, 150, true) }
       rechar[1] = char[1]?.let { Bitmap.createScaledBitmap(it, 200, 150, true) }
       rechar[2] = char[2]?.let { Bitmap.createScaledBitmap(it, 200, 150, true) }
       charY = screenHeight - 500
       charX = screenWidth / 2 - 600
   }

    fun setjumpStats(){
        if (charY < screenHeight - rechar[0]!!.height-210 || velocity <= 0) {
            velocity += gravity
            charY += velocity

        } else {
            jumpCount = 0
            jumpState = false
        }
    }

    fun drawChar(canvas: Canvas){
        charframe = if(birdsneek) 2 else if (charframe == 1) 0 else 1
        rechar[charframe]?.let { canvas.drawBitmap(it, charX.toFloat(), charY.toFloat(), null) }
    }

    fun sprung(){
        if(jumpCount < 2 && !birdsneek){
            jumpCount++
            velocity = -50
            jumpState = true
        }
    }

}