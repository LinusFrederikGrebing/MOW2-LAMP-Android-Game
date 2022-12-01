package de.thm.lampgame.controller.tileset

import android.content.Context
import de.thm.lampgame.view.item.DoublePoints
import de.thm.lampgame.view.item.Torch
import de.thm.lampgame.view.item.BonusJump
import de.thm.lampgame.view.item.Immortality
import de.thm.lampgame.model.obstacles.ObstacleNames
import de.thm.lampgame.model.tileset.TilesetModel
import de.thm.lampgame.view.obstacles.*

class Tileset(
    tileset: Int,
    val context: Context,
    startX: Int,
    screenWidth: Int,
    screenHeight: Int
) {
    val tilesetModel = TilesetModel(startX, tileset, screenWidth, screenHeight)
    init {
        // first initializes all tileset variations and then adds the bitmaps
        tilesetModel.initTilesetWithItsObstacles()
        addBitmaps()
        initItems()
    }

   private fun initItems(){
       tilesetModel.dblPoints = DoublePoints(context, tilesetModel.height, tilesetModel.width, tilesetModel.itemX, tilesetModel.itemY)
       tilesetModel.bonusJump = BonusJump(context, tilesetModel.height, tilesetModel.width, tilesetModel.itemX, tilesetModel.itemY)
       tilesetModel.immortality = Immortality(context, tilesetModel.height, tilesetModel.width, tilesetModel.itemX, tilesetModel.itemY)
       tilesetModel.torch = Torch(context, tilesetModel.height, tilesetModel.width, tilesetModel.itemX, tilesetModel.itemY)
   }

    // assigns the associated bitmap to each tileset. The tileset name distinguishes them.
    private fun addBitmaps() {
        tilesetModel.obstaclesWithoutBitmaps.forEach {
            when (it.name) {
                ObstacleNames.GROUND -> tilesetModel.obstacles.add(BitmapGround(context, tilesetModel.width, tilesetModel.height))
                ObstacleNames.TERRAIN  -> tilesetModel.obstacles.add(BitmapTerrain(context, it.width, it.height, it.x, it.y))
                ObstacleNames.TUBE  -> tilesetModel.obstacles.add(BitmapTube(context, it.width, it.height, it.x, it.y))
                ObstacleNames.WATER  -> tilesetModel.obstacles.add(BitmapWater(context, it.width, it.height, it.x, it.y))
                ObstacleNames.SAW  -> tilesetModel.obstacles.add(BitmapSaw(context, it.width, it.height, it.x, it.y))
                ObstacleNames.BOUNCINGSAW  -> tilesetModel.obstacles.add(BitmapBouncingSaw(context, it.width, it.height, it.x, it.y))
            }
        }
    }
}