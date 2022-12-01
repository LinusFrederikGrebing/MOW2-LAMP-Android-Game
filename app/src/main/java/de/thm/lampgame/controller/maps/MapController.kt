package de.thm.lampgame.controller.maps

import android.graphics.Bitmap
import android.graphics.Canvas
import de.thm.lampgame.model.map.MapModel

abstract class MapController(screenWidth: Int, screenHeight: Int)  {
    var mapController = MapModel(screenWidth, screenHeight)
    lateinit var middle: Bitmap
    lateinit var background: Bitmap
    lateinit var vorne: Bitmap

    fun drawMapBack(canvas: Canvas, speed: Double, bmp: Bitmap) {
        setNewMapBackXCoords(speed)
        canvas.drawBitmap(bmp, mapBack.toFloat(), 0f, null)
        if (needToRepeatPartBack()) {
            canvas.drawBitmap(bmp, (mapBack + newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapBackMars(canvas: Canvas, speed: Double, bmp: Bitmap) {
        setNewMapBackXCoords(speed)
        canvas.drawBitmap(bmp, -700f, 0f, null)
        if (needToRepeatPartBack()) {
            canvas.drawBitmap(bmp, (mapBack + newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapFront(canvas: Canvas, speed: Double, bmp: Bitmap) {
        setNewMapFrontXCoords(speed)
        canvas.drawBitmap(bmp, mapFront.toFloat(), 0f, null)
        if (needToRepeatPartFront()) {
            canvas.drawBitmap(bmp, (mapFront + newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapMiddle(canvas: Canvas, speed: Double, bmp: Bitmap) {
        setNewMapMiddleXCoords(speed)
        canvas.drawBitmap(bmp, mapMiddle.toFloat(), 0f, null)
        if (needToRepeatPartMiddle()) {
            canvas.drawBitmap(bmp, (mapMiddle + newWidth).toFloat(), 0f, null)
        }
    }

    abstract fun drawMap(canvas: Canvas, speedBack: Double, speedMiddle: Double, speedFront: Double)
}