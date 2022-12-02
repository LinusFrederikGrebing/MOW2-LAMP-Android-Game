package de.thm.lampgame.controller.maps

import android.graphics.Bitmap
import android.graphics.Canvas
import de.thm.lampgame.model.map.MapModel

abstract class MapController(screenWidth: Int, screenHeight: Int) {
    var mapModel = MapModel(screenWidth, screenHeight)
    lateinit var middle: Bitmap
    lateinit var background: Bitmap
    lateinit var front: Bitmap

    fun drawMapBack(canvas: Canvas, speed: Double, bmp: Bitmap) {
        mapModel.setNewMapBackXCoords(speed)
        canvas.drawBitmap(bmp, mapModel.mapBack.toFloat(), 0f, null)
        if (mapModel.needToRepeatPartBack()) {
            canvas.drawBitmap(bmp, (mapModel.mapBack + mapModel.newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapBackMars(canvas: Canvas, speed: Double, bmp: Bitmap) {
        mapModel.setNewMapBackXCoords(speed)
        canvas.drawBitmap(bmp, -700f, 0f, null)
        if (mapModel.needToRepeatPartBack()) {
            canvas.drawBitmap(bmp, (mapModel.mapBack + mapModel.newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapFront(canvas: Canvas, speed: Double, bmp: Bitmap) {
        mapModel.setNewMapFrontXCoords(speed)
        canvas.drawBitmap(bmp, mapModel.mapFront.toFloat(), 0f, null)
        if (mapModel.needToRepeatPartFront()) {
            canvas.drawBitmap(bmp, (mapModel.mapFront + mapModel.newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapMiddle(canvas: Canvas, speed: Double, bmp: Bitmap) {
        mapModel.setNewMapMiddleXCoords(speed)
        canvas.drawBitmap(bmp, mapModel.mapMiddle.toFloat(), 0f, null)
        if (mapModel.needToRepeatPartMiddle()) {
            canvas.drawBitmap(bmp, (mapModel.mapMiddle + mapModel.newWidth).toFloat(), 0f, null)
        }
    }

    abstract fun drawMap(canvas: Canvas, speedBack: Double, speedMiddle: Double, speedFront: Double)
}