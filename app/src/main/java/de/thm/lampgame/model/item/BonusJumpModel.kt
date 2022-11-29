package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player
import de.thm.lampgame.view.item.ActiveItem

abstract class BonusJumpModel(screenHeight: Int, screenWidth: Int, height : Int, width: Int, x: Int, y: Int) : ItemModel(screenHeight, screenWidth, height, width, x,y) {
    companion object{
        val bonusjumpduration = 250
    }

    override var activateEffect: (Player) -> Unit = { p ->
        ActiveItem.texture = R.drawable.bonusjump
        ActiveItem.speedMultiplier = 360F
        p.playerModel.maxJump = 3
        p.playerModel.dblJumpDur = bonusjumpduration
    }
}