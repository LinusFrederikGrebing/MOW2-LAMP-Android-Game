package de.thm.lampgame.controller.player

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.model.PlayerModel
import de.thm.lampgame.model.shop.Database

class Player(context: Context, screenHeight: Int, screenWidth: Int) : AppCompatActivity() {
    // creates the associated model
    var playerModel = PlayerModel(screenWidth, screenHeight)
    // creates an object of the firebar to draw it with the player's current fire
    private var firebar = DrawFirebar(screenWidth, screenHeight)

    var rechar = arrayOfNulls<Bitmap>(6)
    private lateinit var char: Array<Bitmap?>

    init {
        // checks which skin is active and returns the respective char skin sequence
        Database.listOfSkins.forEach {
            if (it.itemInfo.active) char = it.createSkin(context).getSkin()
        }

        // resize char
        // -> get the values from the associated model
        rechar[0] =
            char[0]?.let {
                Bitmap.createScaledBitmap(
                    it,
                    playerModel.charWidth,
                    playerModel.charHeight,
                    true
                )
            }
        rechar[1] =
            char[1]?.let {
                Bitmap.createScaledBitmap(
                    it,
                    playerModel.charWidth,
                    playerModel.charHeight,
                    true
                )
            }
        rechar[2] =
            char[2]?.let {
                Bitmap.createScaledBitmap(
                    it,
                    playerModel.charWidth,
                    playerModel.charHeight,
                    true
                )
            }
        rechar[3] =
            char[3]?.let {
                Bitmap.createScaledBitmap(
                    it,
                    playerModel.charWidth,
                    playerModel.charHeight,
                    true
                )
            }
        rechar[4] =
            char[4]?.let {
                Bitmap.createScaledBitmap(
                    it,
                    playerModel.charWidth,
                    playerModel.charHeight,
                    true
                )
            }
        rechar[5] =
            char[5]?.let {
                Bitmap.createScaledBitmap(
                    it,
                    playerModel.charWidth,
                    playerModel.charHeight,
                    true
                )
            }
    }


    private fun drawChar(canvas: Canvas) {
        // calkCharFrame returns the current characters skin frequency
        // draws the character at the position given by the model
        rechar[playerModel.calkCharFrame()]?.let {
            canvas.drawBitmap(
                it,
                playerModel.charX.toFloat(),
                playerModel.charY.toFloat(),
                null
            )
        }
    }

    // plays a short jump music
    fun groundJumping(context: Context) {
        if(playerModel.jumpCount < playerModel.maxJump) {
            val mp: MediaPlayer = MediaPlayer.create(context, R.raw.jump_sound)
            mp.start()
        }
    }

    // draws everything important related to the character and calculates values like the current points or the current fire
    fun drawPlayer(canvas: Canvas, velocity: Double, collision: Boolean) {
        playerModel.setJumpStats(collision)
        playerModel.calkFire()
        firebar.drawFirebar(canvas, playerModel.fire)
        playerModel.calkPoints(velocity)
        drawChar(canvas)
    }
}
