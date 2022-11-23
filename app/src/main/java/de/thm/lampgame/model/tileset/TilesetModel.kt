package de.thm.lampgame.model.tileset

import de.thm.lampgame.controller.items.Item
import de.thm.lampgame.controller.obstaclesBitmaps.Obstacles
import de.thm.lampgame.model.obstacles.ObstacleModel
import de.thm.lampgame.model.obstacles.ObstacleNames

open class TilesetModel(var startX: Int, var tilesetNr: Int, var width: Int, var height: Int) {
    var obstacles: MutableList<Obstacles> = mutableListOf()
    var obstaclesWithoutBitmaps: MutableList<ObstacleModel> = mutableListOf()
    lateinit var item: Item
    lateinit var dblPoints : Item
    lateinit var bonusJump : Item
    lateinit var immortality : Item
    lateinit var torch : Item
    var hasItem = false
    var itemX = 0
    var itemY = 0

    fun setItemSpawnpoint() {
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
                itemX = (0.6 * width).toInt(); itemY = (0.6 * height).toInt()
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
            else -> println("Error Torch Spawn Point")
        }
    }

    fun randomItemSpawn(isTorch: Boolean) {
        if (isTorch) {
            item = torch ; item.x = itemX ; item.y = itemY ; hasItem = true ; item.pickedUp = false

        } else when ((1..6).random()) {
            1 -> {
                item = dblPoints ; item.x = itemX; item.y = itemY; hasItem = true ; item.pickedUp = false
            }
            2 -> {
                item = bonusJump ; item.x = itemX; item.y = itemY; hasItem = true ; item.pickedUp = false
            }
            3 -> {
                item = immortality ; item.x = itemX; item.y = itemY; hasItem = true ; item.pickedUp = false
            }
            else -> hasItem = false
        }
    }

    fun initTilesetWithItsObstacles() {
        when (tilesetNr) {
            0 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        ObstacleNames.GROUND,
                        width,
                        height,
                        0,
                        (0.9 * height).toInt()
                    )
                )
            )
            1 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.60 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.45 * height).toInt()
                    )
                )
            )
            2 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.15 * width).toInt(),
                        height,
                        (0.5 * width).toInt(),
                        height
                    )
                )
            )
            3 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.30 * width).toInt(),
                        (0.65 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.9 * width).toInt(),
                        (0.55 * height).toInt()
                    )
                )
            )
            4 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.50 * width).toInt(),
                        (0.65 * height).toInt()
                    )
                )
            )
            5 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.40 * width).toInt(),
                        (0.55 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.75 * width).toInt(),
                        (0.65 * height).toInt()
                    )
                )
            )
            6 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.SAW,
                        width,
                        height,
                        width,
                        (0.63 * height).toInt()
                    )
                )
            )
            7 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.BOUNCINGSAW,
                        width,
                        height,
                        (0.5 * width).toInt(),
                        (0.63 * height).toInt()
                    )
                )
            )
            8 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.60 * width).toInt(),
                        (0.45 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.10 * width).toInt(),
                        (0.45 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.30 * width).toInt(),
                         height,
                        (0.3 * width).toInt(),
                        height
                    )
                )
            )
            9 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.SAW,
                        width,
                        height,
                        width,
                        (0.33 * height).toInt()
                    )
                )
            )
            10 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.65 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.60 * width).toInt(),
                        (0.45 * height).toInt()
                    ),
                )
            )
            11 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.1 * width).toInt(),
                        (0.65 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.40 * width).toInt(),
                        (0.45 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.25 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.70 * width).toInt(),
                        (0.25 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.8 * width).toInt(),
                        height,
                        (0.1 * width).toInt(),
                        height
                    )
                )
            )
            12 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.80 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.15 * height).toInt()
                    ),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.45 * width).toInt(),
                        (0.55 * height).toInt()
                    ),
                )
            )
            13 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.45 * width).toInt(),
                        (0.35 * height).toInt()
                    ),
                )
            )
            14 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.10 * width).toInt(),
                        height,
                        (0.1 * width).toInt(),
                        height
                    ),
                    ObstacleModel(
                        ObstacleNames.WATER,
                        (0.10 * width).toInt(),
                        height,
                        (0.7 * width).toInt(),
                        height
                    )
                )
            )
            15 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        ObstacleNames.TERRAIN,
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.45 * height).toInt()
                    ),

                    ObstacleModel(
                        ObstacleNames.TUBE,
                        width,
                        height,
                        (0.7 * width).toInt(),
                        (0.55 * height).toInt()
                    ),
                )
            )
            16 -> obstaclesWithoutBitmaps.addAll(
            listOf(
                ObstacleModel(ObstacleNames.GROUND, width, height, 0, (0.90 * height).toInt()),
                ObstacleModel(
                    ObstacleNames.TUBE,
                    width,
                    height,
                    (0.7 * width).toInt(),
                    (0.65 * height).toInt()
                ),
                ObstacleModel(
                    ObstacleNames.TUBE,
                    width,
                    height,
                    (0.5 * width).toInt(),
                    (0.55 * height).toInt()
                ),
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