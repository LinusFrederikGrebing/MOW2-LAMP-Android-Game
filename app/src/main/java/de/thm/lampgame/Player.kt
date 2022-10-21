package de.thm.lampgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.util.Log
import java.util.*

class Player(context: Context, val screenHeight : Int, val screenWidth : Int) {
    var char = arrayOfNulls<Bitmap>(6)
    var rechar = arrayOfNulls<Bitmap>(6)
    var firebar = arrayOfNulls<Bitmap>(5)
    var charframe_firebar = 0
    var points = 0
    var fire: Float = 100F
    var charX : Int = 0
    var charY : Int = 0
    var charframe = 0
    var velocity = 0
    var gravity = 3
    var jumpCount = 0
    var jumpState = false
        get() = field
        set(value){ field = value }
    var birdsneek = false
        get() = field
        set(value){ field = value }

   init {
       // init char-Bitmap
       char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_idle)
       char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_walk1)
       char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_walk2)
       char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_walk3)
       char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_sneek)
       char[5] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_jump_fire)

       // init firebar-Bitmap
       firebar[0] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar100)
       firebar[1] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar80)
       firebar[2] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar60)
       firebar[3] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar40)
       firebar[4] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar20)

       // resize char
       rechar[0] = char[0]?.let { Bitmap.createScaledBitmap(it, 200, 200, true) }
       rechar[1] = char[1]?.let { Bitmap.createScaledBitmap(it, 200, 200, true) }
       rechar[2] = char[2]?.let { Bitmap.createScaledBitmap(it, 200, 200, true) }
       rechar[3] = char[3]?.let { Bitmap.createScaledBitmap(it, 200, 200, true) }
       rechar[4] = char[4]?.let { Bitmap.createScaledBitmap(it, 200, 200, true) }
       rechar[5] = char[5]?.let { Bitmap.createScaledBitmap(it, 200, 200, true) }
       charY = screenHeight - 500
       charX = screenWidth / 2 - 600
   }

    fun setjumpStats(collision : Boolean){
        if (!collision  || velocity <= 0) {
            jumpState = true
            velocity += gravity
            charY += velocity

        } else {
            jumpCount = 0
            jumpState = false

        }

    }

    fun drawChar(canvas: Canvas){
        charframe = if(birdsneek) 4 else if(jumpState) 5 else if (charframe == 0) 1 else if(charframe == 1) 2 else if(charframe == 2) 3 else 0
        rechar[charframe]?.let { canvas.drawBitmap(it, charX.toFloat(), charY.toFloat(), null) }
        points++
        fire = fire - 0.1F
    }
    fun drawFirebar(canvas: Canvas) {
        charframe_firebar = if(fire > 80) 0 else if (fire > 60) 1 else if (fire > 40) 2 else if (fire > 20) 3 else 4
        firebar[charframe_firebar]?.let {canvas.drawBitmap(it, (screenWidth - screenWidth).toFloat(), (screenHeight/6).toFloat(), null)}
    }

    fun sprung(){
        if(jumpCount < 2 && !birdsneek){
            jumpCount++
            velocity = -45
            jumpState = true
        }
    }

}