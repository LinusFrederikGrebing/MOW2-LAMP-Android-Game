package de.thm.lampgame.model

interface MusicInterface {
    var active: Boolean
    var name: String
    var buyStatus: Boolean
    val price: String
    val icon: Int
    val song: Int
}