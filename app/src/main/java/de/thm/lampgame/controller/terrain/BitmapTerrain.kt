package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R

class BitmapTerrain(context: Context, val terrainHeight : Int, val terrainWidth: Int, var terrainX : Int, var terrainY: Int) {
    var unsizedbottomterrain: Bitmap? = null
    var bottomterrain: Bitmap? = null
    init {
        unsizedbottomterrain = BitmapFactory.decodeResource(context.resources, R.drawable.gras2)
        bottomterrain = unsizedbottomterrain?.let { Bitmap.createScaledBitmap(it, terrainWidth, 100, true) }
    }

    fun drawTerrain(canvas: Canvas, terrainVelocity : Int){
        terrainX -= terrainVelocity
        canvas.drawBitmap(bottomterrain!!, terrainX.toFloat(), terrainY.toFloat(), null)
    }

}