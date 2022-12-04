package de.thm.lampgame.model.obstacles

open class ObstacleModel(
    val name: ObstacleNames,
    val width: Int,
    val height: Int,
    var x: Double,
    var y: Double,
    var death: Boolean
) {
    var changeableX = x
    var changeableY = y
    lateinit var unsizedBmp: Any
    lateinit var bmp: Any

    private var isFalling = false

    fun changeYCoords(velocityY: Double) {
        if (changeableY >= y / 4 && !isFalling) changeableY -= velocityY
        else isFalling = true
        if (changeableY <= y && isFalling) changeableY += velocityY
        else isFalling = false
    }

}