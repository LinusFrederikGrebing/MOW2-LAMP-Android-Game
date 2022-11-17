package de.thm.lampgame.controller.items

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.controller.Player

class DoublePoints(context: Context, screenHeight: Int, screenWidth: Int, x: Int, y: Int ) : Item(context, screenHeight, screenWidth, (0.1*screenHeight).toInt(),(0.1*screenWidth).toInt(), x,y) {

    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.doublepoints_icon)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true) }

    override fun draw(canvas: Canvas, velocity: Int) {
        if (!pickedUp) {
            x -= velocity
            canvas.drawBitmap(bmp, x.toFloat(), y.toFloat(), null)
        }
    }

    override var activateEffect: (Player) -> Unit = {p ->
        p.hasDblPts = true
        p.dblPtsDur = 250
    }

}
