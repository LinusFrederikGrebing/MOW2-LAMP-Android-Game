package de.thm.lampgame.controller.skins

import android.content.Context
import android.graphics.BitmapFactory
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.SkinInterface

class BlueLampSkin(context: Context) : SkinController() {
    companion object : SkinInterface {
        override var itemInfo = Database.skinBlueLamp
        override fun createSkin(context: Any): BlueLampSkin {
            return BlueLampSkin(context as Context)
        }
    }

    init {
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.blue_lamp_legs_left)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.blue_lamp_inside)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.blue_lamp_legs_right)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.blue_lamp_outside)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.blue_lamp_jump)
    }
}