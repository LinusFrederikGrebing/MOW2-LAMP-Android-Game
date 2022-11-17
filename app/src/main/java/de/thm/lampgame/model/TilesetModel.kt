package de.thm.lampgame.model

import de.thm.lampgame.controller.items.Item
import de.thm.lampgame.controller.ObstaclesBitmaps.Obstacles

open class TilesetModel(var startX: Int, var tilesetNr: Int, var width: Int, var height: Int) {
    var obstacles: MutableList<Obstacles> = mutableListOf()
    var obstaclesWithoutBitmaps: MutableList<ObstacleModel> = mutableListOf()
    lateinit var item: Item
    var hasItem = false
    var itemX = 0
    var itemY = 0


    fun placeTileset(startPos: Int) {
        startX = startPos
        setItemSpawnpoint()
    }

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
            else -> println("Error Torch Spawn Point")
        }
    }

    fun randomTileset() {
        when (tilesetNr) {
            0 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel(
                        "ground",
                        width,
                        height,
                        0,
                        (0.9 * height).toInt()
                    )
                )
            )
            1 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "terrain",
                        (0.60 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.45 * height).toInt()
                    )
                )
            )
            2 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "water",
                        (0.15 * width).toInt(),
                        height,
                        (0.5 * width).toInt(),
                        height
                    )
                )
            )
            3 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "tube",
                        width,
                        height,
                        (0.30 * width).toInt(),
                        (0.65 * height).toInt()
                    ),
                    ObstacleModel(
                        "tube",
                        width,
                        height,
                        (0.9 * width).toInt(),
                        (0.55 * height).toInt()
                    )
                )
            )
            4 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "tube",
                        width,
                        height,
                        (0.50 * width).toInt(),
                        (0.65 * height).toInt()
                    )
                )
            )
            5 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "tube",
                        width,
                        height,
                        (0.40 * width).toInt(),
                        (0.55 * height).toInt()
                    ),
                    ObstacleModel(
                        "tube",
                        width,
                        height,
                        (0.75 * width).toInt(),
                        (0.65 * height).toInt()
                    )
                )
            )
            6 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "saw",
                        width,
                        height,
                        width,
                        (0.63 * height).toInt()
                    )
                )
            )
            7 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "waterenemy",
                        width,
                        height,
                        (0.5 * width).toInt(),
                        (0.63 * height).toInt()
                    )
                )
            )
            8 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "terrain",
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.60 * width).toInt(),
                        (0.45 * height).toInt()
                    ),
                    ObstacleModel(
                        "terrain",
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.10 * width).toInt(),
                        (0.45 * height).toInt()
                    ),
                    ObstacleModel(
                        "water",
                        (0.30 * width).toInt(),
                         height,
                        (0.4 * width).toInt(),
                        height
                    )
                )
            )
            9 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "saw",
                        width,
                        height,
                        width,
                        (0.33 * height).toInt()
                    )
                )
            )
            10 -> obstaclesWithoutBitmaps.addAll(
                listOf(
                    ObstacleModel("ground", width, height, 0, (0.90 * height).toInt()),
                    ObstacleModel(
                        "terrain",
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.20 * width).toInt(),
                        (0.65 * height).toInt()
                    ),
                    ObstacleModel(
                        "terrain",
                        (0.30 * width).toInt(),
                        (0.1 * height).toInt(),
                        (0.60 * width).toInt(),
                        (0.45 * height).toInt()
                    ),
                )
            )
            else -> println("Failed")
        }
    }

    fun drawTileset(terrainVelocity: Int) {
        startX -= terrainVelocity
    }
}