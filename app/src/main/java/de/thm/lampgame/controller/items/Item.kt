package de.thm.lampgame.controller.items

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import de.thm.lampgame.controller.Player

open abstract class Item(context: Context, val height: Int, val width: Int, var x: Int, var y: Int) {

    lateinit var unsizedBmp: Bitmap
    lateinit var bmp: Bitmap
    var pickedUp = false

    abstract fun draw(canvas: Canvas, velocity: Int)

    abstract val activateEffect: (Player) -> Unit

    fun itemPickup(p: Player, itemEffect: (Player) -> Unit){
        if (!pickedUp) {
            val playerHitbox = Rect(
                p.charX,
                p.charY,
                (p.charX + p.rechar[0]!!.width),
                (p.charY + p.rechar[0]!!.height)
            )
            val itemHitbox = Rect(x, y, (x + bmp.width), (y + bmp.height))
            if (Rect.intersects(playerHitbox, itemHitbox)) {
                pickedUp = true
                bmp.recycle()
                itemEffect(p)
            }
        }
    }

}