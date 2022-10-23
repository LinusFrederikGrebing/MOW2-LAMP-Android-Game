package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class GrassLandscapeMap(context: Context, screenHeight : Int, private val screenWidth: Int)  {
    var mountain: Bitmap
    var background: Bitmap
    var newWidth = 0
    var newHeigt = 0
    var cloudX = 0.0
    var mountainX = 0


    init {
        background = BitmapFactory.decodeResource(context.resources, R.drawable.berghinten)
        mountain= BitmapFactory.decodeResource(context.resources, R.drawable.bergvorne)
        val height = background.getHeight().toFloat()
        val width = background.getWidth().toFloat()
        val ratio = width / height
        newHeigt = screenHeight
        newWidth = (ratio * screenHeight).toInt()
        background = Bitmap.createScaledBitmap(background, newWidth, newHeigt, false)
        mountain = Bitmap.createScaledBitmap(mountain, newWidth, newHeigt, false)
    }

    fun drawClouds(canvas: Canvas, speed: Double) {
        cloudX -= speed
         if (cloudX < -newWidth) { cloudX = 0.0 }
            canvas.drawBitmap(background, cloudX.toFloat(), 0f, null)
            if (cloudX < screenWidth - newWidth) {
               canvas.drawBitmap(background, (cloudX + newWidth).toFloat(), 0f, null)
            }
    }

    fun drawMountains(canvas: Canvas, speed : Int) {
        mountainX -= speed
        if (mountainX < -newWidth) { mountainX = 0 }
          canvas.drawBitmap(mountain, mountainX.toFloat(), 0f, null)
        if (mountainX < screenWidth - newWidth) {
             canvas.drawBitmap(mountain, (mountainX + newWidth).toFloat(), 0f, null)
        }
    }
}






