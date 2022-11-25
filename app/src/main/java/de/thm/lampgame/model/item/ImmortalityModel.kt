package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.items.ActiveItem
import de.thm.lampgame.controller.items.Item

abstract class ImmortalityModel(screenHeight: Int, screenWidth: Int, height : Int, width: Int, x: Int, y: Int) : Item(screenHeight, screenWidth, height, width, x,y) {
    companion object{
        val immortalduration = 100
    }

    override var activateEffect: (Player) -> Unit = {p ->
        ActiveItem.texture = R.drawable.immortality
        ActiveItem.speedMultiplyer = 360F
        p.immortal = true
        p.immortalDur = immortalduration
    }
}