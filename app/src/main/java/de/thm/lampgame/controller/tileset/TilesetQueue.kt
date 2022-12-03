package de.thm.lampgame.controller.tileset

import android.content.Context
import android.graphics.Canvas
import de.thm.lampgame.controller.Player
import de.thm.lampgame.model.tileset.TilesetQueueModel

class TilesetQueue(context: Context, screenWidth: Int, screenHeight: Int) {
    // creates the associated model
    val tilesetQueueModel = TilesetQueueModel(screenWidth, screenHeight)

    init {
        // initialize each possible tileset once
        for (i in 1..tilesetQueueModel.possibleTilesetCount) {
            tilesetQueueModel.tilesetList.add(
                Tileset(
                    i,
                    context,
                    screenWidth,
                    screenWidth,
                    screenHeight
                )
            )
        }

        // the tilesetqueue consists of two tilesets, first and last. Initialize the first two.
        tilesetQueueModel.initQueue(
            Tileset(
                0,
                context,
                0,
                screenWidth,
                screenHeight
            ),
            Tileset(
                (1..tilesetQueueModel.possibleTilesetCount).random(),
                context,
                screenWidth,
                screenWidth,
                screenHeight
            )
            // the first tileset stays at 0 and the second tileset is set to the "new tileset number".
        )
    }

    fun drawTilesetsAndCheckCollisions(canvas: Canvas, velocity: Int, player: Player) {
        tilesetQueueModel.iterate()

        // draw the item if the tileset has one and check if the player picked up the item
        if (tilesetQueueModel.queue.first().tilesetModel.hasItem) {
            tilesetQueueModel.queue.first().tilesetModel.item.draw(canvas, velocity)
            tilesetQueueModel.queue.first().tilesetModel.item.itemModel.itemPickup(
                player,
                tilesetQueueModel.queue.first().tilesetModel.item.itemModel.activateEffect
            )
        }
        if (tilesetQueueModel.queue.last().tilesetModel.hasItem) {
            tilesetQueueModel.queue.last().tilesetModel.item.draw(canvas, velocity)
            tilesetQueueModel.queue.last().tilesetModel.item.itemModel.itemPickup(
                player,
                tilesetQueueModel.queue.last().tilesetModel.item.itemModel.activateEffect
            )
        }

        // draw the respective tileset and its obstacles
        tilesetQueueModel.queue.first().tilesetModel.drawTileset(velocity)
        tilesetQueueModel.queue.first().tilesetModel.obstacles.forEach {
            it.draw(canvas, velocity, velocity / 4)
        }
        tilesetQueueModel.queue.last().tilesetModel.drawTileset(velocity)
        tilesetQueueModel.queue.last().tilesetModel.obstacles.forEach {
            it.draw(canvas, velocity, velocity / 4)
        }

        // checks whether there is a collision and sets the event
        tilesetQueueModel.collisionCheck(player)

        // checks if a new tileset needs to be added to the queue and if so, adds it
        tilesetQueueModel.insertTilesetIfNeeded()
    }

}