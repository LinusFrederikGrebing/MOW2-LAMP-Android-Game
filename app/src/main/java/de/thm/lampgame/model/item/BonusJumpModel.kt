package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player

class BonusJumpModel(screenHeight: Int, screenWidth: Int, height: Int, width: Int, x: Int, y: Int) :
    ItemModel(screenHeight, screenWidth, height, width, x, y) {
    companion object {
        const val bonusJumpDuration = 250
        var dblJumpDur = 0
        var textur =  R.drawable.bonusjump_icon
    }

    init {
        dblJumpDur = 0
    }

    override var activateEffect: (Player) -> Unit = { p ->
        p.playerModel.maxJump = 3
        dblJumpDur = bonusJumpDuration
    }
}