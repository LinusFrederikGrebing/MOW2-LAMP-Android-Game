package de.thm.lampgame.model

abstract class PlayerModel(val screenWidth: Int, val screenHeight: Int) {
    var charHeight  = (0.18 * screenHeight).toInt()
    var charwidth =  (0.16 * screenHeight).toInt()

    //var firebarFrame = 0
    var points: Double = 0.0
    var fire: Float = 100F
    var charframe = 0
    var charY = (0.10 * screenWidth).toInt()
    var charX = (0.15 * screenWidth).toInt()
    var velocity = 0
    var gravity = 3
    var jumpCount = 0
    var jumpState = false
    var birdsneek = false
    val maxVelocity = 40
    var hasDblPts = false
    var dblPtsDur = 0
    var dblJumpDur = 0
    var maxJump = 2
    var immortal = true
    var immortalDur = 0
    var coinsPerRound = 0
    companion object {
        var coins = 500

    }

    init {
        coinsPerRound = 0
    }

    fun calkCharframe(): Int {
        charframe =
            if (birdsneek) 4 else if (jumpState) 5 else if (charframe == 0) 1 else if (charframe == 1) 2 else if (charframe == 2) 3 else 0
        return charframe
    }

    //Alternativer Firebar-Ansatz
    /*fun calkFirebar() : Int{
        firebarFrame = if(fire > 80) 0 else if (fire > 60) 1 else if (fire > 40) 2 else if (fire > 20) 3 else 4
        return firebarFrame
    }*/

    fun setJumpStats(collision: Boolean) {
        if (!collision || velocity <= 0) {
            jumpState = true
            if (velocity < maxVelocity) velocity += gravity
            charY += velocity
        } else {
            jumpCount = 0
            jumpState = false
        }
    }

    fun calkCoins() {
        coins++
    }

    fun calkPoints(addPts: Double) {
        if (!hasDblPts) points += addPts
        else points += (addPts * 2.0)
    }

    fun calkFire() {
        fire -= 0.15F
    }

    fun sprung() {
        if (jumpCount < maxJump && !birdsneek) {
            jumpCount++
            velocity = -40
            jumpState = true
        }
    }
}