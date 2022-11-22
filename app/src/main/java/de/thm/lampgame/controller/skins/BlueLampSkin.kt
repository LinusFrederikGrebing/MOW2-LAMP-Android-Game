package de.thm.lampgame.controller.skins

import android.content.Context
import android.graphics.BitmapFactory
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.SkinInterface

class BlueLampSkin(context: Context) : SkinController() {

    companion object : SkinInterface {
        override var name = "BlueLampSkin"
        override var active = false
        override var buyStatus = false
        override val price = "30"
        override val icon = R.drawable.skin_blau
        override fun createSkin(context: Any): BlueLampSkin {
            return BlueLampSkin(context as Context)
        }
    }
    init {
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_leftskin2)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.innenskin2)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_rightskin2)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.aussenskin2)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_sneek)
        char[5] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_jump_fireskin2)
    }
}