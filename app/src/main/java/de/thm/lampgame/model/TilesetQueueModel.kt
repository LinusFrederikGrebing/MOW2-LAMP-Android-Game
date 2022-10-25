package de.thm.lampgame.model

import de.thm.lampgame.controller.Tileset
import de.thm.lampgame.controller.terrain.Obstacles

class TilesetQueueModel {
    var queue = ArrayDeque<Tileset>(2)

    fun initQueue(t1: Tileset, t2: Tileset, screenWidth: Int){
        queue.add(t1); queue.add(t2)

        queue.first().obstacles.forEach {
            it.x  += screenWidth}
        queue.last().obstacles.forEach {
            it.x += screenWidth * 2}
    }

    fun recycleOldTileset() {
        queue.first().obstacles.forEach {
            it.bmp.recycle()
        }
    }

    fun insertTileset(screenWidth: Int, t: Tileset){
            queue.removeFirst()
            queue.add(t)
            queue.last().obstacles.forEach {it.x += screenWidth}
    }
}