package de.thm.lampgame

sealed class DataItem{
    data class Unlocked(val text1: Int, val text: String) : DataItem()
    data class Active(val text1: Int, val text: String) : DataItem()
    data class Locked(val text1: Int, val text: String, val price: String) : DataItem()

}
