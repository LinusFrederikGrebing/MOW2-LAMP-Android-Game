package de.thm.lampgame.controller.items

import android.content.Context
import android.graphics.*
import de.thm.lampgame.R
import de.thm.lampgame.controller.ActiveItem
import de.thm.lampgame.controller.Player

class Immortality(context: Context, screenHeight: Int, screenWidth: Int, x: Int, y: Int ) : Item(context, screenHeight, screenWidth, (0.1*screenHeight).toInt(),(0.05*screenWidth).toInt(), x,y) {
    init {
        unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.immortality)
        bmp = Bitmap.createScaledBitmap(unsizedBmp, width, height, true) }

    override fun draw(canvas: Canvas, velocity: Int) {
        if (!pickedUp) {
            x -= velocity
            canvas.drawBitmap(bmp, x.toFloat(), y.toFloat(), null)
        }
    }


    override var activateEffect: (Player) -> Unit = {p ->
        ActiveItem.texture = R.drawable.immortality
        p.immortal = true
        p.immortalDur = 100
    }




}
