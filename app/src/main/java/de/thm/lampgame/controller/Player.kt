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
    val firebarPositionLeft = screenWidth/40
    val firebarPositionTop = screenHeight/4
    val firebarPositionRight = screenWidth/15
    val firebarPositionBottom = screenHeight-screenHeight/4
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
        firebarBackgroundRect.set(firebarPositionLeft, firebarPositionTop, firebarPositionRight, firebarPositionBottom)
        firebarBackgroundPaint.setARGB(90, 0, 0, 0)
        firebarRect.set(firebarPositionLeft, (((firebarPositionBottom)-fire/50*firebarPositionTop).toInt()), firebarPositionRight, firebarPositionBottom)
        firebarPaint.setShader(LinearGradient(firebarPositionLeft.toFloat(),firebarPositionTop.toFloat(),firebarPositionTop.toFloat(),firebarPositionBottom.toFloat(), Color.GREEN,Color.RED, Shader.TileMode.CLAMP))
        canvas.drawRect(firebarBackgroundRect, firebarBackgroundPaint)
        canvas.drawRect(firebarRect, firebarPaint)
    }


    fun groundjumping(context: Context) {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.mixkitplayerjumpinginavideogame2043)
        mp.start()
    }

    fun drawPlayer(canvas: Canvas, velocity : Double, collision : Boolean){
        setJumpStats(collision)
        drawFirebar(canvas)
        calkFire()
        calkPoints(velocity)
        drawChar(canvas)
    }

}
