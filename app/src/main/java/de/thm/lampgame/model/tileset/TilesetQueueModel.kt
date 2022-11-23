package de.thm.lampgame.model.tileset

import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.tileset.Tileset
import de.thm.lampgame.view.GameView

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


    // Three different ways to deal with collision
    // Case 1: Obstacle x is a death tileset
    // Case 2: obstacle x is not a death tileset, the player touches the tileset from above
    // Case 3: Obstacle x is not a death Tileset, the player is touching the tileset from below

    fun setCollisionResult(death: Boolean, obstacleY: Int, playerY: Int, player: Player) {
        // case 1
        if (death) {
            if (!player.immortal) {         // checks if the player has immortality status or not
                GameView.gameover = true
            }
        }
        // case 2
        else if (playerY >= obstacleY && playerY <= (obstacleY + player.maxVelocity+10)) {   // checks collision from above with a buffer of the player's maximum velocity
            player.charY = obstacleY - player.rechar[1]!!.height + 1                      // sets the character's y-coordinate to the edge of the floor
            player.jumpState = false
        }
        // case 3
        else {
            if (!player.immortal) {
                GameView.gameover = true
            }
        }
    }

}