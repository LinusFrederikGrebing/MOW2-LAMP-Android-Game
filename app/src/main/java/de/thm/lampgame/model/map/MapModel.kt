package de.thm.lampgame.model.map

abstract class MapModel(val screenWidth: Int, val screenHeight: Int) {
    var newWidth = 0
    var mapBack = 0.0
    var mapMiddle = 0.0
    var mapFront = 0.0
    var width = 0.0F
    var height = 0.0F
    var ratio = 0.0F

    fun setNewMapBackXCoords(speed: Double) {
        mapBack -= speed
        if (mapBack <- newWidth) {
            mapBack = 0.0
        }
    }

    fun setNewMapMiddleXCoords(speed: Double) {
        mapMiddle -= speed
        if (mapMiddle <- newWidth) {
            mapMiddle = 0.0
        }
    }

    fun setNewMapFrontXCoords(speed: Double) {
        mapFront -= speed
        if (mapFront <- newWidth) {
            mapFront = 0.0
        }
    }

    fun needToRepeatPartBack(): Boolean {
        return mapBack < screenWidth - newWidth
    }

    fun needToRepeatPartMiddle(): Boolean {
        return mapMiddle < screenWidth - newWidth
    }

    fun needToRepeatPartFront(): Boolean {
        return mapFront < screenWidth - newWidth
    }
}