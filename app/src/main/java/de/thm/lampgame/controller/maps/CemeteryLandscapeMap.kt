package de.thm.lampgame.controller.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.MapInterface
import de.thm.lampgame.controller.obstaclesBitmaps.BitmapGround
import de.thm.lampgame.controller.obstaclesBitmaps.BitmapTerrain
import de.thm.lampgame.controller.obstaclesBitmaps.BitmapTube
import de.thm.lampgame.controller.obstaclesBitmaps.BitmapWater
import de.thm.lampgame.controller.obstaclesBitmaps.BitmapSaw

class CemeteryLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {
    companion object : MapInterface {
        override var active = false
        override var name = "CemeteryLandscapeMap"
        override var buyStatus = false
        override val price = "50"
        override val icon = R.drawable.cemetery
        override fun createMap(context: Any, screenHeight: Int, screenWidth: Int): CemeteryLandscapeMap {
            return CemeteryLandscapeMap(context as Context,screenHeight,screenWidth)
        }
    }

    init {
        BitmapGround.texture = R.drawable.tilesground
        BitmapTerrain.texture = R.drawable.cemetery_platform
        BitmapWater.texture = R.drawable.waternew
        BitmapTube.texture = R.drawable.obstaclestonewall
        BitmapSaw.texture = R.drawable.saw_water
        BitmapBouncingSaw.texture = R.drawable.bouncingsaw_water
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






