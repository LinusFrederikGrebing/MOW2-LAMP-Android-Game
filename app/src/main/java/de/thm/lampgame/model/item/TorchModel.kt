package de.thm.lampgame.model.item

import de.thm.lampgame.controller.Player

class TorchModel(screenHeight: Int, screenWidth: Int, height : Int, width: Int, x: Int, y: Int) : ItemModel(screenHeight, screenWidth, height, width, x,y) {
    override var activateEffect: (Player) -> Unit = {
            p -> p.playerModel.fire = 100F
            p.playerModel.calkCoins()
            p.playerModel.coinsPerRound++
    }
}