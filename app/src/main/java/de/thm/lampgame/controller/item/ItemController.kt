package de.thm.lampgame.controller.item

import de.thm.lampgame.model.item.ItemModel

abstract class ItemController {
    open lateinit var itemModel : ItemModel
    abstract fun draw(canvas: Any, velocity: Int)
}