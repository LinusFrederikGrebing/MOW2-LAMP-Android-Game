package de.thm.lampgame.controller
import android.content.Context
import de.thm.lampgame.controller.items.DoublePoints
import de.thm.lampgame.controller.items.Item
import de.thm.lampgame.controller.items.Torch
import de.thm.lampgame.controller.terrain.*


class Tileset(val tileset: Int, val context: Context, var startX: Int, var startY: Int, val width: Int, val height: Int) {
    var obstacles: MutableList<Obstacles> = mutableListOf()
    lateinit var item: Item
    var hasItem = false
    var itemX = 0
    var itemY = 0
    init {
        randomTileset()
    }

    fun drawTileset(terrainVelocity : Int){
        startX -= terrainVelocity
    }

    fun placeTileset(startPos: Int) {
        startX = startPos
        setItemSpawnpoint()
    }

    fun randomTileset() {
        when (tileset) {
            1 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapTerrain(context, (0.50*width).toInt(), height, (0.20*width).toInt(), (0.45*height).toInt())))
            2 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapWater(context, (0.15*width).toInt(),(0.20*height).toInt(), (0.55*width).toInt(), (0.80*height).toInt())))
            3 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapTube(context, width, height, (0.30*width).toInt(), (0.65*height).toInt()), BitmapTube(context, width, height,(0.9*width).toInt(), (0.55*height).toInt())))
            4 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapTube(context, width, height, (0.50*width).toInt(), (0.65*height).toInt())))
            5 -> obstacles.addAll(listOf(BitmapGround(context, width, height), BitmapTube(context,  width, height, (0.40*width).toInt(), (0.55*height).toInt()), BitmapTube(context,  width, height, (0.75*width).toInt(), (0.65*height).toInt())))
            else -> println("Failed")
        }
    }

    fun setItemSpawnpoint() {
        when (tileset) {
            1 -> {itemX = (0.9*width).toInt(); itemY = (0.1*height).toInt()}
            2 -> {itemX = (0.55*width).toInt(); itemY = (0.5*height).toInt()}
            else -> println("Error Torch Spawn Point")
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