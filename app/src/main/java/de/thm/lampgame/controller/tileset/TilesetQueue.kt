package de.thm.lampgame.controller.tileset

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.obstaclesBitmaps.Obstacles
import de.thm.lampgame.model.tileset.TilesetQueueModel

class TilesetQueue(screenWidth: Int, screenHeight: Int) :
    TilesetQueueModel(screenWidth, screenHeight) {

    private var tilesetList = ArrayList<Tileset>()     // list in which all tileset variations are stored
    private val possibleTilesetCount = 16      // possible variations of different tilesets

    fun initialQueue(context: Context) {
        // initialize each possible tileset once
        for (i in 1..possibleTilesetCount) {
            tilesetList.add(Tileset(i, context, screenWidth, screenWidth, screenHeight))
        }

        // the tilesetqueue consists of two tilesets, first and last. Initialize the first two.
        initQueue(
            Tileset(0, context, 0, screenWidth, screenHeight),      // the tileset with the number 0 has no obstacles
            Tileset((1..possibleTilesetCount).random(), context, screenWidth, screenWidth, screenHeight)
        )
    }

    fun drawTilesetsAndCheckCollisions(canvas: Canvas, velocity: Int, player: Player) {
        iterate()

        // draw the item if the tileset has one and check if the player picked up the item
        if (queue.first().hasItem) {
            queue.first().item.draw(canvas, velocity)
            queue.first().item.itemPickup(player, queue.first().item.activateEffect)
        }
        if (queue.last().hasItem) {
            queue.last().item.draw(canvas, velocity)
            queue.last().item.itemPickup(player, queue.last().item.activateEffect)
        }


        // draw the respective tileset and its obstacles
        queue.first().drawTileset(velocity)
        queue.first().obstacles.forEach {
            it.draw(canvas, velocity, velocity / 4)
        }
        queue.last().drawTileset(velocity)
        queue.last().obstacles.forEach {
            it.draw(canvas, velocity, velocity / 4)
        }


        collision = false  // sets collision to false by default

        //checks  if the player collides with an obstacle
        for (i in 0 until queue.first().obstacles.size) {
            if (this.checkCollisions(queue.first().obstacles[i], player)) collision = true
        }
        for (i in 0 until queue.last().obstacles.size) {
            if (this.checkCollisions(queue.last().obstacles[i], player)) collision = true
        }

        // checks if a new tileset needs to be added to the queue and if so, adds it
        insertTilesetifneedTo(tilesetList, possibleTilesetCount)
    }

    // Creates a hitbox from the player and one from the respective obstacle based on the bitmap
    //   -> intersect checks collision

    private fun checkCollisions(obstacle: Obstacles, player: Player): Boolean {
        val playerHitbox = Rect(
            player.charX,
            player.charY,
            (player.charX + player.rechar[1]!!.width),  // each character's sprite has the same size
            (player.charY + player.rechar[1]!!.height)
        )
        val obstacleHitbox = Rect(
            obstacle.changeableX,
            obstacle.changeableY,
            (obstacle.changeableX + obstacle.bmp.width),
            (obstacle.changeableY + obstacle.bmp.height)
        )
        if (Rect.intersects(obstacleHitbox, playerHitbox)) {
            // deal with collision
            setCollisionResult(
                obstacle.death,
                obstacle.changeableY,
                player.charY + player.rechar[1]!!.height,
                player
            )
            return true
        }
        return false
    }
}