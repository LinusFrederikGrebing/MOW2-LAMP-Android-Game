package de.thm.lampgame.model.map

abstract class MapModel(val screenWidth: Int, val screenHeight: Int) {
    var newWidth = 0
    var mapHinten = 0.0
    var mapMitte = 0.0
    var mapVorne = 0.0
    var width = 0.0F
    var height = 0.0F
    var ratio = 0.0F


    fun setNewMapHintenXCoords(speed: Double) {
        mapHinten -= speed
        if (mapHinten < -newWidth) {
            mapHinten = 0.0
        }
    }

    fun setNewMapMitteXCoords(speed: Double) {
        mapMitte -= speed
        if (mapMitte < -newWidth) {
            mapMitte = 0.0
        }
    }

    fun setNewMapVorneXCoords(speed: Double) {
        mapVorne -= speed
        if (mapVorne < -newWidth) {
            mapVorne = 0.0
        }
    }

    fun needToRepeatPartHinten(): Boolean {
        return mapHinten < screenWidth - newWidth
    }

    fun needToRepeatPartMitte(): Boolean {
        return mapMitte < screenWidth - newWidth
    }

    fun needToRepeatPartVorne(): Boolean {
        return mapVorne < screenWidth - newWidth
    }
}