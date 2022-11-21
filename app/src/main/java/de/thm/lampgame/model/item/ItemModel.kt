package de.thm.lampgame.model.item

import de.thm.lampgame.controller.Player

abstract class ItemModel(val screenHeight: Int, val screenWidth: Int, var x: Int, var y: Int) {
    var pickedUp = false
    abstract val activateEffect: (Player) -> Unit

}