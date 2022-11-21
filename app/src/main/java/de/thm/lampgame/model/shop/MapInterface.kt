package de.thm.lampgame.model.shop

import de.thm.lampgame.controller.maps.MapController

interface MapInterface {
    var active: Boolean
    var name: String
    var buyStatus: Boolean
    val price: String
    val icon: Int
    fun createMap(context: Any,screenHeight: Int, screenWidth: Int): MapController
}