package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.item.ActiveItem

class DoublePointsModel(screenHeight: Int, screenWidth: Int, height : Int, width: Int, x: Int, y: Int) : ItemModel(screenHeight, screenWidth, height, width, x,y) {
    companion object{
        const val doublepointsduration = 250
        var dblPtsDur = 0
    }

    override var activateEffect: (Player) -> Unit = {p ->
        ActiveItem.texture = R.drawable.doublepoints_icon
        ActiveItem.speedMultiplier = 360F
        p.playerModel.hasDblPts = true
        dblPtsDur = doublepointsduration
    }

}