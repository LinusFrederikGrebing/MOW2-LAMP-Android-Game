package de.thm.lampgame.model.tileset

import de.thm.lampgame.controller.tileset.Tileset

open class TilesetQueueModel(val screenWidth: Int, val screenHeight: Int) {
    var queue = ArrayDeque<Tileset>(2)       //the queue consists of 2 tilesets
    var collision = false
    var iterations = 0
    var nextTilesethasTorch = false

    fun initQueue(t1: Tileset, t2: Tileset) {
        // add the first two tilesets to the queue
        queue.add(t1); queue.add(t2)
        // each tileset is the width of the device
        // the first tileset starts at x = 0
        queue.first().obstacles.forEach {
            it.changeableX += 0
        }
        // the secound tileset starts at x = screenWidth
        queue.last().obstacles.forEach {
            it.changeableX += screenWidth
        }
    }

    fun iterate(){
        iterations++ // each time a tileset is redrawn counts up once
        if (iterations % 300 == 0) nextTilesethasTorch = true
    }

    fun insertTilesetifneedTo(
        tilesetList: ArrayList<Tileset>,
        tilesetsCount: Int
    ) {
        // check if the end of the first tileset has been reached
        if (queue.first().startX <= -screenWidth) {
            val rest = -screenWidth - queue.first().startX // buffer to close gaps between added tilesets
            val tileset = getpossibleTileset(tilesetList, tilesetsCount)
            tileset.placeTileset(screenWidth - rest)    // add the new start point to the tileset and set its respective item spawn
            insertTileset((screenWidth - rest), tileset)
            queue.last().randomItemSpawn(nextTilesethasTorch)  // spawn a Torch when it's time otherwise with a probability of 1 in 4 another item
            if (nextTilesethasTorch) nextTilesethasTorch = false
        }
    }

    fun insertTileset(startX: Int, t: Tileset) {
        // reset the coordinates of the old tileset
        queue.first().obstacles.forEach {
            it.changeableX = it.x
        }
        queue.first().hasItem = false
        // remove the old tileset
        queue.removeFirst()
        // add the new tileset
        queue.add(t)
        // set the start positon to the new value
        queue.last().obstacles.forEach { it.changeableX += startX }
        queue.last().itemX += startX
    }




    // get a random new tileset as long as the new tileset is not already in the queue
    private fun getpossibleTileset(tilesetList: ArrayList<Tileset>, tilesetsCount: Int): Tileset {
        var random: Int
        do {
            random = (0 until tilesetsCount).random()
        } while (queue.last() == tilesetList[random]) // because the tileset at the first of the queue is removed, the tileset at the first is allowed to be reallocated
        return tilesetList[random]
    }
}