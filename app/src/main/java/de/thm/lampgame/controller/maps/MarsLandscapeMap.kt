package de.thm.lampgame.controller.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapGround
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapTerrain

class MarsLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {
    companion object {
         var name = "MarsLandscapeMap"
         var active = false
         var buyStatus = false
    }



    init {
        BitmapGround.texture = R.drawable.groundmars
        BitmapTerrain.texture = R.drawable.platformmars
        background = BitmapFactory.decodeResource(context.resources, R.drawable.letzerbackgroundmars)
        mitte = BitmapFactory.decodeResource(context.resources, R.drawable.mittebackgroundmars)
        vorne = BitmapFactory.decodeResource(context.resources, R.drawable.vornebackgroundmars)
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






