package de.thm.lampgame.model

abstract class PlayerModel(val screenWidth: Int, val screenHeight: Int) {
    var charHeight: Double = 0.0
    var charwidth: Double = 0.0
    var firebarFrame = 0
    var points = 0
    var fire: Float = 100F
    var charframe = 0
    var charY = (0.10*screenWidth).toInt()
    var charX = (0.15*screenWidth).toInt()
    var velocity = 0
    var gravity = 2
    var jumpCount = 0
    var jumpState = false
    var birdsneek = false
    val maxVelocity = 30
    var hasDblPts = false
    var dblPtsDur = 0

    fun getCharWidth() : Int{
        charwidth = 0.08*screenWidth
        return charwidth.toInt()
    }
    fun getCharHeight() : Int{
        charHeight = 0.18*screenHeight
        return charHeight.toInt()
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
            if(velocity < maxVelocity) velocity += gravity
            charY += velocity
        } else {
            jumpCount = 0
            jumpState = false
        }

    }

    fun calkPoints(){
        if (!hasDblPts) points++
        else points += 2
    }

    fun calkFire(){
        fire -= 0.15F
    }

    fun sprung(){
        if(jumpCount < 2 && !birdsneek){
            jumpCount++
            velocity = -30
            jumpState = true
        }
    }
}