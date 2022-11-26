package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player
import de.thm.lampgame.view.item.ActiveItem

abstract class DoublePointsModel(screenHeight: Int, screenWidth: Int, height : Int, width: Int, x: Int, y: Int) : ItemModel(screenHeight, screenWidth, height, width, x,y) {

    companion object{
        val doublepointsduration = 250
    }

    override var activateEffect: (Player) -> Unit = {p ->
        ActiveItem.texture = R.drawable.doublepoints_icon
        ActiveItem.speedMultiplyer = 360F
        p.hasDblPts = true
        p.dblPtsDur = doublepointsduration
    }

}