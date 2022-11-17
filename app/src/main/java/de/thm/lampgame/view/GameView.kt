package de.thm.lampgame.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View
import de.thm.lampgame.controller.*
import de.thm.lampgame.controller.Activitys.GameOverActivity
import de.thm.lampgame.controller.Activitys.PauseActivity
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MapController
import de.thm.lampgame.controller.maps.MountainLandscapeMap
import de.thm.lampgame.controller.ObstaclesBitmaps.BitmapGround
import de.thm.lampgame.controller.maps.MarsLandscapeMap
import de.thm.lampgame.controller.tileset.Tileset
import de.thm.lampgame.controller.tileset.TilesetQueue

class GameView(context: Context) : View(context) {
    private var screenWidth = 0
    private var screenHeight = 0
    private var runnable: Runnable? = null
    private val updateMillis: Long = 5
    var gameStatus = true
    private var multiplication = 0
    private var tilesetQueue = TilesetQueue()
    private val paint = Paint()
    private var map: MapController
    private var pauseButton: PauseButton
    private var activeItem: ActiveItem

    var tilesetList = ArrayList<Tileset>()
    val tilesetsCount = 10


    init {
        paint.textSize = 75F
        paint.color = Color.BLACK
        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels
        pauseButton = PauseButton(context, screenWidth, screenHeight)
        activeItem = ActiveItem(context, screenWidth, screenHeight)
        map = if (CemeteryLandscapeMap.active) CemeteryLandscapeMap(
            context,
            screenHeight,
            screenWidth
        )
        else if (MarsLandscapeMap.active) MarsLandscapeMap(context, screenHeight, screenWidth)
        else MountainLandscapeMap(context, screenHeight, screenWidth)
        for (i in 1..tilesetsCount) {
            tilesetList.add(Tileset(i, context, screenWidth, screenWidth, screenHeight))
        }

        tilesetQueue.initQueue(
            Tileset(0, context, 0, screenWidth, screenHeight),
            Tileset(
                (1..tilesetsCount).random(),
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

    private var ground = BitmapGround(context, screenWidth, screenHeight)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (gameStatus) {

            player.calkPoints()
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



            if (player.dblPtsDur > 0) paint.color = Color.RED else paint.color = Color.BLACK
            canvas.drawText("Punkte: " + player.points.toString(), 10F, 75F, paint)
            canvas.drawText("Fackeln: " + player.coinsPerRound.toString(), 10F, 135F, paint)
            tilesetQueue.iterations++
            activeItem.drawbg(canvas)
            pauseButton.draw(canvas)
            if (player.dblPtsDur > 0) { player.dblPtsDur-- ; activeItem.draw(canvas) }
            if (player.dblJumpDur > 0) { player.dblJumpDur-- ; activeItem.draw(canvas) } else player.maxJump = 2
            if (player.immortalDur > 0) { player.immortalDur-- ; activeItem.draw(canvas) } else player.immortal = false


            // refresh
            handler!!.postDelayed(runnable!!, updateMillis)

            Log.i("testCoins", player.coinsPerRound.toString())
        }
    }

    // Start GameOver Activity
    fun gameOver() {
        val intent = Intent(context, GameOverActivity::class.java)
        intent.putExtra("POINTS", player.points)
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