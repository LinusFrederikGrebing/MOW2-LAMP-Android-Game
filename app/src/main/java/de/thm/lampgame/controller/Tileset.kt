package de.thm.lampgame.controller

import android.content.Context
import de.thm.lampgame.controller.items.DoublePoints
import de.thm.lampgame.controller.items.Torch
import de.thm.lampgame.controller.ObstaclesBitmaps.*
import de.thm.lampgame.model.TilesetModel

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
                "ground" -> obstacles.add(BitmapGround(context, width, height))
                "terrain" -> obstacles.add(BitmapTerrain(context, it.width, it.height, it.x, it.y))
                "tube" -> obstacles.add(BitmapTube(context, it.width, it.height, it.x, it.y))
                "water" -> obstacles.add(BitmapWater(context, it.width, it.height, it.x, it.y))
                "saw" -> obstacles.add(BitmapSaw(context, it.width, it.height, it.x, it.y))
                "waterenemy" -> obstacles.add(
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
        } else when ((1..2).random()) {
            1 -> {
                item = DoublePoints(context, height, width, itemX, itemY); hasItem = true
            }
            else -> hasItem = false
        }
    }

}