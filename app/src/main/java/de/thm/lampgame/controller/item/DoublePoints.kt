package de.thm.lampgame.controller.item

import android.content.Context
import android.graphics.*
import de.thm.lampgame.R
import de.thm.lampgame.model.item.DoublePointsModel
import de.thm.lampgame.model.item.ItemModel

class DoublePoints(context: Context, screenHeight: Int, screenWidth: Int, x: Int, y: Int ) : ItemController(){
    override var itemModel: ItemModel = DoublePointsModel(screenHeight, screenWidth, (0.1*screenHeight).toInt(),(0.05*screenWidth).toInt(), x,y)
    init {
        itemModel.unsizedBmp = BitmapFactory.decodeResource(context.resources, R.drawable.doublepoints)
        itemModel.bmp = Bitmap.createScaledBitmap(itemModel.unsizedBmp as Bitmap, itemModel.width, itemModel.height, true) }

    override fun draw(canvas: Any, velocity: Int) {
        if (!itemModel.isPickedUp) {
            itemModel.x -= velocity
            (canvas as Canvas).drawBitmap(itemModel.bmp as Bitmap, itemModel.x.toFloat(), itemModel.y.toFloat(), null)
        }
    }
}
