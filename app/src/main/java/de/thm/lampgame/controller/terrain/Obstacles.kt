package de.thm.lampgame.controller.terrain

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas


open abstract class Obstacles(context: Context,val height: Int,val width: Int,var x: Int,var y: Int, var death : Boolean) {

    var changeableX = x
    lateinit var unsizedBmp: Bitmap
    lateinit var bmp: Bitmap

    abstract fun draw(canvas: Canvas, velocity: Int)

}