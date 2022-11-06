package de.thm.lampgame.controller.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.controller.terrain.BitmapGround
import de.thm.lampgame.controller.terrain.BitmapTerrain

class CemeteryLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {
    companion object {
         var name = "CemeteryLandscapeMap"
         var active = false
    }



    init {
        BitmapGround.texture = R.drawable.tilesground
        BitmapTerrain.texture = R.drawable.cemetery_platform
        background = BitmapFactory.decodeResource(context.resources, R.drawable.moon)
        mitte = BitmapFactory.decodeResource(context.resources, R.drawable.background2)
        vorne = BitmapFactory.decodeResource(context.resources, R.drawable.background1)
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





