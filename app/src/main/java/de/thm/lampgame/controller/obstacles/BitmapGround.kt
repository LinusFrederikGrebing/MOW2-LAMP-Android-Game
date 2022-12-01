package de.thm.lampgame.controller.obstacles

import  android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.obstacles.ObstacleModel
import de.thm.lampgame.model.obstacles.ObstacleNames

class BitmapGround(context: Context, width: Int, height: Int) : ObstacleController() {
    override var obstacleModel =  ObstacleModel (ObstacleNames.GROUND, width, (0.13 * height).toInt(), 0, (0.87 * height).toInt(), false)
    companion object {
        var texture = R.drawable.tilesground
    }

    init {
        obstacleModel.unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        obstacleModel.bmp = Bitmap.createScaledBitmap(obstacleModel.unsizedBmp as Bitmap, obstacleModel.width, obstacleModel.height, true)
    }

    override fun draw(canvas: Any, velocityX: Int, velocityY: Int) {
        obstacleModel.changeableX -= velocityX
        (canvas as Canvas).drawBitmap(obstacleModel.bmp as Bitmap, obstacleModel.changeableX.toFloat(), obstacleModel.changeableY.toFloat(), null)
    }
}