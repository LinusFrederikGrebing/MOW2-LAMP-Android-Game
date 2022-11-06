package de.thm.lampgame.controller.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.controller.terrain.BitmapGround
import de.thm.lampgame.controller.terrain.BitmapTerrain

class MountainLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {
    companion object {
        var name = "MountainLandscapeMap"
        var active = true
    }

    init {
        BitmapGround.texture = R.drawable.bodengras
        BitmapTerrain.texture = R.drawable.plattform2
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

    override fun drawMap(canvas: Canvas, speedHinten: Double, speedMitte: Double, speedVorne: Double) {
        drawMapHinten(canvas, speedHinten, background)
        drawMapMitte(canvas, speedMitte, mitte)
        drawMapVorne(canvas, speedVorne, vorne)
    }
}






