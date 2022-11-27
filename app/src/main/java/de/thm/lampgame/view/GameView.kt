package de.thm.lampgame.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View
import de.thm.lampgame.controller.*
import de.thm.lampgame.controller.activities.GameOverActivity
import de.thm.lampgame.controller.activities.PauseActivity
import de.thm.lampgame.view.item.ActiveItem
import de.thm.lampgame.controller.maps.MapController
import de.thm.lampgame.controller.tileset.TilesetQueue
import de.thm.lampgame.model.shop.Database
import kotlin.math.roundToInt

class GameView(context: Context) : View(context) {
    private var screenWidth = 0
    private var screenHeight = 0
    private var runnable: Runnable? = null
    private val updateMillis: Long = 2
    private var multiplication = 0
    private val paint = Paint()
    private lateinit var map: MapController
    var gameStatus = true

    // the game should also be able to get the gameover status from other classes, such as the TilesetQueueModel
    companion object {
        var gameover = false
    }

    init {
        // if the game starts or is restarted, the gameover status must be set to false
        gameover = false

        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels

        paint.color = Color.BLACK
        paint.textSize = screenHeight*0.07.toFloat()

        // the active status is used to check which map needs to be loaded
        Database.listOfMaps.forEach { if (it.active) map = it.createMap(context,screenHeight,screenWidth) }

        runnable = Runnable { invalidate() }
    }

    // initialize view and controller elements
    private var tilesetQueue = TilesetQueue(context, screenWidth, screenHeight)
    private var player = Player(context, screenHeight, screenWidth)
    private var pauseButton = PauseButton(context, screenWidth, screenHeight)
    private var drawTorch = DrawTorchCount(context, screenWidth, screenHeight)
    private var activeItem = ActiveItem(context, screenWidth, screenHeight)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // redraw the game elements only when the game status is active
        if (gameStatus) {
            // every 300 iterations the game speed is increased
            if (tilesetQueue.iterations % 300 == 0) multiplication++
            // draw the background. There are three background layers. Each layer can have a different velocity, usually the backmost level is the slowest
            map.drawMap(canvas, 0.1 + multiplication*0.1, 0.2 + multiplication*0.2, 0.3 + multiplication*0.3)

            // draw tileset with obstacles
            tilesetQueue.drawTilesetsAndCheckCollisions(canvas, (screenWidth/200) + multiplication, player)

            // draw canvas-elements
            canvas.drawText("Punkte: ${player.points.roundToInt()}",  (screenWidth*0.01).toFloat(), (screenHeight*0.075).toFloat(), paint)
            drawTorch.draw(canvas, player.coinsPerRound)
            pauseButton.draw(canvas)

            // draw the player and if an item is picked up, draw the item
            player.checkItemDurAndSetItemEffect(canvas, paint, activeItem)
            player.drawPlayer(canvas,1.0 + (multiplication * 0.01), tilesetQueue.collision)

            // check if the game is lost yet
            if (gameover) { this.gameOver() }

            // refresh
            handler!!.postDelayed(runnable!!, updateMillis)
        }
    }

    // Start GameOver Activity
    fun gameOver() {
        gameStatus = false
        val intent = Intent(context, GameOverActivity::class.java)
        intent.putExtra("POINTS", player.points.roundToInt())
        context.startActivity(intent)
        (context as Activity).finish()
    }

    // jump if action up, count for double jump, check action move for sneek
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (pauseButton.checkIfClicked(event.x, event.y)) {
                    val intent = Intent(context, PauseActivity::class.java)
                    context.startActivity(intent)
                    gameStatus = false
                } else if (player.jumpCount < player.maxJump) {
                    player.groundjumping(context)
                    player.sprung()
                }
                return true
            }
        }
        return false
    }
}