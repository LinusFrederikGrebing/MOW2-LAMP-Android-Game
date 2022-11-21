package de.thm.lampgame.controller.ObstaclesBitmaps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.obstacles.ObstacleNames


class BitmapTerrain(context: Context, width: Int, height: Int, x: Int, y: Int) :
    Obstacles(ObstacleNames.TERRAIN, width, height, x, y, false) {

    companion object {
        var texture = R.drawable.tilesground
    }

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, this.width, this.height, true)
    }

    override fun draw(canvas: Canvas, velocityX: Int, velocityY: Int) {
        changeableX -= velocityX
        canvas.drawBitmap(bmp, changeableX.toFloat(), changeableY.toFloat(), null)
    }

}