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
import de.thm.lampgame.controller.Activitys.GameOverActivity
import de.thm.lampgame.controller.Activitys.PauseActivity
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapGround
import de.thm.lampgame.controller.items.ActiveItem
import de.thm.lampgame.controller.items.BonusJump
import de.thm.lampgame.controller.items.DoublePoints
import de.thm.lampgame.controller.items.Immortality
import de.thm.lampgame.controller.maps.MapController
import de.thm.lampgame.controller.tileset.Tileset
import de.thm.lampgame.controller.tileset.TilesetQueue
import de.thm.lampgame.model.Database
import kotlin.math.roundToInt


class GameView(context: Context) : View(context) {
    private var screenWidth = 0
    private var screenHeight = 0
    private var runnable: Runnable? = null
    private val updateMillis: Long = 2
    var gameStatus = true
    private var multiplication = 0
    private var tilesetQueue = TilesetQueue()
    private val paint = Paint()
    private lateinit var map: MapController
    var tilesetList = ArrayList<Tileset>()
    val tilesetsCount = 16


    init {

        paint.color = Color.BLACK
        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels
        paint.textSize = screenHeight*0.07.toFloat()

        Database.listOfMaps.forEach { if (it.active) map = it.createMap(context,screenHeight,screenWidth) }
        for (i in 1..tilesetsCount) {
            tilesetList.add(Tileset(i, context, screenWidth, screenWidth, screenHeight))
        }

        tilesetQueue.initQueue(
            Tileset(0, context, 0, screenWidth, screenHeight),
            Tileset(
                (1..15).random(),
                context,
                screenWidth,
                screenWidth,
                screenHeight
            ),
            screenWidth
        )
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

            player.calkPoints(1.0 + (multiplication * 0.02))
            if (tilesetQueue.iterations == 200) {
                multiplication++
                println("Points: " + player.points + " Multi: " + multiplication)
            }
            player.calkFire()


            //cloud background-fragment
            map.drawMap(
                canvas,
                0.1 + multiplication / 4,
                0.2 + multiplication / 3,
                0.3 + multiplication / 2
            )

            player.drawChar(canvas)
            //draw tileset with obstacless
            tilesetQueue.drawTilesetsAndCheckCollisions(canvas, (screenWidth/200) + multiplication, player, ground)
            if (tilesetQueue.gameover || player.fire <= 0F) {
                gameStatus = false
                this.gameOver()
            }

            //check If a new Tileset needs to be inserted into the Queue
            tilesetQueue.insertTilesetifneedTo(screenWidth, tilesetList, tilesetsCount)

            //draw Char
            player.setJumpStats(tilesetQueue.collision)

            player.drawFirebar(canvas)


            canvas.drawText("Punkte: " + player.points.roundToInt().toString(),  (screenWidth*0.01).toFloat(), (screenHeight*0.075).toFloat(), paint)

            drawTorch.draw(canvas, player.coinsPerRound)
            tilesetQueue.iterations++
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
            /*  MotionEvent.ACTION_MOVE -> {
                  player.birdsneek = true
              }*/
        }
        /* if (action == MotionEvent.ACTION_UP){
             player.birdsneek = false
         }*/
        return true
    }


}