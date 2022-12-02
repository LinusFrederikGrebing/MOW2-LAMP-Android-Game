package de.thm.lampgame.model.shop

import de.thm.lampgame.controller.maps.MapController

interface MapInterface {
    var itemInfo: ShopItemInfo
    fun createMap(context: Any, screenHeight: Int, screenWidth: Int): MapController
}