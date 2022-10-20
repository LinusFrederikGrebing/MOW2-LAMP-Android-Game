package de.thm.lampgame

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import java.util.*

class BitmapTerrain(context: Context, val terrainHeight : Int, val terrainWidth: Int, var terrainX : Int, var terrainY: Int) {
    var random: Random? = null
    var unsizedbottomterrain: Bitmap? = null
    var bottomterrain: Bitmap? = null
    init {
        unsizedbottomterrain = BitmapFactory.decodeResource(context.resources, R.drawable.gras2)
        bottomterrain = unsizedbottomterrain?.let { Bitmap.createScaledBitmap(it, terrainWidth, 100, true) }
        random = Random()
    }

    fun drawTerrain(canvas: Canvas, terrainVelocity : Int){
        terrainX -= terrainVelocity
        canvas.drawBitmap(bottomterrain!!, terrainX.toFloat(), terrainY.toFloat(), null)
    }

}