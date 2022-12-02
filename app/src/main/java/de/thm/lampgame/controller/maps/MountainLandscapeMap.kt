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
        override fun createMap(
            context: Any,
            screenHeight: Int,
            screenWidth: Int
        ): MountainLandscapeMap {
            return MountainLandscapeMap(context as Context, screenHeight, screenWidth)
        }
    }

    init {
        BitmapGround.texture = R.drawable.mountain_landscape_ground
        BitmapTerrain.texture = R.drawable.mountain_landscape_platform
        BitmapWater.texture = R.drawable.mountain_landscape_water
        BitmapTube.texture = R.drawable.mountain_landscape_wall
        BitmapSaw.texture = R.drawable.water_projectile
        BitmapBouncingSaw.texture = R.drawable.water_bouncing_projectile
        background = BitmapFactory.decodeResource(
            context.resources,
            R.drawable.mountain_landscape_background
        )
        middle =
            BitmapFactory.decodeResource(context.resources, R.drawable.mountain_landscape_middle)
        front = BitmapFactory.decodeResource(context.resources, R.drawable.mountain_landscape_front)
        mapModel.height = background.height.toFloat()
        mapModel.width = background.width.toFloat()
        mapModel.ratio = mapModel.width / mapModel.height
        mapModel.newWidth = (mapModel.ratio * screenHeight).toInt()
        background = Bitmap.createScaledBitmap(background, mapModel.newWidth, screenHeight, false)
        middle = Bitmap.createScaledBitmap(middle, mapModel.newWidth, screenHeight, false)
        front = Bitmap.createScaledBitmap(front, mapModel.newWidth, screenHeight, false)
    }

    override fun drawMap(
        canvas: Canvas,
        speedBack: Double,
        speedMiddle: Double,
        speedFront: Double
    ) {
        drawMapBack(canvas, speedBack, background)
        drawMapMiddle(canvas, speedMiddle, middle)
        drawMapFront(canvas, speedFront, front)
    }
}






