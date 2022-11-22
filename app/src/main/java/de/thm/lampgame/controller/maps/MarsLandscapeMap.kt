package de.thm.lampgame.controller.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.MapInterface
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapGround
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapTerrain
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapTube
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapWater
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapSaw
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapBouncingSaw

class MarsLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {

    companion object : MapInterface {
        override var active = true
        override var name = "MarsLandscapeMap"
        override var buyStatus = true
        override val price = "100"
        override val icon = R.drawable.map_mars
        override fun createMap(context: Any, screenHeight: Int, screenWidth: Int): MarsLandscapeMap {
            return MarsLandscapeMap(context as Context,screenHeight,screenWidth)
        }
    }



    init {
        BitmapSaw.texture = R.drawable.saw_water
        BitmapGround.texture = R.drawable.groundmars
        BitmapTerrain.texture = R.drawable.platformmars
        BitmapWater.texture = R.drawable.waternew
        BitmapTube.texture = R.drawable.obstaclestonewall
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
        drawMapHintenMars(canvas, speedHinten, background)
        drawMapMitte(canvas, speedMitte, mitte)
        drawMapVorne(canvas, speedVorne, vorne)
    }
}






