package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.items.ActiveItem
import de.thm.lampgame.controller.items.Item

abstract class BonusJumpModel(screenHeight: Int, screenWidth: Int, height : Int, width: Int, x: Int, y: Int) : Item(screenHeight, screenWidth, height, width, x,y) {
    companion object{
        val bonusjumpduration = 250
    }

    override var activateEffect: (Player) -> Unit = { p ->
        ActiveItem.texture = R.drawable.bonusjump
        ActiveItem.speedMultiplyer = 360F
        p.maxJump = 3
        p.dblJumpDur = bonusjumpduration
    }
}