package de.thm.lampgame.controller.item

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import de.thm.lampgame.R
import de.thm.lampgame.model.item.BonusJumpModel
import de.thm.lampgame.model.item.ItemModel

class BonusJump(context: Context, screenHeight: Int, screenWidth: Int, x: Double, y: Double) :
    ItemController() {
    // creates the associated model
    override var itemModel: ItemModel = BonusJumpModel(
        screenHeight,
        screenWidth,
        (0.1 * screenHeight).toInt(),
        (0.05 * screenWidth).toInt(),
        x,
        y
    )

    // initializes and resize the respective bitmap based on the data from the associated model
    init {
        itemModel.unsizedBmp =
            BitmapFactory.decodeResource(context.resources, R.drawable.bonusjump_highlight)
        itemModel.bmp = Bitmap.createScaledBitmap(
            itemModel.unsizedBmp as Bitmap,
            itemModel.width,
            itemModel.height,
            true
        )
    }

    // If the respective item has not been picked up yet,
    // then change the x coordinate by the given speed value and draw the item
    // -> get the required data from the associated model
    override fun draw(canvas: Any, velocity: Double) {
        if (!itemModel.isPickedUp) {
            itemModel.x -= velocity
            (canvas as Canvas).drawBitmap(
                itemModel.bmp as Bitmap,
                itemModel.x.toFloat(),
                itemModel.y.toFloat(),
                null
            )
        }
    }
}
