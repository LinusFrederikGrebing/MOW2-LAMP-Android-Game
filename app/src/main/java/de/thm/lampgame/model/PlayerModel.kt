package de.thm.lampgame.model

abstract class PlayerModel(screenWidth: Int, screenHeight: Int) {
    var firebarFrame = 0
    var points = 0
    var fire: Float = 100F
    var charX : Int = 0
    var charY : Int = 0
    var charframe = 0
    var velocity = 0
    var gravity = 3
    var jumpCount = 0
    var jumpState = false
    var birdsneek = false
    init {
        charY = screenHeight - 500
        charX = screenWidth / 2 - 600
    }

    fun calkCharframe() : Int{
        charframe = if(birdsneek) 4 else if(jumpState) 5 else if (charframe == 0) 1 else if(charframe == 1) 2 else if(charframe == 2) 3 else 0
        return charframe
    }

    fun calkFirebar() : Int{
        firebarFrame = if(fire > 80) 0 else if (fire > 60) 1 else if (fire > 40) 2 else if (fire > 20) 3 else 4
        return firebarFrame
    }

    fun setJumpStats(collision : Boolean){

        if (!collision  || velocity <= 0 ) {
            jumpState = true
            velocity += gravity
            charY += velocity
        } else {
            jumpCount = 0
            jumpState = false
        }

    }

    fun calkPoints(){
        points++
    }

    fun calkFire(){
        fire -= 0.1F
    }

    fun sprung(){
        if(jumpCount < 2 && !birdsneek){
            jumpCount++
            velocity = -45
            jumpState = true
        }
    }
}