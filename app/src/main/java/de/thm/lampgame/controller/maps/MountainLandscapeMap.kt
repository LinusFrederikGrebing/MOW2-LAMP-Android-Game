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

class MountainLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {
    companion object : MapInterface {
        override var active = false
        override var name = "MountainLandscapeMap"
        override var buyStatus = true
        override val price = "0"
        override val icon = R.drawable.bergeicon
        override fun createMap(context: Any, screenHeight: Int, screenWidth: Int): MountainLandscapeMap {
            return MountainLandscapeMap(context as Context,screenHeight,screenWidth)
        }
    }

    init {
        BitmapGround.texture = R.drawable.bodengras
        BitmapWater.texture = R.drawable.wasser
        BitmapTube.texture = R.drawable.bottomtube
        BitmapTerrain.texture = R.drawable.plattform2
        background = BitmapFactory.decodeResource(context.resources, R.drawable.berghinten)
        mitte = BitmapFactory.decodeResource(context.resources, R.drawable.bergemitte)
        vorne = BitmapFactory.decodeResource(context.resources, R.drawable.bergevorne)
        height = background.height.toFloat()
        width = background.width.toFloat()
        ratio = width / height
        newWidth = (ratio * screenHeight).toInt()
        background = Bitmap.createScaledBitmap(background, newWidth, screenHeight, false)
        mitte = Bitmap.createScaledBitmap(mitte, newWidth, screenHeight, false)
        vorne = Bitmap.createScaledBitmap(vorne, newWidth, screenHeight, false)
    }

    override fun drawMap(canvas: Canvas, speedHinten: Double, speedMitte: Double, speedVorne: Double) {
        drawMapHinten(canvas, speedHinten, background)
        drawMapMitte(canvas, speedMitte, mitte)
        drawMapVorne(canvas, speedVorne, vorne)
    }
}






