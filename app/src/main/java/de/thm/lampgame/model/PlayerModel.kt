package de.thm.lampgame.model

import de.thm.lampgame.view.GameView

class PlayerModel(val screenWidth: Int, val screenHeight: Int) {
    
    // player size and starting position
    var charHeight  = (0.16 * screenHeight).toInt()
    var charWidth =  (0.07 * screenWidth).toInt()
    var charY = (0.10 * screenWidth).toInt()
    var charX = (0.15 * screenWidth).toInt()

    var points: Double = 0.0
    var fire: Float = 100F
    var charframe = 0

    var jumpState = false   // if jumpState is true the jumpskin-sprite should be loaded

    var hasDblPts = false
    var immortal = false

    var velocity = 0
    var gravity = (screenHeight * 0.003).toInt()
    val maxVelocity = (screenHeight * 0.04).toInt()

    // itemduration
    var immortalDur = 0
    var dblPtsDur = 0
    var dblJumpDur = 0


    var jumpCount = 0    // counts the number of jumps without ground collision
    var maxJump = 2     // the maximum number of jumps is 2 by default, but can increase to 3 with an item


    var coinsPerRound = 0  // saves the torches collected per round

    companion object {
        var coins = 500
    }

    init {
        coinsPerRound = 0 // if a new round starts, the torches must be reset each round
    }

    // cycles through the character spritesheet -> special case: while jumping / while falling
    fun calkCharframe(): Int {
        charframe = if (jumpState) 4 else if (charframe == 0) 1 else if (charframe == 1) 2 else if (charframe == 2) 3 else 0
        return charframe
    }

    fun setJumpStats(collision: Boolean) {
        if (!collision || velocity <= 0) { // the player can only jump or fall if there is either no collision or negative velocity
            jumpState = true // jumpskin-sprite should be loaded
            if (velocity < maxVelocity) velocity += gravity // the player has a maximum falling speed, as long as this is not reached, the speed is offset against the gravity
            charY += velocity
        } else {
            jumpCount = 0     // if a platform is touched, the jump count should be reset
            jumpState = false // jumpskin-sprite should not be loaded
        }
    }

    // increase player's coins by one
    fun calkCoins() {
        coins++
    }

    // the points are the equivalent of meters and can be temporarily doubled by an item
    fun calkPoints(addPts: Double) {
        if (hasDblPts) points += (addPts * 2.0)
        else points += addPts
    }

    // decrease player's fire by 0.15F, when it reaches zero, the player dies
    fun calkFire() {
        fire -= 0.15F
        if(fire <= 0F) GameView.gameover = true
    }

    // it can only be jumped if there are still jumps left
    fun sprung() {
        if (jumpCount < maxJump) {
            jumpCount++
            velocity = -(screenHeight * 0.04).toInt()
        }
    }
}