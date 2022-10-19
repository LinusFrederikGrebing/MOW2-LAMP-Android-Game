package de.thm.lampgame

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Point
import java.util.*

class BitmapObstacles(context: Context, val TubeHeight : Int, var tubeX : Int) {
    var random: Random? = null
    var unsizedbottomtube: Bitmap? = null
    var bottomtube: Bitmap? = null
    init {
        unsizedbottomtube = BitmapFactory.decodeResource(context.resources, R.drawable.bottomtube)
        bottomtube = unsizedbottomtube?.let { Bitmap.createScaledBitmap(it, 150, 400, true) }
        random = Random()
    }

    fun drawTubes(canvas: Canvas,  tubeVelocity : Int){
            tubeX -= tubeVelocity
            canvas.drawBitmap(bottomtube!!, tubeX.toFloat(), TubeHeight.toFloat(), null)
        }
    }
