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
        // all information about the associated map, such as the name or status, is stored in the itemInfo attribute
        override var itemInfo = Database.mapMountainLandscape
        // the method is used to create an object from the given map. It serves as an aid to create the map.
        override fun createMap(
            context: Any,
            screenHeight: Int,
            screenWidth: Int
        ): MountainLandscapeMap {
            return MountainLandscapeMap(context as Context, screenHeight, screenWidth)
        }
    }

    init {
        // Depending on which map is active, the design of some obstacles should also change.
        // Therefore, the textures of the obstacles belonging to the map are initialized with the map
        BitmapGround.texture = R.drawable.mountain_landscape_ground
        BitmapTerrain.texture = R.drawable.mountain_landscape_platform
        BitmapWater.texture = R.drawable.mountain_landscape_water
        BitmapTube.texture = R.drawable.mountain_landscape_wall
        BitmapSaw.texture = R.drawable.water_projectile
        BitmapBouncingSaw.texture = R.drawable.water_bouncing_projectile
        // initializes all layers of the map with their bitmaps
        background = BitmapFactory.decodeResource(context.resources, R.drawable.mountain_landscape_background)
        middle = BitmapFactory.decodeResource(context.resources, R.drawable.mountain_landscape_middle)
        front = BitmapFactory.decodeResource(context.resources, R.drawable.mountain_landscape_front)
        // save the values calculated from the sizes of the bitmap in the associated model
        mapModel.height = background.height.toFloat()
        mapModel.width = background.width.toFloat()
        mapModel.ratio = mapModel.width / mapModel.height
        mapModel.newWidth = (mapModel.ratio * screenHeight).toInt()
        // use the calculated values to resize the bitmaps
        background = Bitmap.createScaledBitmap(background, mapModel.newWidth, screenHeight, false)
        middle = Bitmap.createScaledBitmap(middle, mapModel.newWidth, screenHeight, false)
        front = Bitmap.createScaledBitmap(front, mapModel.newWidth, screenHeight, false)
    }


    // each map consists of three levels, each level can have a different speed,
    // with the last one usually showing the lowest speed
    override fun drawMap(
        canvas: Canvas,
        velocity: Double,
    ) {
        // draw each layer with the corresponding speed
        drawMapBack(canvas, velocity*0.03, background)
        drawMapMiddle(canvas, velocity*0.06, middle)
        drawMapFront(canvas, velocity*0.09, front)
      }
}






