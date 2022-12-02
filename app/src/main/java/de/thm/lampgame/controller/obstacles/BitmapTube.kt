package de.thm.lampgame.controller.obstacles

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.obstacles.ObstacleModel
import de.thm.lampgame.model.obstacles.ObstacleNames

class BitmapTube(context: Context, width: Int, height: Int, x: Int, y: Int) : ObstacleController() {
    // creates the associated model
    override var obstacleModel = ObstacleModel(
        ObstacleNames.TUBE,
        (0.08 * width).toInt(),
        (0.65 * height).toInt(),
        x,
        y,
        false
    )

    // depending on the map, a different texture of the obstacle should be loaded
    companion object {
        var texture = R.drawable.obstaclestonewall
    }

    // initializes and resize the respective bitmap based on the data from the associated model
    init {
        obstacleModel.unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        obstacleModel.bmp = Bitmap.createScaledBitmap(
            obstacleModel.unsizedBmp as Bitmap,
            obstacleModel.width,
            obstacleModel.height,
            true
        )
    }

    // change the x coordinate by the given velocity value and draw the obstacle
    // -> get the required data from the associated model
    override fun draw(canvas: Any, velocityX: Int, velocityY: Int) {
        obstacleModel.changeableX -= velocityX
        (canvas as Canvas).drawBitmap(
            obstacleModel.bmp as Bitmap,
            obstacleModel.changeableX.toFloat(),
            obstacleModel.changeableY.toFloat(),
            null
        )
    }
}