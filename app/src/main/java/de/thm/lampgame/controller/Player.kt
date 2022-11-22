package de.thm.lampgame.controller

import android.content.Context
import android.graphics.*
import android.media.MediaPlayer
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.PlayerModel

class Player(context: Context, screenHeight: Int, screenWidth: Int) :
    PlayerModel(screenWidth, screenHeight) {
    var rechar = arrayOfNulls<Bitmap>(6)

    var firebarBackgroundRect = Rect()
    var firebarBackgroundPaint = Paint()
    var firebarRect = Rect()
    var firebarPaint = Paint()

    lateinit var char: Array<Bitmap?>


    init {
        Database.listOfSkins.forEach {
            if (it.active) char = it.createSkin(context).getSkin()
        }

        // resize char
        rechar[0] =
            char[0]?.let { Bitmap.createScaledBitmap(it, charwidth, charHeight, true) }
        rechar[1] =
            char[1]?.let { Bitmap.createScaledBitmap(it, charwidth, charHeight, true) }
        rechar[2] =
            char[2]?.let { Bitmap.createScaledBitmap(it, charwidth, charHeight, true) }
        rechar[3] =
            char[3]?.let { Bitmap.createScaledBitmap(it, charwidth, charHeight, true) }
        rechar[4] =
            char[4]?.let { Bitmap.createScaledBitmap(it, charwidth, charHeight, true) }
        rechar[5] =
            char[5]?.let { Bitmap.createScaledBitmap(it, charwidth, charHeight, true) }
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
      firebarBackgroundRect.set(screenWidth/40, screenHeight/4, screenWidth/15, screenHeight-screenHeight/4)
      firebarBackgroundPaint.setARGB(90, 0, 0, 0)
      firebarRect.set(screenWidth/40, (((screenHeight-screenHeight/4)-fire/50*screenHeight/4).toInt()), screenWidth/15, screenHeight-screenHeight/4)
      firebarPaint.setColor(Color.RED)
      canvas.drawRect(firebarBackgroundRect, firebarBackgroundPaint)
      canvas.drawRect(firebarRect, firebarPaint)
    }


    fun groundjumping(context: Context) {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.groundjumping)
        mp.start()
    }

    fun drawPlayer(canvas: Canvas, velocity : Double, collision : Boolean){
        setJumpStats(collision)
        drawFirebar(canvas)
        calkPoints(velocity)
        drawChar(canvas)
    }

}
