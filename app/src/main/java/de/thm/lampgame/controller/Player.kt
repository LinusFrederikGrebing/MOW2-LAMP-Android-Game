package de.thm.lampgame.controller

import android.content.Context
import android.graphics.*
import android.media.MediaPlayer
import de.thm.lampgame.R
import de.thm.lampgame.controller.Skins.BlueLampSkin
import de.thm.lampgame.controller.Skins.LampSkin
import de.thm.lampgame.model.PlayerModel

class Player(context: Context, screenHeight: Int, screenWidth: Int) :
    PlayerModel(screenWidth, screenHeight) {

    var rechar = arrayOfNulls<Bitmap>(6)
    var firebar = arrayOfNulls<Bitmap>(5)

    var firebarBackgroundRect = Rect()
    var firebarBackgroundPaint = Paint()
    var firebarRect = Rect()
    var firebarPaint = Paint()

     var lampSkin = LampSkin(context)
     var blueLampSkinn = BlueLampSkin(context)
     var char = if(BlueLampSkin.active) blueLampSkinn.getSkin() else lampSkin.getSkin()


    init {

        // init firebar-Bitmap
        firebar[0] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar100)
        firebar[1] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar80)
        firebar[2] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar60)
        firebar[3] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar40)
        firebar[4] = BitmapFactory.decodeResource(context.resources, R.drawable.firebar20)

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

//Altenative Version.
  /*  fun drawFirebar(canvas: Canvas) {
        firebar[calkFirebar()]?.let {
            canvas.drawBitmap(
                it,
                0.toFloat(),
                (screenHeight / 6).toFloat(),
                null
            )
        }
    }
    */
//Neue Version.
    fun drawFirebar(canvas: Canvas) {
      firebarBackgroundRect.set(0+screenWidth/40, screenHeight/4, 0+screenWidth/15, screenHeight-screenHeight/4)
      firebarBackgroundPaint.setARGB(90, 0, 0, 0)
      firebarRect.set(0+screenWidth/40, (((screenHeight-screenHeight/4)-fire/50*screenHeight/4).toInt()), 0+screenWidth/15, screenHeight-screenHeight/4)
      firebarPaint.setColor(Color.RED)
      canvas.drawRect(firebarBackgroundRect, firebarBackgroundPaint)
      canvas.drawRect(firebarRect, firebarPaint)
    }


    fun groundjumping(context: Context) {
        val mp: MediaPlayer = MediaPlayer.create(context, R.raw.groundjumping)
        mp.start()
    }

}
