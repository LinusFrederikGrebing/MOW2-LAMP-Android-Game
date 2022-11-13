package de.thm.lampgame.model

open class ObstacleModel(
    val name: String,
    val width: Int,
    val height: Int,
    var x: Int,
    var y: Int
) {
    var changeableX = x
    var changeableY = y
}