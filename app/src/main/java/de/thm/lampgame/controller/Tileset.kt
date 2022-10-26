package de.thm.lampgame.controller
import android.content.Context
import de.thm.lampgame.controller.terrain.*


class Tileset(val context: Context, var startX: Int, var startY: Int, val width: Int, val height: Int) {
    val obstacles: MutableList<Obstacles> = mutableListOf()
    init {
        randomTileset()
    }

    fun drawTileset(terrainVelocity : Int){
        startX -= terrainVelocity
    }


    private fun randomTileset() {
        when ((1..5).random()) {
            1 -> obstacles.addAll(listOf(BitmapGround(context, width), BitmapTerrain(context,1200, 100, 300)))
            2 -> obstacles.addAll(listOf(BitmapGround(context, width), BitmapWater(context, 1000, 840)))
            3 -> obstacles.addAll(listOf(BitmapGround(context, width), BitmapTube(context,300, 700), BitmapTube(context,1300, 700)))
            4 -> obstacles.addAll(listOf(BitmapGround(context, width), BitmapTube(context,500, 700)))
            5 -> obstacles.addAll(listOf(BitmapGround(context, width), BitmapTube(context,100, 600), BitmapTube(context,800, 700)))
            else -> println("Failed")
        }
    }
}