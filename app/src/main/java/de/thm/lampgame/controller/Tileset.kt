package de.thm.lampgame.controller
import de.thm.lampgame.controller.terrain.BitmapGround
import de.thm.lampgame.controller.terrain.Obstacles
import de.thm.lampgame.controller.terrain.BitmapTerrain
import android.content.Context
import de.thm.lampgame.controller.terrain.BitmapTube


class Tileset(val context: Context, var startX: Int, var startY: Int, val width: Int, val height: Int) {
    val obstacles: MutableList<Obstacles> = mutableListOf()
    init {
        randomTileset()
    }

    fun drawTileset(terrainVelocity : Int){
        startX -= terrainVelocity
    }


    private fun randomTileset() {
        when ((1..2).random()) {
            1 -> obstacles.addAll(listOf(BitmapGround(context, width), BitmapTerrain(context,1200, 100, 300)))
            2 -> obstacles.addAll(listOf(BitmapGround(context, width), BitmapTube(context,300, 700), BitmapTube(context,1300, 700)))
            else -> println("Failed")
        }
    }
}