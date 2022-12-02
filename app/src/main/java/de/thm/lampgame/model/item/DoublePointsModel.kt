package de.thm.lampgame.model.item

import de.thm.lampgame.R
import de.thm.lampgame.controller.Player

class DoublePointsModel(
    screenHeight: Int,
    screenWidth: Int,
    height: Int,
    width: Int,
    x: Int,
    y: Int
) : ItemModel(screenHeight, screenWidth, height, width, x, y) {
    companion object {
        const val doublePointsDuration = 250
        var dblPtsDur = 0
        var textur =  R.drawable.doublepoints_icon
    }

    init {
        dblPtsDur = 0
    }

    override var activateEffect: (Player) -> Unit = { p ->
        p.playerModel.hasDblPts = true
        dblPtsDur = doublePointsDuration
    }

}