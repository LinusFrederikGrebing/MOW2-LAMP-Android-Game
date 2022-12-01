package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.item.ActiveItem

class ImmortalityModel(screenHeight: Int, screenWidth: Int, height : Int, width: Int, x: Int, y: Int) : ItemModel(screenHeight, screenWidth, height, width, x,y) {

    companion object{
        const val immortalduration = 100
        var immortalDur = 0
    }
    override var activateEffect: (Player) -> Unit = {p ->
        ActiveItem.texture = R.drawable.immortality
        ActiveItem.speedMultiplier = 360F
        p.playerModel.immortal = true
        immortalDur = immortalduration
    }
}