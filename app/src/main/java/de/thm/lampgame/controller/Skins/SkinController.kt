package de.thm.lampgame.controller.Skins

import android.graphics.Bitmap

abstract class SkinController() {
    var char = arrayOfNulls<Bitmap>(6)

    fun getSkin(): Array<Bitmap?> {
        return char
    }
}