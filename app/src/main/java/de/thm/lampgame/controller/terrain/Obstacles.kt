package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import de.thm.lampgame.model.ObstacleModel


abstract class Obstacles(
    val width: Int,
    val height: Int,
    x: Int,
    var y: Int,
    var death: Boolean
) : ObstacleModel(x) {

    lateinit var unsizedBmp: Bitmap
    lateinit var bmp: Bitmap

    abstract fun draw(canvas: Canvas, velocity: Int)

}