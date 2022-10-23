package de.thm.lampgame

import android.content.Context
import android.graphics.*


class Tileset(val context: Context, var startX: Int, var startY: Int, val width: Int, val height: Int) {
    var unsizedbottomtileset: Bitmap? = null
    var bottomtileset: Bitmap? = null
    val obstacles: MutableList<Obstacles> = mutableListOf()
    init {
        unsizedbottomtileset = BitmapFactory.decodeResource(context.resources, R.drawable.border)
        bottomtileset = unsizedbottomtileset?.let { Bitmap.createScaledBitmap(it, width, height, true) }
        randomTileset()
    }

    fun drawTileset(canvas: Canvas, terrainVelocity : Int){
        startX -= terrainVelocity
        canvas.drawBitmap(bottomtileset!!, startX.toFloat(), startY.toFloat(),null)
    }

    fun randomTileset() {
        var random = (1..2).random()
        when (random) {
            1 -> obstacles.addAll(listOf(BitmapGround(context,width), BitmapTerrain(context,1000, 100, 200),BitmapTerrain(context,500, 500, 700)))
            2 -> obstacles.addAll(listOf(BitmapGround(context,width), BitmapTerrain(context,300, 100, 600),BitmapTerrain(context,300, 300, 400), BitmapTerrain(context,300, 500, 200)))
            else -> println("Failed")
        }
    }
}