package de.thm.lampgame.controller.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.MapModel

class MountainLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapModel(screenWidth, screenHeight) {
    companion object {
        var name = "MountainLandscapeMap"
        var active = false
    }

    var mitte: Bitmap
    var background: Bitmap
    var vorne: Bitmap


    init {
        background = BitmapFactory.decodeResource(context.resources, R.drawable.berghinten)
        mitte = BitmapFactory.decodeResource(context.resources, R.drawable.bergemitte)
        vorne = BitmapFactory.decodeResource(context.resources, R.drawable.bergevorne)
        height = background.getHeight().toFloat()
        width = background.getWidth().toFloat()
        ratio = width / height
        newWidth = (ratio * screenHeight).toInt()
        background = Bitmap.createScaledBitmap(background, newWidth, newHeigt, false)
        mitte = Bitmap.createScaledBitmap(mitte, newWidth, newHeigt, false)
        vorne = Bitmap.createScaledBitmap(vorne, newWidth, newHeigt, false)
    }

    fun drawMapHinten(canvas: Canvas, speed: Double) {
        setNewMapHintenXCoords(speed)
        canvas.drawBitmap(background, mapHinten.toFloat(), 0f, null)
        if (needToRepeatPartHinten()) {
            canvas.drawBitmap(background, (mapHinten + newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapMitte(canvas: Canvas, speed: Double) {
        setNewMapMitteXCoords(speed)
        canvas.drawBitmap(mitte, mapMitte.toFloat(), 0f, null)
        if (needToRepeatPartMitte()) {
            canvas.drawBitmap(mitte, (mapMitte + newWidth).toFloat(), 0f, null)
        }
    }

    fun drawMapVorne(canvas: Canvas, speed: Double) {
        setNewMapVorneXCoords(speed)
        canvas.drawBitmap(vorne, mapVorne.toFloat(), 0f, null)
        if (needToRepeatPartVorne()) {
            canvas.drawBitmap(vorne, (mapVorne + newWidth).toFloat(), 0f, null)
        }
    }


    fun drawMap(canvas: Canvas, speedHinten: Double, speedMitte: Double, speedVorne: Double) {
        drawMapHinten(canvas, speedHinten)
        drawMapMitte(canvas, speedMitte)
        drawMapVorne(canvas, speedVorne)
    }
}






