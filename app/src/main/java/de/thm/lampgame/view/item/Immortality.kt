package de.thm.lampgame.view.item

import android.content.Context
import android.graphics.*
import android.util.Log
import de.thm.lampgame.R
import de.thm.lampgame.model.item.ImmortalityModel

class Immortality(context: Context, screenHeight: Int, screenWidth: Int, x: Int, y: Int ) : ImmortalityModel(screenHeight, screenWidth, (0.1*screenHeight).toInt(),(0.05*screenWidth).toInt(), x,y) {
    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.immortality_highlight)
        bmp = Bitmap.createScaledBitmap(unsizedBmp as Bitmap, width, height, true) }

    override fun draw(canvas: Any, velocity: Int) {
        Log.i("test", "TEST")
        if (!pickedUp) {
            x -= velocity
            (canvas as Canvas).drawBitmap(bmp as Bitmap, x.toFloat(), y.toFloat(), null)
        }
    }

}
