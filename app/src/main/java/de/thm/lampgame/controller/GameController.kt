package de.thm.lampgame.controller

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import de.thm.lampgame.R
import de.thm.lampgame.controller.activities.GameOverActivity
import de.thm.lampgame.controller.activities.PauseActivity
import de.thm.lampgame.controller.helper.UiTextHelper
import de.thm.lampgame.controller.item.ActiveItemController
import de.thm.lampgame.controller.maps.MapController
import de.thm.lampgame.controller.tileset.TilesetQueue
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.controller.player.DrawTorchCount
import de.thm.lampgame.controller.player.Player
import kotlin.math.roundToInt

class GameController(val context: Context) {
    private var screenWidth = 0
    private var screenHeight = 0
    private val paint = Paint()
    private lateinit var map: MapController
    var gameStatus = true

    // the game should also be able to get the game-over status from other classes, such as the TilesetQueueModel
    companion object {
        var gameover = false
    }

    init {
        // if the game starts or is restarted, the game-over status must be set to false
        gameover = false
        gameStatus = true


        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels

        paint.color = Color.BLACK
        paint.textSize = screenHeight * 0.07.toFloat()

        // the active status is used to check which map needs to b loaded
        Database.listOfMaps.forEach {
            if (it.itemInfo.active) map = it.createMap(context, screenHeight, screenWidth)
        }
    }
    private var velocity = screenWidth*0.005
    private var tilesetQueue = TilesetQueue(context, screenWidth, screenHeight)
    private var player = Player(context, screenHeight, screenWidth)
    private var pauseButton = PauseButton(context, screenWidth, screenHeight)
    private var drawTorch = DrawTorchCount(context, screenWidth, screenHeight)
    private var activeItemController = ActiveItemController(context, screenWidth, screenHeight)

    fun game(canvas: Canvas) {
        // redraw the game elements only when the game status is active
        if (gameStatus) {
            // every iteration the game speed is increased
            velocity += 0.0025

            // draw the background.
            map.drawMap(canvas, velocity)

            // draw tileset with obstacles
            tilesetQueue.drawTilesetsAndCheckCollisions(canvas, velocity, player)

            // draw canvas-elements
            val text = UiTextHelper.StringResource(R.string.pointsValuesString, "${player.playerModel.points.roundToInt()}")
            canvas.drawText(text.asString(context), (screenWidth * 0.01).toFloat(), (screenHeight * 0.075).toFloat(), paint)

            drawTorch.draw(canvas, player.playerModel.torchesPerRound)
            pauseButton.draw(canvas)

            // draw the player and if an item is picked up, draw the item
            activeItemController.checkItemDurationAndSetItemEffect(canvas, paint, player)
            player.drawPlayer(canvas, velocity, tilesetQueue.tilesetQueueModel.collision)

            // check if the game is lost yet
            if (gameover) {
                gameOver()
            }

        }
    }
        // Start GameOver Activity
        fun gameOver() {
            gameStatus = false
            val intent = Intent(context, GameOverActivity::class.java)
            intent.putExtra("POINTS", player.playerModel.points.roundToInt())
            context.startActivity(intent)
            (context as Activity).finish()
        }

    // jump if action up, count for double jump, check action move for sneek
        fun onClick(x: Float, y: Float) {
            if (pauseButton.checkIfClicked(x, y)) {
                val intent = Intent(context, PauseActivity::class.java)
                context.startActivity(intent)
                gameStatus = false
            } else {
                player.groundJumping(context)
                player.playerModel.jump()
            }
        }

}
