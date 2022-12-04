package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player

class BonusJumpModel(screenHeight: Int, screenWidth: Int, height: Int, width: Int, x: Double, y: Double) :
    ItemModel(screenHeight, screenWidth, height, width, x, y) {

    companion object {
        // how long the effect of an item remains is determined by the constant
        const val bonusJumpDuration = 250
        var dblJumpDur = 0
        // each item has a texture, the texture is used to draw the item or to draw an image in the form of the active item
        var texture =  R.drawable.bonusjump_icon
    }

    // if an object is created from the item, it must be ensured that the item is not still active
    init {
        dblJumpDur = 0
    }

    // determines the effect of the item
    override var activateEffect: (Player) -> Unit = { p ->
        p.playerModel.maxJump = 3
        dblJumpDur = bonusJumpDuration
    }
}