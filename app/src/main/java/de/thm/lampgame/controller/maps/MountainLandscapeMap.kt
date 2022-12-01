package de.thm.lampgame.controller.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.controller.obstacles.*
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MapInterface

class MountainLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {
    companion object : MapInterface {
        override var itemInfo = Database.mapMountainLandscape
        override fun createMap(context: Any, screenHeight: Int, screenWidth: Int): MountainLandscapeMap {
            return MountainLandscapeMap(context as Context,screenHeight,screenWidth)
        }
    }

    init {
        BitmapGround.texture = R.drawable.bodengras
        BitmapWater.texture = R.drawable.wasser
        BitmapTube.texture = R.drawable.bottomtube
        BitmapTerrain.texture = R.drawable.plattform2
        BitmapSaw.texture = R.drawable.saw_water
        BitmapBouncingSaw.texture = R.drawable.bouncingsaw_water
        background = BitmapFactory.decodeResource(context.resources, R.drawable.berghinten)
        mitte = BitmapFactory.decodeResource(context.resources, R.drawable.bergemitte)
        vorne = BitmapFactory.decodeResource(context.resources, R.drawable.bergevorne)
        mapController.height = background.height.toFloat()
        mapController.width = background.width.toFloat()
        mapController.ratio = mapController.width / mapController.height
        mapController.newWidth = (mapController.ratio * screenHeight).toInt()
        background = Bitmap.createScaledBitmap(background, mapController.newWidth, screenHeight, false)
        mitte = Bitmap.createScaledBitmap(mitte, mapController.newWidth, screenHeight, false)
        vorne = Bitmap.createScaledBitmap(vorne, mapController.newWidth, screenHeight, false)
    }

    override fun drawMap(canvas: Canvas, speedHinten: Double, speedMitte: Double, speedVorne: Double) {
        drawMapHinten(canvas, speedHinten, background)
        drawMapMitte(canvas, speedMitte, mitte)
        drawMapVorne(canvas, speedVorne, vorne)
    }
}






