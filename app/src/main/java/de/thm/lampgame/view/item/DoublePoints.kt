package de.thm.lampgame.view.item

import android.content.Context
import android.graphics.*
import de.thm.lampgame.R
import de.thm.lampgame.model.item.DoublePointsModel

class DoublePoints(context: Context, screenHeight: Int, screenWidth: Int, x: Int, y: Int ) : DoublePointsModel(screenHeight, screenWidth, (0.1*screenHeight).toInt(),(0.1*screenWidth).toInt(), x,y) {
    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.doublepoints)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true) }

    override fun draw(canvas: Canvas, velocity: Int) {
        if (!pickedUp) {
            x -= velocity
            canvas.drawBitmap(bmp, x.toFloat(), y.toFloat(), null)
        }
    }

}
