package de.thm.lampgame.view.obstacles

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.obstacles.ObstacleModel
import de.thm.lampgame.model.obstacles.ObstacleNames

class BitmapTube(context: Context, width: Int, height: Int, x: Int, y: Int) :
    ObstacleModel(ObstacleNames.TUBE, (0.08 * width).toInt(),(0.47 * height).toInt(), x, y, false) {
    companion object {
        var texture = R.drawable.obstaclestonewall
    }

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, texture)
        bmp = Bitmap.createScaledBitmap(unsizedBmp as Bitmap, this.width, this.height, true)
    }

    override fun draw(canvas: Any, velocityX: Int, velocityY: Int){
        changeableX -= velocityX
        (canvas as Canvas).drawBitmap(bmp as Bitmap, changeableX.toFloat(), changeableY.toFloat(), null)
    }
}