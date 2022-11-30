package de.thm.lampgame.controller.maps

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MapInterface
import de.thm.lampgame.view.obstacles.BitmapGround
import de.thm.lampgame.view.obstacles.BitmapTerrain
import de.thm.lampgame.view.obstacles.BitmapTube
import de.thm.lampgame.view.obstacles.BitmapWater
import de.thm.lampgame.view.obstacles.BitmapSaw
import de.thm.lampgame.view.obstacles.BitmapBouncingSaw

class ChristmasLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {
    companion object : MapInterface {
        override var itemInfo = Database.mapChristmasLandscape
        override fun createMap(context: Any, screenHeight: Int, screenWidth: Int): ChristmasLandscapeMap {
            return ChristmasLandscapeMap(context as Context,screenHeight,screenWidth)
        }
    }

    init {
        BitmapGround.texture = R.drawable.bodengrasschnee
        BitmapTerrain.texture = R.drawable.cemetery_platform
        BitmapWater.texture = R.drawable.waternew
        BitmapTube.texture = R.drawable.obstaclestonewall
        BitmapSaw.texture = R.drawable.saw_water
        BitmapBouncingSaw.texture = R.drawable.bouncingsaw_water
        background = BitmapFactory.decodeResource(context.resources, R.drawable.hinten_christmas)
        mitte = BitmapFactory.decodeResource(context.resources, R.drawable.mittechristmas)
        vorne = BitmapFactory.decodeResource(context.resources, R.drawable.vorne_christmas)
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





