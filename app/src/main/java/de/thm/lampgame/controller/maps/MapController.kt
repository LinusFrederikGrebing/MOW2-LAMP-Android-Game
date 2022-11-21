package de.thm.lampgame.controller.maps

import android.graphics.Bitmap
import android.graphics.Canvas
import de.thm.lampgame.model.map.MapModel

abstract class MapController(screenWidth: Int, screenHeight: Int) : MapModel(screenWidth, screenHeight) {
    lateinit var mitte: Bitmap
    lateinit var background: Bitmap
    lateinit var vorne: Bitmap

    fun drawMapHinten(canvas: Canvas, speed: Double, bmp: Bitmap) {
        setNewMapHintenXCoords(speed)
        canvas.drawBitmap(bmp, mapHinten.toFloat(), 0f, null)
        if (needToRepeatPartHinten()) {
            canvas.drawBitmap(bmp, (mapHinten + newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapHintenMars(canvas: Canvas, speed: Double, bmp: Bitmap) {
        setNewMapHintenXCoords(speed)
        canvas.drawBitmap(bmp, -700f, 0f, null)
        if (needToRepeatPartHinten()) {
            canvas.drawBitmap(bmp, (mapHinten + newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapVorne(canvas: Canvas, speed: Double, bmp: Bitmap) {
        setNewMapVorneXCoords(speed)
        canvas.drawBitmap(bmp, mapVorne.toFloat(), 0f, null)
        if (needToRepeatPartVorne()) {
            canvas.drawBitmap(bmp, (mapVorne + newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapMitte(canvas: Canvas, speed: Double, bmp: Bitmap) {
        setNewMapMitteXCoords(speed)
        canvas.drawBitmap(bmp, mapMitte.toFloat(), 0f, null)
        if (needToRepeatPartMitte()) {
            canvas.drawBitmap(bmp, (mapMitte + newWidth).toFloat(), 0f, null)
        }
    }

    abstract fun drawMap(canvas: Canvas, speedHinten: Double, speedMitte: Double, speedVorne: Double)
}