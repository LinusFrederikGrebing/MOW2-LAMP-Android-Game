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
        // how long the effect of an item remains is determined by the constant
        const val immortalityDuration = 100
        var immortalityDur = 0
        // each item has a texture, the texture is used to draw the item or to draw an image in the form of the active item
        var textur = R.drawable.immortality_icon
    }

    // if an object is created from the item, it must be ensured that the item is not still active
    init {
        immortalityDur = 0
    }

    // determines the effect of the item
    override var activateEffect: (Player) -> Unit = { p ->
        p.playerModel.immortal = true
        immortalityDur = immortalityDuration
    }
}