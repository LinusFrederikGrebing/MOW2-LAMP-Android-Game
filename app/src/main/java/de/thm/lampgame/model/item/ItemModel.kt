package de.thm.lampgame.model.item

import de.thm.lampgame.controller.Player

abstract class ItemModel(val screenHeight: Int, val screenWidth: Int, val width : Int, val height : Int, var x: Int, var y: Int) {
    var pickedUp = false
    lateinit var unsizedBmp: Any
    lateinit var bmp: Any

    abstract fun draw(canvas: Any, velocity: Int)

    open fun itemPickup(p: Player, itemEffect: (Player) -> Unit){
        if (!pickedUp) {
            if (p.charX+p.charWidth >= x && p.charX+p.charWidth <= x + width
                || p.charX >= x && p.charX <= x + width){
                if (p.charY >= y && p.charY <= y + height
                    || p.charY+p.charWidth >= y && p.charY+p.charWidth <= y + height) {
                    pickedUp = true
                    itemEffect(p)
                }
            }
            /*
             val playerHitbox = Rect(
                p.charX,
                p.charY,
                (p.charX + p.charWidth),
                (p.charY + p.charHeight)
            )
            val itemHitbox = Rect(x, y, (x + width), (y + height))
            if (Rect.intersects(playerHitbox, itemHitbox)) {
                pickedUp = true
                itemEffect(p)
            }
            */
        }
    }

    abstract val activateEffect: (Player) -> Unit
}