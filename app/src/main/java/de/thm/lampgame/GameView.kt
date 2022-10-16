package de.thm.lampgame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Handler
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat.startActivity


class GameView(context: Context?) : View(context) {
    var screenWidth = 0
    var screenHeight = 0
    var newWidth = 0
    var newHeigt = 0
    var cloudX = 0
    var skyX = 0
    var grassX = 0
    var mountainX = 0
    var jumpState = false

    var charX = 0
    var charY = 0
    var charframe = 0
    var sky: Bitmap
    var mountain: Bitmap
    var clouds: Bitmap
    var grass: Bitmap
    var char = arrayOfNulls<Bitmap>(2)
    var rechar: Array<Bitmap?>
    var runnable: Runnable? = null
    val UPDATE_MILLIS: Long = 30
    var velocity = 0
    var gravity = 5
    var jumpCount = 0
    var points = 0
    var multiplyer = 0

    init {
        clouds = BitmapFactory.decodeResource(resources, R.drawable.clouds3)
        sky = BitmapFactory.decodeResource(resources, R.drawable.himmel)
        grass = BitmapFactory.decodeResource(resources, R.drawable.gras2)
        mountain= BitmapFactory.decodeResource(resources, R.drawable.berge)
        char[0] = BitmapFactory.decodeResource(resources, R.drawable.birdcrack)
        char[1] = BitmapFactory.decodeResource(resources, R.drawable.birdcrack2)
        rechar = arrayOfNulls(2)
        rechar[0] = char[0]?.let { Bitmap.createScaledBitmap(it, 200, 150, true) }
        rechar[1] = char[1]?.let { Bitmap.createScaledBitmap(it, 200, 150, true) }
        val display = (getContext() as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        val height = sky.getHeight().toFloat()
        val width = sky.getWidth().toFloat()
        val ratio = width / height
        newHeigt = screenHeight
        newWidth = (ratio * screenHeight).toInt()
        grass = Bitmap.createScaledBitmap(grass, newWidth, newHeigt, false)
        clouds = Bitmap.createScaledBitmap(clouds, newWidth, newHeigt, false)
        sky = Bitmap.createScaledBitmap(sky, newWidth, newHeigt, false)
        mountain = Bitmap.createScaledBitmap(mountain, newWidth, newHeigt, false)
        charX = screenWidth / 2 - 200
        charY = screenHeight - 500
        var handler: Handler? = Handler()
        runnable = Runnable { invalidate() }
    }



    override fun onDraw(canvas: Canvas) {
        points++
        if(points == 1000){
            this.gameover()
        }
        multiplyer = 0
        super.onDraw(canvas)

        skyX -= 1 + multiplyer
        if (skyX < -newWidth) {
            skyX = 0
        }
        canvas.drawBitmap(sky!!, skyX.toFloat(), 0f, null)
        if (skyX < screenWidth - newWidth) {
            canvas.drawBitmap(sky!!, (skyX + newWidth).toFloat(), 0f, null)
        }
        cloudX -= 2 + multiplyer
        if (cloudX < -newWidth) {
            cloudX = 0
        }
        canvas.drawBitmap(clouds, cloudX.toFloat(), 0f, null)
        if (cloudX < screenWidth - newWidth) {
            canvas.drawBitmap(clouds, (cloudX + newWidth).toFloat(), 0f, null)
        }
        mountainX -= 1   + multiplyer
        if (mountainX < -newWidth) {
            mountainX = 0
        }
        canvas.drawBitmap(mountain, mountainX.toFloat(), 0f, null)
        if (mountainX < screenWidth - newWidth) {
            canvas.drawBitmap(mountain, (mountainX + newWidth).toFloat(), 0f, null)
        }

        grassX -= 10 + multiplyer
        if (grassX < -newWidth) {
            grassX = 0
        }
        canvas.drawBitmap(grass, grassX.toFloat(), 0f, null)
        if (grassX < screenWidth - newWidth) {
            canvas.drawBitmap(grass, (grassX + newWidth).toFloat(), 0f, null)
        }




        if (charframe == 1) {
            charframe = 0
        } else {
            charframe = 1
        }
        if (charY < screenHeight - rechar[0]!!.height -210 || velocity <= 0) {
            velocity += gravity
            charY += velocity

        } else {
            jumpCount = 0
            jumpState = false
        }


        canvas.drawBitmap(rechar[charframe]!!, charX.toFloat(), charY.toFloat(), null)
        handler!!.postDelayed(runnable!!, UPDATE_MILLIS)
    }

    private fun gameover() {
        val intent = Intent(context, GameOver::class.java)
        intent.putExtra("POINTS", points)
        context.startActivity(intent)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.action

        if (action == MotionEvent.ACTION_DOWN && jumpCount < 2) {
            jumpCount++
            velocity = -50
            jumpState = true
        }

                return true
       }

}


