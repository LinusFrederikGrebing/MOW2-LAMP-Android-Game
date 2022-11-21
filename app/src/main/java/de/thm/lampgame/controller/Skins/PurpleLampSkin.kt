package de.thm.lampgame.controller.Skins

import android.content.Context
import android.graphics.BitmapFactory
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.SkinInterface

class PurpleLampSkin(context: Context) : SkinController(){

    companion object : SkinInterface {
        override var name = "PurpleLampSkin"
        override var active = false
        override var buyStatus = false
        override val price = "100"
        override val icon = R.drawable.skin_lila
        override fun createSkin(context: Any): PurpleLampSkin {
            return PurpleLampSkin(context as Context)
        }
    }
    init {
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_left_skin3)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.innen_skin3)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_right_skin3)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.aussen_skin3)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_sneek)
        char[5] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_jump_fireskin3)
    }
}