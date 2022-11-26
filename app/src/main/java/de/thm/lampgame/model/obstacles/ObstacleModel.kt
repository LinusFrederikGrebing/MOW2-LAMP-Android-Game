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

    open fun draw(canvas: Any, velocityX: Int, velocityY: Int) {}
}