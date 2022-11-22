package de.thm.lampgame.view

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
import de.thm.lampgame.controller.obstaclesBitmaps.BitmapGround
import de.thm.lampgame.controller.items.ActiveItem
import de.thm.lampgame.controller.items.BonusJump
import de.thm.lampgame.controller.items.DoublePoints
import de.thm.lampgame.controller.items.Immortality
import de.thm.lampgame.controller.maps.MapController
import de.thm.lampgame.controller.tileset.TilesetQueue
import de.thm.lampgame.model.shop.Database
import kotlin.math.roundToInt


class GameView(context: Context) : View(context) {
    private var screenWidth = 0
    private var screenHeight = 0
    private var runnable: Runnable? = null
    private val updateMillis: Long = 2
    var gameStatus = true
    private var multiplication = 0

    private val paint = Paint()
    private lateinit var map: MapController
    private var tilesetQueue : TilesetQueue

    init {
        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels

        paint.color = Color.BLACK
        paint.textSize = screenHeight*0.07.toFloat()

        Database.listOfMaps.forEach { if (it.active) map = it.createMap(context,screenHeight,screenWidth) }

        tilesetQueue = TilesetQueue(screenWidth, screenHeight)
        tilesetQueue.initialQueue(context)

        runnable = Runnable { invalidate() }
    }


    private var player = Player(context, screenHeight, screenWidth)
    private var pauseButton = PauseButton(context, screenWidth, screenHeight)
    private var drawTorch = drawTorchIcon(context, screenWidth, screenHeight)
    private var activeItem = ActiveItem(context, screenWidth, screenHeight)
    private var ground = BitmapGround(context, screenWidth, screenHeight)



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (gameStatus) {
            tilesetQueue.iterations++
            if (tilesetQueue.iterations == 200) multiplication++


            //cloud background-fragment
            map.drawMap(canvas,
                0.1 + multiplication*0.1,
                0.2 + multiplication*0.2,
                0.3 + multiplication*0.3
            )

            //draw tileset with obstacless
            tilesetQueue.drawTilesetsAndCheckCollisions(canvas, (screenWidth/200) + multiplication, player, ground)

            if (tilesetQueue.gameover || player.fire <= 0F) {
                gameStatus = false
                this.gameOver()
            }

            canvas.drawText("Punkte: " + player.points.roundToInt().toString(),  (screenWidth*0.01).toFloat(), (screenHeight*0.075).toFloat(), paint)

            drawTorch.draw(canvas, player.coinsPerRound)
            pauseButton.draw(canvas)


            if (player.dblPtsDur > 0) {
                paint.color = Color.RED
                player.dblPtsDur--
                activeItem.drawCircle(canvas, DoublePoints.doublepointsduration)
            } else paint.color = Color.BLACK
            if (player.dblJumpDur > 0) {
                player.dblJumpDur--
                activeItem.drawCircle(canvas, BonusJump.bonusjumpduration)
            } else player.maxJump = 2
            if (player.immortalDur > 0) {
                player.immortalDur--
                activeItem.drawCircle(canvas, Immortality.immortalduration)
            } else player.immortal = false

            player.drawPlayer(canvas,1.0 + (multiplication * 0.02), tilesetQueue.collision)
            player.calkFire()

            // refresh
            handler!!.postDelayed(runnable!!, updateMillis)
        }
    }

    // Start GameOver Activity
    fun gameOver() {
        val intent = Intent(context, GameOverActivity::class.java)
        intent.putExtra("POINTS", player.points.roundToInt())
        context.startActivity(intent)
        (context as Activity).finish()
    }

    // jump if action up, count for double jump, check action move for sneek
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (pauseButton.checkIfClicked(event.x, event.y)) {
                    val intent = Intent(context, PauseActivity::class.java)
                    context.startActivity(intent)
                    gameStatus = false
                    return true
                } else if (player.jumpCount < 2) player.groundjumping(context)
                player.sprung()
            }
        }
        return true
    }


}