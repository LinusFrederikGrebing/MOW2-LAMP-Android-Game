package de.thm.lampgame.model

import de.thm.lampgame.controller.items.Item
import de.thm.lampgame.controller.terrain.Obstacles

open class TilesetModel(var startX: Int, var tilesetNr: Int, var width: Int, var height: Int) {
    var obstacles: MutableList<Obstacles> = mutableListOf()
    lateinit var item: Item
    var hasItem = false
    var itemX = 0
    var itemY = 0


    fun placeTileset(startPos: Int) {
        startX = startPos
        setItemSpawnpoint()
    }

    fun setItemSpawnpoint() {
        when (tilesetNr) {
            1 -> {itemX = (0.9*width).toInt(); itemY = (0.1*height).toInt()}
            2 -> {itemX = (0.55*width).toInt(); itemY = (0.5*height).toInt()}
            3 -> {itemX = (0.55*width).toInt(); itemY = (0.6*height).toInt()}
            4 -> {itemX = (0.7*width).toInt(); itemY = (0.25*height).toInt()}
            5 -> {itemX = (0.15*width).toInt(); itemY = (0.2*height).toInt()}
            else -> println("Error Torch Spawn Point")
        }
    }


    fun drawTileset(terrainVelocity: Int) {
        startX -= terrainVelocity
    }
}