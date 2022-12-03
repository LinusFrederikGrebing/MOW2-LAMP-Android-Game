package de.thm.lampgame.controller.skins

import android.content.Context
import android.graphics.BitmapFactory
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.SkinInterface

class PurpleLampSkin(context: Context) : SkinController() {

    companion object : SkinInterface {
        // all information about the associated skin, such as the name or status, is stored in the itemInfo attribute
        override var itemInfo = Database.skinPurpleLamp
        // the method is used to create an object from the given skin. It serves as an aid to create the skin.
        override fun createSkin(context: Any): PurpleLampSkin {
            return PurpleLampSkin(context as Context)
        }
    }

    // initializes the bitmaps of the associated skin
    init {
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.purple_lamp_legs_left)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.purple_lamp_inside)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.purple_lamp_legs_right)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.purple_lamp_outside)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.purple_lamp_jump)
    }
}