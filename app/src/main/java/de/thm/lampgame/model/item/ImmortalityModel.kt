package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player

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
        var textur = R.drawable.immortality_icon
    }

    init {
        immortalityDur = 0
    }

    override var activateEffect: (Player) -> Unit = { p ->
        p.playerModel.immortal = true
        immortalityDur = immortalityDuration
    }
}