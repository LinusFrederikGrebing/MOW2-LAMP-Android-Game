package de.thm.lampgame.controller.ObstaclesBitmaps

import android.graphics.Bitmap
import android.graphics.Canvas
import de.thm.lampgame.model.ObstacleModel


abstract class Obstacles(
    name: String,
    width: Int,
    height: Int,
    x: Int,
    y: Int,
    var death: Boolean
) : ObstacleModel(name,width,height,x,y) {

    lateinit var unsizedBmp: Bitmap
    lateinit var bmp: Bitmap

    abstract fun draw(canvas: Canvas, velocityX: Int, velocityY: Int)

}