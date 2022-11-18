package de.thm.lampgame.model

import de.thm.lampgame.controller.Skins.SkinController

interface SkinInterface {
    var active: Boolean
    var name: String
    var buyStatus: Boolean
    val price: String
    val icon: Int
    fun createSkin(context: Any) : SkinController
}