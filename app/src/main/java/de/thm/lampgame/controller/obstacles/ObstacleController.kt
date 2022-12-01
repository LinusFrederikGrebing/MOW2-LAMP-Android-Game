package de.thm.lampgame.controller.obstacles

import de.thm.lampgame.model.obstacles.ObstacleModel

abstract class ObstacleController {
    open lateinit var obstacleModel : ObstacleModel
    abstract fun draw(canvas: Any, velocityX: Int, velocityY: Int)
}