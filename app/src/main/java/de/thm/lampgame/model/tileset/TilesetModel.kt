package de.thm.lampgame.model.tileset

import de.thm.lampgame.controller.item.ItemController
import de.thm.lampgame.controller.obstacles.ObstacleController
import de.thm.lampgame.model.obstacles.ObstacleModel
import de.thm.lampgame.model.obstacles.ObstacleNames

open class TilesetModel(
    var startX: Int,
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
    var itemX = 0
    var itemY = 0

    // sets different item coordinates depending on the tileset id
    private fun setItemSpawnpoint() {
        when (tilesetNr) {
            1 -> {
                itemX = (0.9 * width).toInt(); itemY = (0.1 * height).toInt()
            }
            2 -> {
                itemX = (0.55 * width).toInt(); itemY = (0.5 * height).toInt()
            }
            3 -> {
                itemX = (0.55 * width).toInt(); itemY = (0.6 * height).toInt()
            }
            4 -> {
                itemX = (0.7 * width).toInt(); itemY = (0.25 * height).toInt()
            }
            5 -> {
                itemX = (0.15 * width).toInt(); itemY = (0.2 * height).toInt()
            }
            6 -> {
                itemX = (0.6 * width).toInt(); itemY = (0.6 * height).toInt()
            }
            7 -> {
                itemX = (0.15 * width).toInt(); itemY = (0.6 * height).toInt()
            }
            8 -> {
                itemX = (0.65 * width).toInt(); itemY = (0.3 * height).toInt()
            }
            9 -> {
                itemX = (0.30 * width).toInt(); itemY = (0.5 * height).toInt()
            }
            10 -> {
                itemX = (0.52 * width).toInt(); itemY = (0.45 * height).toInt()
            }
            11 -> {
                itemX = (0.6 * width).toInt(); itemY = (0.1 * height).toInt()
            }
            12 -> {
                itemX = (0.15 * width).toInt(); itemY = (0.6 * height).toInt()
            }
            13 -> {
                itemX = (0.65 * width).toInt(); itemY = (0.3 * height).toInt()
            }
            14 -> {
                itemX = (0.30 * width).toInt(); itemY = (0.5 * height).toInt()
            }
            15 -> {
                itemX = (0.52 * width).toInt(); itemY = (0.45 * height).toInt()
            }
            16 -> {
                itemX = (0.60 * width).toInt(); itemY = (0.5 * height).toInt()
            }
            17 -> {
                itemX = 0; itemY = (0.5 * height).toInt()
            }
            18 -> {
                itemX = width; itemY = (0.1 * height).toInt()
            }
            19 -> {
                itemX = (0.7 * width).toInt(); itemY = (0.5 * height).toInt()
            }
            20 -> {
                itemX = (0.5 * width).toInt(); itemY = (0.6 * height).toInt()
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
                        0,
                        (0.9 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.60 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.45 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.15 * width).toInt(),
                        height,
                        (0.5 * width).toInt(),
                        height,
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.30 * width).toInt(),
                        (0.65 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.9 * width).toInt(),
                        (0.55 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.50 * width).toInt(),
                        (0.65 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.40 * width).toInt(),
                        (0.55 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.75 * width).toInt(),
                        (0.65 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.SAW,
                        width,
                        height,
                        width,
                        (0.63 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.BOUNCINGSAW,
                        width,
                        height,
                        (0.5 * width).toInt(),
                        (0.63 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.60 * width).toInt(),
                        (0.45 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.10 * width).toInt(),
                        (0.45 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.10 * width).toInt(),
                        height,
                        (0.10 * width).toInt(),
                        height,
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.SAW,
                        width,
                        height,
                        width,
                        (0.33 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.65 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.60 * width).toInt(),
                        (0.45 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.1 * width).toInt(),
                        (0.65 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.40 * width).toInt(),
                        (0.45 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.70 * width).toInt(),
                        (0.25 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.267 * width).toInt(),
                        height,
                        (0.1 * width).toInt(),
                        height,
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.267 * width).toInt(),
                        height,
                        (0.3 * width).toInt(),
                        height,
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.267 * width).toInt(),
                        height,
                        (0.5 * width).toInt(),
                        height,
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.267 * width).toInt(),
                        height,
                        (0.7 * width).toInt(),
                        height,
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.80 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.15 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.45 * width).toInt(),
                        (0.70 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.45 * width).toInt(),
                        (0.55 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.10 * width).toInt(),
                        height,
                        (0.1 * width).toInt(),
                        height,
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.10 * width).toInt(),
                        height,
                        (0.7 * width).toInt(),
                        height,
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.45 * height).toInt(),
                        false
                    ),

                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.7 * width).toInt(),
                        (0.55 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.7 * width).toInt(),
                        (0.65 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.5 * width).toInt(),
                        (0.55 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.2 * width).toInt(),
                        (0.5 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.1 * width).toInt(),
                        height,
                        (0.5 * width).toInt(),
                        height,
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.2 * width).toInt(),
                        (0.45 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.13 * width).toInt(),
                        height,
                        (0.5 * width).toInt(),
                        height,
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.1 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.9 * width).toInt(),
                        (0.5 * height).toInt(),
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.03 * width).toInt(),
                        height,
                        (0.1 * width).toInt(),
                        height,
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.03 * width).toInt(),
                        height,
                        (0.35 * width).toInt(),
                        height,
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.03 * width).toInt(),
                        height,
                        (0.60 * width).toInt(),
                        height,
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.03 * width).toInt(),
                        height,
                        (0.85 * width).toInt(),
                        height,
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
                        0,
                        (0.90 * height).toInt(),
                        false
                    ),
                    ObstacleModel(
                        ObstacleNames.SAW,
                        width,
                        height,
                        width,
                        (0.63 * height).toInt(),
                        true
                    ),
                    ObstacleModel(
                        ObstacleNames.BOUNCINGSAW,
                        width,
                        height,
                        (0.8 * width).toInt(),
                        (0.63 * height).toInt(),
                        true
                    )
                )
            )
            else -> println("Failed")
        }
    }

    fun placeTileset(startPos: Int) {
        startX = startPos
        setItemSpawnpoint()
    }

    fun drawTileset(terrainVelocity: Int) {
        startX -= terrainVelocity
    }
}