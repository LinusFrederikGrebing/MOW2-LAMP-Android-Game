package de.thm.lampgame.model.tileset

import de.thm.lampgame.controller.player.Player
import de.thm.lampgame.controller.obstacles.ObstacleController
import de.thm.lampgame.controller.tileset.Tileset
import de.thm.lampgame.controller.GameController

open class TilesetQueueModel(val screenWidth: Int, val screenHeight: Int) {
    var queue = ArrayDeque<Tileset>(2)       //the queue consists of 2 tilesets
    var tilesetList = ArrayList<Tileset>()     // list in which all tileset variations are stored
    val possibleTilesetCount = 20      // possible variations of different tilesets
    var collision = false
    private var iterations = 0
    private var nextTilesetHasTorch = false

    fun initQueue(t1: Tileset, t2: Tileset) {
        // add the first two tilesets to the queue
        queue.add(t1); queue.add(t2)
        // each tileset is the width of the device
        // the first tileset starts at x = 0
        queue.first().tilesetModel.obstacles.forEach {
            it.obstacleModel.changeableX += 0.0
        }
        // the second tileset starts at x = screenWidth
        queue.last().tilesetModel.obstacles.forEach {
            it.obstacleModel.changeableX += screenWidth
        }
    }

    fun iterate() {
        iterations++ // counts up once each time a tileset is redrawn
        if (iterations % 300 == 0) nextTilesetHasTorch = true
    }

    fun insertTilesetIfNeeded() {
        // check if the end of the first tileset has been reached
        if (queue.first().tilesetModel.startX <= -screenWidth) {
            val rest =
                -screenWidth - queue.first().tilesetModel.startX // buffer to close gaps between added tilesets
            val tileset = getPossibleTileset()
            tileset.tilesetModel.placeTileset(screenWidth - rest)    // add the new start point to the tileset and set its respective item spawn
            insertTileset((screenWidth - rest), tileset)
            queue.last().tilesetModel
                .randomItemSpawn(nextTilesetHasTorch)  // spawn a Torch when it's time otherwise with a probability of 1 in 4 another item
            if (nextTilesetHasTorch) nextTilesetHasTorch = false
        }
    }

    private fun insertTileset(startX: Double, t: Tileset) {
        // reset the coordinates of the old tileset
        queue.first().tilesetModel.obstacles.forEach {
            it.obstacleModel.changeableX = it.obstacleModel.x
        }
        queue.first().tilesetModel.hasItem = false
        // remove the old tileset
        queue.removeFirst()
        // add the new tileset
        queue.add(t)
        // set the start position to the new value
        queue.last().tilesetModel.obstacles.forEach { it.obstacleModel.changeableX += startX }
        queue.last().tilesetModel.itemX += startX
    }

    // get a random new tileset as long as the new tileset is not already in the queue
    private fun getPossibleTileset(): Tileset {
        var random: Int
        do {
            random = (0 until possibleTilesetCount).random()
        } while (queue.last() == tilesetList[random]) // because the tileset at the first of the queue is removed, the tileset at the first is allowed to be reallocated
        return tilesetList[random]
    }

    fun collisionCheck(player: Player) {
        collision = false  // sets collision to false by default
        for (i in 0 until queue.first().tilesetModel.obstacles.size) {
            if (this.returnCollision(queue.first().tilesetModel.obstacles[i], player)) collision =
                true
        }
        for (i in 0 until queue.last().tilesetModel.obstacles.size) {
            if (this.returnCollision(queue.last().tilesetModel.obstacles[i], player)) collision =
                true
        }
    }

    private fun returnCollision(obstacle: ObstacleController, player: Player): Boolean {
        if (player.playerModel.charX + player.playerModel.charWidth > obstacle.obstacleModel.changeableX && player.playerModel.charX + player.playerModel.charWidth < obstacle.obstacleModel.changeableX + obstacle.obstacleModel.width
            || player.playerModel.charX > obstacle.obstacleModel.changeableX && player.playerModel.charX < obstacle.obstacleModel.changeableX + obstacle.obstacleModel.width
        ) {
            if (player.playerModel.charY > obstacle.obstacleModel.changeableY && player.playerModel.charY < obstacle.obstacleModel.changeableY + obstacle.obstacleModel.height
                || player.playerModel.charY + player.playerModel.charHeight > obstacle.obstacleModel.changeableY && player.playerModel.charY + player.playerModel.charHeight < obstacle.obstacleModel.changeableY + obstacle.obstacleModel.height
            ) {
                setCollisionResult(
                    obstacle.obstacleModel.death,
                    obstacle.obstacleModel.changeableY,
                    player.playerModel.charY + player.playerModel.charHeight,
                    player
                )
                return true
            }
        }
        return false
    }

// Three different ways to deal with collision
    // Case 1: Obstacle x is a death tileset
    // Case 2: obstacle x is not a death tileset, the player touches the tileset from above
    // Case 3: Obstacle x is not a death Tileset, the player is touching the tileset from below

    private fun setCollisionResult(death: Boolean, obstacleY: Double, playerY: Double, player: Player) {
        // case 1
        if (death) {
            if (!player.playerModel.immortal) {         // checks if the player has immortality status or not
                GameController.gameOver = true
            }
        }
        // case 2
        else if (playerY >= obstacleY && playerY <= (obstacleY + player.playerModel.maxVelocity + 10)) {   // checks collision from above with a buffer of the player's maximum velocity
            player.playerModel.charY =
                obstacleY - player.rechar[1]!!.height + 1                      // sets the character's y-coordinate to the edge of the floor
            player.playerModel.jumpState = false
        }
        // case 3
        else {
            if (!player.playerModel.immortal) {
                GameController.gameOver = true
            }
        }
    }
}