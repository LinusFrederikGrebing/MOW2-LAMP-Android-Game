package de.thm.lampgame.controller.obstaclesBitmaps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.obstacles.ObstacleNames

class BitmapBouncingSaw(context: Context, width: Int, height: Int, x: Int, y: Int) :
    Obstacles(ObstacleNames.BOUNCINGSAW, (0.05 * width).toInt(),(0.1 * height).toInt(), x, y, true) {
    var falling = false

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.saw)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, this.width, this.height, true)
    }

    override fun draw(canvas: Canvas, velocityX: Int, velocityY: Int) {
        changeableX -= velocityX

        if(changeableY >= y/4 && !falling) changeableY -= velocityY
        else falling = true
        if(changeableY <= y && falling) changeableY += velocityY
        else falling = false



        canvas.drawBitmap(bmp, changeableX.toFloat(), changeableY.toFloat(), null)
    }



}
