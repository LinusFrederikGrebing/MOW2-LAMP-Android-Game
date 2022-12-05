package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.player.Player

class DoublePointsModel(
    screenHeight: Int,
    screenWidth: Int,
    height: Int,
    width: Int,
    x: Double,
    y: Double
) : ItemModel(screenHeight, screenWidth, height, width, x, y) {
    companion object {
        // how long the effect of an item remains is determined by the constant
        const val doublePointsDuration = 300
        var dblPtsDur = 0
        // each item has a texture, the texture is used to draw the item or to draw an image in the form of the active item
        var texture =  R.drawable.doublepoints_icon
    }

    // if an object is created from the item, it must be ensured that the item is not still active
    init {
        dblPtsDur = 0
    }

    // determines the effect of the item
    override var activateEffect: (Player) -> Unit = { p ->
        p.playerModel.hasDblPts = true
        dblPtsDur = doublePointsDuration
    }

}