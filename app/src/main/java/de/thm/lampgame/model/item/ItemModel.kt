package de.thm.lampgame.model.item

import de.thm.lampgame.controller.player.Player

abstract class ItemModel(
    val screenHeight: Int,
    val screenWidth: Int,
    val width: Int,
    val height: Int,
    var x: Double,
    var y: Double
) {
    var isPickedUp = false
    lateinit var unsizedBmp: Any
    lateinit var bmp: Any


    open fun itemPickup(p: Player, itemEffect: (Player) -> Unit) {
        if (!isPickedUp) {
            if (p.playerModel.charX + p.playerModel.charWidth >= x && p.playerModel.charX + p.playerModel.charWidth <= x + width
                 || p.playerModel.charX >= x && p.playerModel.charX <= x + width
             ) {
                 if (p.playerModel.charY >= y && p.playerModel.charY <= y + height
                     || p.playerModel.charY + p.playerModel.charHeight >= y && p.playerModel.charY + p.playerModel.charHeight <= y + height
                     || p.playerModel.charY + (p.playerModel.charHeight/2) > y &&
                     p.playerModel.charY + (p.playerModel.charHeight/2) < y+height
                 ) {
                     isPickedUp = true
                     itemEffect(p)
                 }
             }

             // old version of the code above, more elegant but discarded so that the code can be translated by S = K
             /*
               val playerHitbox = Rect(
                 p.playerModel.charX,
                 p.playerModel.charY,
                 (p.playerModel.charX + p.playerModel.charWidth),
                 (p.playerModel.charY + p.playerModel.charHeight)
             )
             val itemHitbox = Rect(x, y, (x + width), (y + height))
             if (Rect.intersects(playerHitbox, itemHitbox)) {
                 isPickedUp = true
                 itemEffect(p)
             }
            */
        }
    }

    abstract val activateEffect: (Player) -> Unit
}