package de.thm.lampgame.controller.tileset

import android.content.Context
import android.graphics.Canvas
import de.thm.lampgame.controller.Player
import de.thm.lampgame.model.tileset.TilesetQueueModel

class TilesetQueue(context: Context, screenWidth: Int, screenHeight: Int) :
    TilesetQueueModel(screenWidth, screenHeight) {
    init {
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

        collisionCheck(player)

        // checks if a new tileset needs to be added to the queue and if so, adds it
        insertTilesetifneedTo()
    }

}