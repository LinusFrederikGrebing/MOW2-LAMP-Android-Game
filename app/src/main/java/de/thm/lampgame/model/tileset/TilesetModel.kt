package de.thm.lampgame.model.tileset

import de.thm.lampgame.controller.item.ItemController
import de.thm.lampgame.controller.obstacles.ObstacleController
import de.thm.lampgame.model.obstacles.ObstacleModel
import de.thm.lampgame.model.obstacles.ObstacleNames

open class TilesetModel(
    var startX: Double,
    private var tilesetNr: Int,
    var width: Int,
    var height: Int
) {
    // represents the tileset based on the descriptions without the associated bitmaps
    var obstacles: MutableList<ObstacleController> = mutableListOf()
    // represents the tileset descriptions without the associated bitmaps
    var obstaclesWithoutBitmaps: MutableList<ObstacleModel> = mutableListOf()
    // the respective items are stored with their bitmaps under the following variables and can be drawn in the TilesetqueueController
    lateinit var item: ItemController
    lateinit var dblPoints: ItemController
    lateinit var bonusJump: ItemController
    lateinit var immortality: ItemController
    lateinit var torch: ItemController
    // tilesets can also exist without items
    var hasItem = false
    var itemX = 0.0
    var itemY = 0.0

    // sets different item coordinates depending on the tileset id
    private fun setItemSpawnpoint() {
        when (tilesetNr) {
            1 -> {
                itemX = (0.9 * width); itemY = (0.1 * height)
            }
            2 -> {
                itemX = (0.55 * width); itemY = (0.5 * height)
            }
            3 -> {
                itemX = (0.55 * width); itemY = (0.6 * height)
            }
            4 -> {
                itemX = (0.7 * width); itemY = (0.25 * height)
            }
            5 -> {
                itemX = (0.15 * width); itemY = (0.2 * height)
            }
            6 -> {
                itemX = (0.6 * width); itemY = (0.6 * height)
            }
            7 -> {
                itemX = (0.15 * width); itemY = (0.6 * height)
            }
            8 -> {
                itemX = (0.65 * width); itemY = (0.3 * height)
            }
            9 -> {
                itemX = (0.30 * width); itemY = (0.5 * height)
            }
            10 -> {
                itemX = (0.52 * width); itemY = (0.45 * height)
            }
            11 -> {
                itemX = (0.6 * width); itemY = (0.1 * height)
            }
            12 -> {
                itemX = (0.15 * width); itemY = (0.6 * height)
            }
            13 -> {
                itemX = (0.65 * width); itemY = (0.3 * height)
            }
            14 -> {
                itemX = (0.30 * width); itemY = (0.5 * height)
            }
            15 -> {
                itemX = (0.52 * width); itemY = (0.45 * height)
            }
            16 -> {
                itemX = (0.60 * width); itemY = (0.5 * height)
            }
            17 -> {
                itemX = 0.0; itemY = (0.5 * height)
            }
            18 -> {
                itemX = width.toDouble(); itemY = (0.1 * height)
            }
            19 -> {
                itemX = (0.7 * width); itemY = (0.5 * height)
            }
            20 -> {
                itemX = (0.5 * width); itemY = (0.6 * height)
            }
            else -> println("Error Torch Spawn Point")
        }
    }

    // either the item belonging to the tileset is set to torch or one of the 3 other items is spawned with a probability of 1:2
    fun randomItemSpawn(isTorch: Boolean) {
        if (isTorch) {
            item = torch; item.itemModel.x = itemX; item.itemModel.y = itemY; hasItem =
                true; item.itemModel.isPickedUp = false

        } else when ((1..6).random()) {
            1 -> {
                item = dblPoints; item.itemModel.x = itemX; item.itemModel.y = itemY; hasItem =
                    true; item.itemModel.isPickedUp = false
            }
            2 -> {
                item = bonusJump; item.itemModel.x = itemX; item.itemModel.y = itemY; hasItem =
                    true; item.itemModel.isPickedUp = false
            }
            3 -> {
                item = immortality; item.itemModel.x = itemX; item.itemModel.y = itemY; hasItem =
                    true; item.itemModel.isPickedUp = false
            }
            else -> hasItem = false
        }
    }

    // depending on which tileset id is passed, the tileset stored under that id will be returned with the associated obstacles
    fun initTilesetWithItsObstacles() {
        when (tilesetNr) {
            0 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.9 * height),
                        false
                    )
                )
            )
            1 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.60 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width),
                        (0.45 * height),
                        false
                    )
                )
            )
            2 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.15 * width).toInt(),
                        height,
                        (0.5 * width),
                        height.toDouble(),
                        true
                    )
                )
            )
            3 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.30 * width),
                        (0.65 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.9 * width),
                        (0.55 * height),
                        false
                    )
                )
            )
            4 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.50 * width),
                        (0.65 * height),
                        false
                    )
                )
            )
            5 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.40 * width),
                        (0.55 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.75 * width),
                        (0.65 * height),
                        false
                    )
                )
            )
            6 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.SAW,
                        width,
                        height,
                        width.toDouble(),
                        (0.63 * height),
                        true
                    )
                )
            )
            7 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.BOUNCINGSAW,
                        width,
                        height,
                        (0.5 * width),
                        (0.63 * height),
                        true
                    )
                )
            )
            8 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.60 * width),
                        (0.45 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.10 * width),
                        (0.45 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.10 * width).toInt(),
                        height,
                        (0.10 * width),
                        height.toDouble(),
                        true
                    )
                )
            )
            9 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.SAW,
                        width,
                        height,
                        width.toDouble(),
                        (0.33 * height),
                        true
                    )
                )
            )
            10 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width),
                        (0.65 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.60 * width),
                        (0.45 * height),
                        false
                    ),
                )
            )
            11 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.1 * width),
                        (0.65 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.40 * width),
                        (0.45 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.70 * width),
                        (0.25 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.267 * width).toInt(),
                        height,
                        (0.1 * width),
                        height.toDouble(),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.267 * width).toInt(),
                        height,
                        (0.3 * width),
                        height.toDouble(),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.267 * width).toInt(),
                        height,
                        (0.5 * width),
                        height.toDouble(),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.267 * width).toInt(),
                        height,
                        (0.7 * width),
                        height.toDouble(),
                        true
                    )
                )
            )
            12 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.80 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width),
                        (0.15 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.45 * width),
                        (0.70 * height),
                        false
                    ),
                )
            )
            13 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.45 * width),
                        (0.55 * height),
                        false
                    ),
                )
            )
            14 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.10 * width).toInt(),
                        height,
                        (0.1 * width),
                        height.toDouble(),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.10 * width).toInt(),
                        height,
                        (0.7 * width),
                        height.toDouble(),
                        true
                    )
                )
            )
            15 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width),
                        (0.45 * height),
                        false
                    ),

                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.7 * width),
                        (0.55 * height),
                        false
                    ),
                )
            )
            16 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.7 * width),
                        (0.65 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.5 * width),
                        (0.55 * height),
                        false
                    ),
                )
            )
            17 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.2 * width),
                        (0.5 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.1 * width).toInt(),
                        height,
                        (0.5 * width),
                        height.toDouble(),
                        false
                    )
                )
            )
            18 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.2 * width),
                        (0.45 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.13 * width).toInt(),
                        height,
                        (0.5 * width),
                        height.toDouble(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.1 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.9 * width),
                        (0.5 * height),
                        false
                    )
                )
            )
            19 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.03 * width).toInt(),
                        height,
                        (0.1 * width),
                        height.toDouble(),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.03 * width).toInt(),
                        height,
                        (0.35 * width),
                        height.toDouble(),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.03 * width).toInt(),
                        height,
                        (0.60 * width),
                        height.toDouble(),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.03 * width).toInt(),
                        height,
                        (0.85 * width),
                        height.toDouble(),
                        true
                    )
                )
            )
            20 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0.0,
                        (0.90 * height),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.SAW,
                        width,
                        height,
                        width.toDouble(),
                        (0.63 * height),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.BOUNCINGSAW,
                        width,
                        height,
                        (0.8 * width),
                        (0.63 * height),
                        true
                    )
                )
            )
            else -> println("Failed")
        }
    }

    fun placeTileset(startPos: Double) {
        startX = startPos
        setItemSpawnpoint()
    }

    fun drawTileset(terrainVelocity: Double) {
        startX -= terrainVelocity
    }
}