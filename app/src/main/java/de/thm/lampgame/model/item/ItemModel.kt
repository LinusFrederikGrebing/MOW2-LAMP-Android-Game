package de.thm.lampgame.model.item

import de.thm.lampgame.controller.Player

abstract class ItemModel(val screenHeight: Int, val screenWidth: Int, val width : Int, val height : Int, var x: Int, var y: Int) {
    var pickedUp = false
    lateinit var unsizedBmp: Any
    lateinit var bmp: Any


    open fun itemPickup(p: Player, itemEffect: (Player) -> Unit){
        if (!pickedUp) {
            if (p.playerModel.charX+p.playerModel.charWidth >= x && p.playerModel.charX+p.playerModel.charWidth <= x + width
                || p.playerModel.charX >= x && p.playerModel.charX <= x + width){
                if (p.playerModel.charY >= y && p.playerModel.charY <= y + height
                    || p.playerModel.charY+p.playerModel.charWidth >= y && p.playerModel.charY+p.playerModel.charWidth <= y + height) {
                    pickedUp = true
                    itemEffect(p)
                }
            }
            //TODO auskommentierten Code entfernen, wenn nicht mehr bneötigt
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