package de.thm.lampgame.model

import de.thm.lampgame.controller.terrain.Obstacles

open class TilesetModel(var startX: Int) {
    var obstacles: MutableList<Obstacles> = mutableListOf()

    fun drawTileset(terrainVelocity: Int) {
        startX -= terrainVelocity
    }
}