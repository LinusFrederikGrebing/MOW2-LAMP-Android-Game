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

class MarsLandscapeMap(context: Context, screenHeight: Int, screenWidth: Int) :
    MapController(screenWidth, screenHeight) {

    companion object : MapInterface {
        override var itemInfo = Database.mapMarsLandscape
        override fun createMap(context: Any, screenHeight: Int, screenWidth: Int): MarsLandscapeMap {
            return MarsLandscapeMap(context as Context,screenHeight,screenWidth)
        }
    }

    init {
        BitmapGround.texture = R.drawable.mars_ground
        BitmapTerrain.texture = R.drawable.mars_platform
        BitmapWater.texture = R.drawable.water_ground
        BitmapTube.texture = R.drawable.mars_wall
        BitmapSaw.texture = R.drawable.water_projectile
        BitmapBouncingSaw.texture = R.drawable.water_bouncing_projectile
        background = BitmapFactory.decodeResource(context.resources, R.drawable.mars_background)
        middle = BitmapFactory.decodeResource(context.resources, R.drawable.mars_middle)
        front = BitmapFactory.decodeResource(context.resources, R.drawable.mars_front)
        height = background.height.toFloat()
        width = background.width.toFloat()
        ratio = width / height
        newWidth = (ratio * screenHeight).toInt()
        background = Bitmap.createScaledBitmap(background, newWidth, screenHeight, false)
        middle = Bitmap.createScaledBitmap(middle, newWidth, screenHeight, false)
        front = Bitmap.createScaledBitmap(front, newWidth, screenHeight, false)
    }

    override fun drawMap(canvas: Canvas, speedBack: Double, speedMiddle: Double, speedFront: Double) {
        drawMapBackMars(canvas, speedBack, background)
        drawMapMiddle(canvas, speedMiddle, middle)
        drawMapFront(canvas, speedFront, front)
    }
}






