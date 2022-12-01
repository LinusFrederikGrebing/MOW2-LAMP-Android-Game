package de.thm.lampgame.controller.maps

import android.graphics.Bitmap
import android.graphics.Canvas
import de.thm.lampgame.model.map.MapModel

abstract class MapController(screenWidth: Int, screenHeight: Int)  {
    var mapController = MapModel(screenWidth, screenHeight)
    lateinit var mitte: Bitmap
    lateinit var background: Bitmap
    lateinit var vorne: Bitmap

    fun drawMapHinten(canvas: Canvas, speed: Double, bmp: Bitmap) {
        mapController.setNewMapHintenXCoords(speed)
        canvas.drawBitmap(bmp, mapController.mapHinten.toFloat(), 0f, null)
        if (mapController.needToRepeatPartHinten()) {
            canvas.drawBitmap(bmp, (mapController.mapHinten + mapController.newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapHintenMars(canvas: Canvas, speed: Double, bmp: Bitmap) {
        mapController.setNewMapHintenXCoords(speed)
        canvas.drawBitmap(bmp, -700f, 0f, null)
        if (mapController.needToRepeatPartHinten()) {
            canvas.drawBitmap(bmp, (mapController.mapHinten + mapController.newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapVorne(canvas: Canvas, speed: Double, bmp: Bitmap) {
        mapController.setNewMapVorneXCoords(speed)
        canvas.drawBitmap(bmp, mapController.mapVorne.toFloat(), 0f, null)
        if (mapController.needToRepeatPartVorne()) {
            canvas.drawBitmap(bmp, (mapController.mapVorne + mapController.newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapMitte(canvas: Canvas, speed: Double, bmp: Bitmap) {
        mapController.setNewMapMitteXCoords(speed)
        canvas.drawBitmap(bmp, mapController.mapMitte.toFloat(), 0f, null)
        if (mapController.needToRepeatPartMitte()) {
            canvas.drawBitmap(bmp, (mapController.mapMitte + mapController.newWidth).toFloat(), 0f, null)
        }
    }

    abstract fun drawMap(canvas: Canvas, speedHinten: Double, speedMitte: Double, speedVorne: Double)
}