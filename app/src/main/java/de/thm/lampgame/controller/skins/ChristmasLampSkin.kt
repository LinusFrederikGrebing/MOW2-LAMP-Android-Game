package de.thm.lampgame.controller.skins

import android.content.Context
import android.graphics.BitmapFactory
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.SkinInterface

class ChristmasLampSkin(context: Context) : SkinController() {

    companion object : SkinInterface {
        override var name = "ChristmasLampSkin"
        override var active = false
        override var buyStatus = false
        override val price = "30"
        override val icon = R.drawable.skin_christmas
        override fun createSkin(context: Any): ChristmasLampSkin {
            return ChristmasLampSkin(context as Context)
        }
    }
    init {
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_leftskin4)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.innenskin4)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_rightskin4)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.aussenskin4)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_jump_fireskin4)
    }
}