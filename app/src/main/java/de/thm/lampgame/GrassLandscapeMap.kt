package de.thm.lampgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.view.View

class GrassLandscapeMap(context: Context, screenHeight : Int, private val screenWidth: Int) : View(context) {
    var sky: Bitmap
    var mountain: Bitmap
    var clouds: Bitmap
    var grass: Bitmap
    var newWidth = 0
    var newHeigt = 0
    var cloudX = 0.0
    var skyX = 0
    var grassX = 0
    var grassY = 850f
    var mountainX = 0


    init {
        clouds = BitmapFactory.decodeResource(context.resources, R.drawable.berghinten)
        sky = BitmapFactory.decodeResource(context.resources, R.drawable.backgroudn)
        grass = BitmapFactory.decodeResource(context.resources, R.drawable.gras2)
        mountain= BitmapFactory.decodeResource(context.resources, R.drawable.bergvorne)
        val height = clouds.getHeight().toFloat()
        val width = clouds.getWidth().toFloat()
        val ratio = width / height
        newHeigt = screenHeight
        newWidth = (ratio * screenHeight).toInt()
        grass = Bitmap.createScaledBitmap(grass, newWidth, 200, true)
        clouds = Bitmap.createScaledBitmap(clouds, newWidth, newHeigt, false)
        sky = Bitmap.createScaledBitmap(sky, newWidth, newHeigt, false)
        mountain = Bitmap.createScaledBitmap(mountain, newWidth, newHeigt, false)
    }

    fun drawSky(canvas: Canvas) {
        canvas.drawBitmap(sky, skyX.toFloat(), 0f, null)
    }

    fun drawClouds(canvas: Canvas, speed: Double) {

        cloudX -= speed
         if (cloudX < -newWidth) { cloudX = 0.0 }

            canvas.drawBitmap(clouds, cloudX.toFloat(), 0f, null)

            if (cloudX < screenWidth - newWidth) {
               canvas.drawBitmap(clouds, (cloudX + newWidth).toFloat(), 0f, null)
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

    fun drawGrass(canvas: Canvas, speed : Int) {
        grassX -= speed
        if (grassX < -newWidth) grassX = 0

         canvas.drawBitmap(grass, grassX.toFloat(), grassY, null)
        if (grassX < screenWidth - newWidth) {
            canvas.drawBitmap(grass, (grassX + newWidth).toFloat(), grassY, null)
        }
    }


    }






