package de.thm.lampgame.controller.Skins

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import de.thm.lampgame.R
import de.thm.lampgame.model.SkinInterface

class LampSkin(context: Context) : SkinController(){

    companion object : SkinInterface {
        override var name = "LampSkin"
        override var active = true
        override var buyStatus = true
        override val price = "0"
        override val icon = R.drawable.skin_standard
        override fun createSkin(context: Any): LampSkin {
            return LampSkin(context as Context)
        }
    }
    init {
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_left)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.innen)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_right)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.aussen)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_sneek)
        char[5] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_jump_fire)
    }
}