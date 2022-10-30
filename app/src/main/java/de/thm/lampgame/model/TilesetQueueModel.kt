package de.thm.lampgame.model

import de.thm.lampgame.controller.Tileset

open class TilesetQueueModel {
    var queue = ArrayDeque<Tileset>(2)
    var collision = false
    var gameover = false

    fun initQueue(t1: Tileset, t2: Tileset, screenWidth: Int) {
        queue.add(t1); queue.add(t2)

        queue.first().obstacles.forEach {
            it.changeableX += 0
        }
        queue.last().obstacles.forEach {
            it.changeableX += screenWidth
        }
    }

    fun insertTileset(screenWidth: Int, t: Tileset) {
        queue.first().startX = screenWidth
        queue.first().obstacles.forEach {
            it.changeableX = it.x
        }
        queue.removeFirst()
        queue.add(t)
        queue.last().obstacles.forEach { it.changeableX += screenWidth }
    }

    fun insertTilesetifneedTo(
        screenWidth: Int,
        tilesetList: ArrayList<Tileset>,
        tilesetsCount: Int
    ) : Boolean {
        if (queue.first().startX <= -screenWidth) {
            val rest = -screenWidth - queue.first().startX
            val tileset = getpossibleTileset(tilesetList, tilesetsCount)
            tileset.startX = (screenWidth - rest)
            insertTileset(
                screenWidth - rest, tileset
            )
            return true
        }
        return false
    }

    var random = 0
    private fun getpossibleTileset(tilesetList: ArrayList<Tileset>, tilesetsCount: Int): Tileset {
        do {
            random = (0 until tilesetsCount).random()
        } while (queue.last() == tilesetList[random])
        return tilesetList[random]
    }
}