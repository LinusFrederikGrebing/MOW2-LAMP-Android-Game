package de.thm.lampgame.controller.tileset

import android.content.Context
import de.thm.lampgame.controller.items.DoublePoints
import de.thm.lampgame.controller.items.Torch
import de.thm.lampgame.controller.obstaclesBitmaps.*
import de.thm.lampgame.controller.items.BonusJump
import de.thm.lampgame.controller.items.Immortality
import de.thm.lampgame.model.obstacles.ObstacleNames
import de.thm.lampgame.model.tileset.TilesetModel

class Tileset(
    tileset: Int,
    val context: Context,
    startX: Int,
    screenWidth: Int,
    screenHeight: Int
) : TilesetModel(startX, tileset, screenWidth, screenHeight) {
    init {
        randomTileset()
        addBitmaps()
    }

    private fun addBitmaps() {
        obstaclesWithoutBitmaps.forEach {
            when (it.name) {
                ObstacleNames.GROUND -> obstacles.add(BitmapGround(context, width, height))
                ObstacleNames.TERRAIN  -> obstacles.add(BitmapTerrain(context, it.width, it.height, it.x, it.y))
                ObstacleNames.TUBE  -> obstacles.add(BitmapTube(context, it.width, it.height, it.x, it.y))
                ObstacleNames.WATER  -> obstacles.add(BitmapWater(context, it.width, it.height, it.x, it.y))
                ObstacleNames.SAW  -> obstacles.add(BitmapSaw(context, it.width, it.height, it.x, it.y))
                ObstacleNames.BOUNCINGSAW  -> obstacles.add(
                    BitmapBouncingSaw(
                        context,
                        it.width,
                        it.height,
                        it.x,
                        it.y
                    )
                )
            }
        }
    }

    fun randomItemSpawn(isTorch: Boolean) {
        if (isTorch) {
            item = Torch(context, height, width, itemX, itemY)
            hasItem = true
        } else when ((1..6).random()) {
            1 -> {
                item = DoublePoints(context, height, width, itemX, itemY); hasItem = true
            }
            2 -> {
                item = BonusJump(context, height, width, itemX, itemY); hasItem = true
            }
            3 -> {
                item = Immortality(context, height, width, itemX, itemY); hasItem = true
            }
            else -> hasItem = false
        }
    }

}