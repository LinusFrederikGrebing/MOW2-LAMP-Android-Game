package de.thm.lampgame.controller.Skins

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import de.thm.lampgame.R

class BlueLampSkin(context: Context) {
    var char = arrayOfNulls<Bitmap>(6)

    companion object{
        var name = "BlueLampSkin"
        var active = false
        var buyStatus = false
    }
    init {
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_leftskin2)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.innenskin2)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_rightskin2)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.aussenskin2)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_sneek)
        char[5] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_jump_fireskin2)
    }

    fun getSkin(): Array<Bitmap?> {
        return char
    }
}