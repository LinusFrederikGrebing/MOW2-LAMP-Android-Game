package de.thm.lampgame.model.item

import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.items.Item

abstract class TorchModel(screenHeight: Int, screenWidth: Int, height : Int, width: Int, x: Int, y: Int) : Item(screenHeight, screenWidth, height, width, x,y) {

    override var activateEffect: (Player) -> Unit = {
            p -> p.fire = 100F
            p.calkCoins()
            p.coinsPerRound++
    }

}