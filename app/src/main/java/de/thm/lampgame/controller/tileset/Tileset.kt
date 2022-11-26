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
) : TilesetModel(startX, tileset, screenWidth, screenHeight) {
    init {
        // first initializes all tileset variations and then adds the bitmaps
        initTilesetWithItsObstacles()
        addBitmaps()
        initItems()
    }

   private fun initItems(){
        dblPoints = DoublePoints(context, height, width, itemX, itemY)
        bonusJump = BonusJump(context, height, width, itemX, itemY)
        immortality = Immortality(context, height, width, itemX, itemY)
        torch = Torch(context, height, width, itemX, itemY)
   }

    // assigns the associated bitmap to each tileset. The tileset name distinguishes them.
    private fun addBitmaps() {
        obstaclesWithoutBitmaps.forEach {
            when (it.name) {
                ObstacleNames.GROUND -> obstacles.add(BitmapGround(context, width, height))
                ObstacleNames.TERRAIN  -> obstacles.add(BitmapTerrain(context, it.width, it.height, it.x, it.y))
                ObstacleNames.TUBE  -> obstacles.add(BitmapTube(context, it.width, it.height, it.x, it.y))
                ObstacleNames.WATER  -> obstacles.add(BitmapWater(context, it.width, it.height, it.x, it.y))
                ObstacleNames.SAW  -> obstacles.add(BitmapSaw(context, it.width, it.height, it.x, it.y))
                ObstacleNames.BOUNCINGSAW  -> obstacles.add(BitmapBouncingSaw(context, it.width, it.height, it.x, it.y))
            }
        }
    }

}