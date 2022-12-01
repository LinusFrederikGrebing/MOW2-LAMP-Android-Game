package de.thm.lampgame.model.obstacles

open class ObstacleModel (
    val name: ObstacleNames,
    val width: Int,
    val height: Int,
    var x: Int,
    var y: Int,
    var death: Boolean
) {
    var changeableX = x
    var changeableY = y
    lateinit var unsizedBmp: Any
    lateinit var bmp: Any

    var falling = false

    fun changeYCoords(velocityY : Int){
        if(changeableY >= y/4 && !falling) changeableY -= velocityY
        else falling = true
        if(changeableY <= y && falling) changeableY += velocityY
        else falling = false
    }

}