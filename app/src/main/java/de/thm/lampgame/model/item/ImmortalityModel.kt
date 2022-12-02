package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.item.ActiveItem

class ImmortalityModel(
    screenHeight: Int,
    screenWidth: Int,
    height: Int,
    width: Int,
    x: Int,
    y: Int
) : ItemModel(screenHeight, screenWidth, height, width, x, y) {

    companion object {
        const val immortalityDuration = 100
        var immortalityDur = 0
    }

    override var activateEffect: (Player) -> Unit = { p ->
        ActiveItem.texture = R.drawable.immortality_icon
        ActiveItem.speedMultiplier = 360F
        p.playerModel.immortal = true
        immortalityDur = immortalityDuration
    }
}