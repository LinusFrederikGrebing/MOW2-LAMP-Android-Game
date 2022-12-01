package de.thm.lampgame.controller.tileset

import android.content.Context
import android.graphics.Canvas
import de.thm.lampgame.controller.Player
import de.thm.lampgame.model.tileset.TilesetQueueModel

class TilesetQueue(context: Context, screenWidth: Int, screenHeight: Int) {
    val tilesetQueueModel = TilesetQueueModel(screenWidth, screenHeight)
    init {
        // initialize each possible tileset once
        for (i in 1..tilesetQueueModel.possibleTilesetCount) {
            tilesetQueueModel.tilesetList.add(Tileset(i, context, screenWidth, screenWidth, screenHeight))
            //TEST durch ersetzen der "i" Variable.
        }

        // the tilesetqueue consists of two tilesets, first and last. Initialize the first two.
        tilesetQueueModel.initQueue(
            Tileset(0, context, 0, screenWidth, screenHeight),      // the tileset with the number 0 has no obstacles // TEST DURCH das ändern des 0
            Tileset((1..tilesetQueueModel.possibleTilesetCount).random(), context, screenWidth, screenWidth, screenHeight) // TEST DURCH ändern der maximalen Anzahl
        // 1. Tileset auf 0 lassne und das 2. Tileset auf "neue Tileset nummer" setzen.
        )
    }

    fun drawTilesetsAndCheckCollisions(canvas: Canvas, velocity: Int, player: Player) {
        tilesetQueueModel.iterate()

        // draw the item if the tileset has one and check if the player picked up the item
        if (tilesetQueueModel.queue.first().tilesetModel.hasItem) {
            tilesetQueueModel.queue.first().tilesetModel.item.draw(canvas, velocity)
            tilesetQueueModel.queue.first().tilesetModel.item.itemModel.itemPickup(player, tilesetQueueModel.queue.first().tilesetModel.item.itemModel.activateEffect)
        }
        if (tilesetQueueModel.queue.last().tilesetModel.hasItem) {
            tilesetQueueModel.queue.last().tilesetModel.item.draw(canvas, velocity)
            tilesetQueueModel.queue.last().tilesetModel.item.itemModel.itemPickup(player, tilesetQueueModel.queue.last().tilesetModel.item.itemModel.activateEffect)
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

        tilesetQueueModel.collisionCheck(player)

        // checks if a new tileset needs to be added to the queue and if so, adds it
        tilesetQueueModel.insertTilesetifneedTo()
    }

}