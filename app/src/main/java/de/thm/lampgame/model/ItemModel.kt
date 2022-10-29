package de.thm.lampgame.model

import de.thm.lampgame.controller.Player

abstract class ItemModel {
    var pickedUp = false
    abstract val activateEffect: (Player) -> Unit
}