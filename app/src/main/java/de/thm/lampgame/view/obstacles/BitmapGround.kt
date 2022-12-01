package de.thm.lampgame.view.obstacles

import  android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.obstacles.ObstacleModel
import de.thm.lampgame.model.obstacles.ObstacleNames

class BitmapGround(context: Context, width: Int, height: Int) : ObstacleModel (
    ObstacleNames.GROUND,
    width,
    (0.13 * height).toInt(),
    0,
    (0.87 * height).toInt(),
    false
) {
    companion object {
        var texture = R.drawable.cemetery_ground
    }

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        bmp = Bitmap.createScaledBitmap(unsizedBmp as Bitmap, this.width, this.height, true)
    }

    override fun draw(canvas: Any, velocityX: Int, velocityY: Int) {
        changeableX -= velocityX
        (canvas as Canvas).drawBitmap(bmp as Bitmap, changeableX.toFloat(), changeableY.toFloat(), null)
    }
}