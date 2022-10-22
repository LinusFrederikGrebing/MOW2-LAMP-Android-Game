package de.thm.lampgame.model

import de.thm.lampgame.controller.Tileset

class TilesetQueueModel {
    var queue = ArrayDeque<Tileset>(2)

    fun initQueue(t1: Tileset, t2: Tileset, screenWidth: Int){
        queue.add(t1);queue.add(t2)
        queue.first().obstacles.forEach {it.terrainX += screenWidth}
        queue.last().obstacles.forEach {it.terrainX += screenWidth * 2}
    }

    fun insertTileset(screenWidth: Int, t: Tileset){
            queue.removeFirst()
            queue.add(t)
            queue.last().obstacles.forEach {it.terrainX += screenWidth}

    }
}