package de.thm.lampgame.controller

import android.content.Context
import android.graphics.*
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.PlayerModel
import de.thm.lampgame.view.player.DrawFirebar

class Player(context: Context, screenHeight: Int, screenWidth: Int) : AppCompatActivity() {
    var playerModel = PlayerModel(screenWidth, screenHeight)
    var firebar = DrawFirebar(screenWidth, screenHeight)
    var rechar = arrayOfNulls<Bitmap>(6)
    lateinit var char: Array<Bitmap?>

    init {
        Database.listOfSkins.forEach {
            if (it.itemInfo.active) char = it.createSkin(context).getSkin()
        }

        // resize char
        rechar[0] =
            char[0]?.let { Bitmap.createScaledBitmap(it, playerModel.charWidth, playerModel.charHeight, true) }
        rechar[1] =
            char[1]?.let { Bitmap.createScaledBitmap(it, playerModel.charWidth, playerModel.charHeight, true) }
        rechar[2] =
            char[2]?.let { Bitmap.createScaledBitmap(it, playerModel.charWidth, playerModel.charHeight, true) }
        rechar[3] =
            char[3]?.let { Bitmap.createScaledBitmap(it, playerModel.charWidth, playerModel.charHeight, true) }
        rechar[4] =
            char[4]?.let { Bitmap.createScaledBitmap(it, playerModel.charWidth, playerModel.charHeight, true) }
        rechar[5] =
            char[5]?.let { Bitmap.createScaledBitmap(it, playerModel.charWidth, playerModel.charHeight, true) }
    }


    fun drawChar(canvas: Canvas) {
        rechar[playerModel.calkCharframe()]?.let {
            canvas.drawBitmap(
                it,
                playerModel.charX.toFloat(),
                playerModel.charY.toFloat(),
                null
            )
        }
    }

    fun groundjumping(context: Context) {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.mixkitplayerjumpinginavideogame2043)
        mp.start()
    }

    fun drawPlayer(canvas: Canvas, velocity: Double, collision: Boolean) {
        playerModel.setJumpStats(collision)
        playerModel.calkFire()
        firebar.drawFirebar(canvas, playerModel.fire)
        playerModel.calkPoints(velocity)
        drawChar(canvas)
    }
}
