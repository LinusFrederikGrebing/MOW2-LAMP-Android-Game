package de.thm.lampgame.controller.items

import android.content.Context
import android.graphics.*
import de.thm.lampgame.controller.Player
import de.thm.lampgame.model.ItemModel

abstract class Item(
    context: Context,
    screenHeight: Int,
    screenWidth: Int,
    val height: Int,
    val width: Int,
     x: Int,
     y: Int
) : ItemModel(screenHeight, screenWidth, x, y) {

    lateinit var unsizedBmp: Bitmap
    lateinit var bmp: Bitmap

    abstract fun draw(canvas: Canvas, velocity: Int)

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
                itemEffect(p)
            }
        }
    }


}