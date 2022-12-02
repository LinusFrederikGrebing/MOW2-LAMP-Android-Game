package de.thm.lampgame.model.shop

import de.thm.lampgame.controller.skins.SkinController

interface SkinInterface {
    var itemInfo: ShopItemInfo
    fun createSkin(context: Any): SkinController
}