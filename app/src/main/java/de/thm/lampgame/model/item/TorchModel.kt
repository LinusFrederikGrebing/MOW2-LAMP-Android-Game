package de.thm.lampgame.model.item

import de.thm.lampgame.controller.Player

class TorchModel(screenHeight: Int, screenWidth: Int, height: Int, width: Int, x: Double, y: Double) :
    ItemModel(screenHeight, screenWidth, height, width, x, y) {

    // determines the effect of the item
    override var activateEffect: (Player) -> Unit = { p ->
        p.playerModel.fire = 100F
        p.playerModel.calkTorches()
        p.playerModel.torchesPerRound++
    }
}