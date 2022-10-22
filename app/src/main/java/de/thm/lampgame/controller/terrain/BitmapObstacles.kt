package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R


class BitmapObstacles(context: Context, val TubeHeight : Int, var tubeX : Int) {
    var unsizedbottomtube: Bitmap? = null
    var bottomtube: Bitmap? = null
    init {
        unsizedbottomtube = BitmapFactory.decodeResource(context.resources, R.drawable.bottomtube)
        bottomtube = unsizedbottomtube?.let { Bitmap.createScaledBitmap(it, 150, 400, true) }
    }

    fun drawTubes(canvas: Canvas,  tubeVelocity : Int){
            tubeX -= tubeVelocity
            canvas.drawBitmap(bottomtube!!, tubeX.toFloat(), TubeHeight.toFloat(), null)
    }
}
