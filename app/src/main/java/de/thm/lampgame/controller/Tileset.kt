package de.thm.lampgame.controller
import android.content.Context
import de.thm.lampgame.controller.items.DoublePoints
import de.thm.lampgame.controller.items.Torch
import de.thm.lampgame.controller.terrain.*
import de.thm.lampgame.model.TilesetModel

class Tileset(val tileset: Int, val context: Context, startX : Int, var startY: Int, width: Int, height: Int) : TilesetModel(startX, tileset, width, height) {
    init {
        randomTileset()
    }

    fun randomTileset() {
        when (tileset) {
            0 -> obstacles.addAll(listOf(BitmapGround(context, width, height)))
            1 -> obstacles.addAll(
                listOf(
                    BitmapGround(context, width, height),
                    BitmapTerrain(
                        context,
                        (0.50 * width).toInt(),
                        height,
                        (0.20 * startX).toInt(),
                        (0.45 * height).toInt()
                    )
                )
            )
            2 -> obstacles.addAll(
                listOf(
                    BitmapGround(context, width, height),
                    BitmapWater(
                        context,
                        (0.15 * width).toInt(),
                        (0.2 * height).toInt(),
                        (0.5 * width).toInt(),
                        (0.8 * height).toInt()
                    )
                )
            )
            3 -> obstacles.addAll(
                listOf(
                    BitmapGround(context, width, height),
                    BitmapTube(
                        context,
                        width,
                        height,
                        (0.30 * width).toInt(),
                        (0.65 * height).toInt()
                    ),
                    BitmapTube(
                        context,
                        width,
                        height,
                        (0.9 * width).toInt(),
                        (0.55 * height).toInt()
                    )
                )
            )
            4 -> obstacles.addAll(
                listOf(
                    BitmapGround(context, width, height),
                    BitmapTube(
                        context,
                        width,
                        height,
                        (0.50 * width).toInt(),
                        (0.65 * height).toInt()
                    )
                )
            )
            5 -> obstacles.addAll(
                listOf(
                    BitmapGround(context, width, height),
                    BitmapTube(
                        context,
                        width,
                        height,
                        (0.40 * width).toInt(),
                        (0.55 * height).toInt()
                    ),
                    BitmapTube(
                        context,
                        width,
                        height,
                        (0.75 * width).toInt(),
                        (0.65 * height).toInt()
                    )
                )
            )
            else -> println("Failed")
        }
    }


    fun randomItemSpawn(isTorch: Boolean){
        if (isTorch) {
            item = Torch(context,height,width,itemX,itemY)
            hasItem = true
        }
        else when ((1..10).random()) {
            1 -> { item = DoublePoints(context,height,width,itemX,itemY); hasItem = true}
            else -> hasItem = false
        }
    }

}