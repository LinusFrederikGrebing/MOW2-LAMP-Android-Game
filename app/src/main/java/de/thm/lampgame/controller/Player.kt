package de.thm.lampgame.controller

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.media.MediaPlayer
import de.thm.lampgame.R
import de.thm.lampgame.model.PlayerModel

class Player(context: Context, screenHeight: Int, screenWidth: Int) :
    PlayerModel(screenWidth, screenHeight) {
    var char = arrayOfNulls<Bitmap>(6)
    var rechar = arrayOfNulls<Bitmap>(6)
    var firebar = arrayOfNulls<Bitmap>(5)

    init {
        // init char-Bitmap
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_left)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.innen)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_right)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.aussen)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_sneek)
        char[5] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_jump_fire)

        // init firebar-Bitmap
        firebar[0] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar100)
        firebar[1] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar80)
        firebar[2] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar60)
        firebar[3] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar40)
        firebar[4] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar20)

        // resize char
        rechar[0] =
            char[0]?.let { Bitmap.createScaledBitmap(it, getCharWidth(), getCharHeight(), true) }
        rechar[1] =
            char[1]?.let { Bitmap.createScaledBitmap(it, getCharWidth(), getCharHeight(), true) }
        rechar[2] =
            char[2]?.let { Bitmap.createScaledBitmap(it, getCharWidth(), getCharHeight(), true) }
        rechar[3] =
            char[3]?.let { Bitmap.createScaledBitmap(it, getCharWidth(), getCharHeight(), true) }
        rechar[4] =
            char[4]?.let { Bitmap.createScaledBitmap(it, getCharWidth(), getCharHeight(), true) }
        rechar[5] =
            char[5]?.let { Bitmap.createScaledBitmap(it, getCharWidth(), getCharHeight(), true) }
    }


    fun drawChar(canvas: Canvas) {
        rechar[calkCharframe()]?.let {
            canvas.drawBitmap(
                it,
                charX.toFloat(),
                charY.toFloat(),
                null
            )
        }
    }

    fun drawFirebar(canvas: Canvas) {
        firebar[calkFirebar()]?.let {
            canvas.drawBitmap(
                it,
                0.toFloat(),
                (screenHeight / 6).toFloat(),
                null
            )
        }
    }

    fun groundjumping(context: Context) {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.groundjumping)
        mp.start()
    }

}