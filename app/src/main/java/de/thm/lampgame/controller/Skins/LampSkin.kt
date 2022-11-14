package de.thm.lampgame.controller.Skins

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import de.thm.lampgame.R

class LampSkin(context: Context) {
    var char = arrayOfNulls<Bitmap>(6)

    companion object {
        var name = "LampSkin"
        var active = true
    }
    init {
        char[0] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_left)
        char[1] = BitmapFactory.decodeResource(context.resources, R.drawable.innen)
        char[2] = BitmapFactory.decodeResource(context.resources, R.drawable.legs_right)
        char[3] = BitmapFactory.decodeResource(context.resources, R.drawable.aussen)
        char[4] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_sneek)
        char[5] = BitmapFactory.decodeResource(context.resources, R.drawable.shared_char_jump_fire)
    }

    fun getSkin(): Array<Bitmap?> {
        return char
    }
}