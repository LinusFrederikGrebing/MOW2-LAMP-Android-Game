package de.thm.lampgame.model.shop


sealed class DataItem {
    data class Unlocked(val image: Int, val text: String) : DataItem()
    data class Active(val image: Int, val text: String) : DataItem()
    data class Locked(
        val image: Int,
        val text: String,
        val price: String
    ) : DataItem()

}
