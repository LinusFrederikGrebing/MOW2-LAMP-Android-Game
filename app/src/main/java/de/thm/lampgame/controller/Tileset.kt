package de.thm.lampgame.controller
import android.content.Context
import de.thm.lampgame.controller.terrain.*


class Tileset(val context: Context, var startX: Int, var startY: Int, val width: Int, val height: Int) {
    var obstacles: MutableList<Obstacles> = mutableListOf()
    init {
        randomTileset()
    }

    fun drawTileset(terrainVelocity : Int){
        startX -= terrainVelocity
    }


    fun randomTileset() {
        when ((1..5).random()) {
            1 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapTerrain(context, (0.50*width).toInt(), height, (0.20*startX).toInt(), (0.45*height).toInt())))
            2 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapWater(context, (0.15*width).toInt(),(0.20*height).toInt(), (0.55*startX).toInt(), (0.80*height).toInt())))
            3 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapTube(context, width, height, (0.30*width).toInt(), (0.65*height).toInt()), BitmapTube(context, width, height,(0.9*width).toInt(), (0.55*height).toInt())))
            4 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapTube(context, width, height, (0.50*width).toInt(), (0.65*height).toInt())))
            5 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapTube(context,  width, height, (0.40*width).toInt(), (0.55*height).toInt()), BitmapTube(context,  width, height, (0.75*width).toInt(), (0.65*height).toInt())))
            else -> println("Failed")
        }
    }

}