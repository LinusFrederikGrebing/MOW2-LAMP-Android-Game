package de.thm.lampgame.view.item

import android.content.Context
import android.graphics.*
import de.thm.lampgame.R
import de.thm.lampgame.model.item.BonusJumpModel

class BonusJump(context: Context, screenHeight: Int, screenWidth: Int, x: Int, y: Int ) : BonusJumpModel(screenHeight, screenWidth, (0.1*screenHeight).toInt(),(0.05*screenWidth).toInt(), x,y) {
    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.bonusjump_highlight)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true) }

    override fun draw(canvas: Canvas, velocity: Int) {
        if (!pickedUp) {
            x -= velocity
            canvas.drawBitmap(bmp, x.toFloat(), y.toFloat(), null)
        }
    }

}
