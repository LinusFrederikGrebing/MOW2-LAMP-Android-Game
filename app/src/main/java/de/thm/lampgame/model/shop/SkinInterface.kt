package de.thm.lampgame.model.shop

import de.thm.lampgame.controller.skins.SkinController

interface SkinInterface {
    var active: Boolean
    var name: String
    var buyStatus: Boolean
    val price: String
    val icon: Int
    fun createSkin(context: Any) : SkinController
}