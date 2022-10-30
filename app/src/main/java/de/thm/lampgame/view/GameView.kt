package de.thm.lampgame.view

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.media.MediaPlayer
import android.view.MotionEvent
import android.view.View
import de.thm.lampgame.R
import de.thm.lampgame.controller.GameOverActivity
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.Tileset
import de.thm.lampgame.controller.TilesetQueue
import de.thm.lampgame.controller.items.Torch
import de.thm.lampgame.controller.terrain.BitmapGround
import de.thm.lampgame.controller.maps.GrassLandscapeMap


class GameView(context: Context) : View(context) {
    private var screenWidth = 0
    private var screenHeight = 0
    private var runnable: Runnable? = null
    private val updateMillis: Long = 5
    private var gameStatus = true
    private var multiplication = 0
    private var tilesetQueue = TilesetQueue()
    private val paint = Paint()
    private val mp: MediaPlayer
    var tilesetList = ArrayList<Tileset>()
    private val tilesetsCount = 5
    var newX = 0
    var newY = 0
    var possible = false


    init {
        paint.textSize = 75F
        paint.color = Color.BLACK
        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels
        for (i in 1..tilesetsCount) {
            tilesetList.add(Tileset(i, context, screenWidth, 0, screenWidth, screenHeight))
        }

        tilesetQueue.initQueue(
            Tileset(0, context, 0, 0, screenWidth, screenHeight),
            Tileset(
                (1..tilesetsCount).random(),
                context,
                screenWidth,
                0,
                screenWidth,
                screenHeight
            ),
            screenWidth
        )
        runnable = Runnable { invalidate() }
        mp = MediaPlayer.create(context, R.raw.background)
        mp.start()
    }

    private var player = Player(context, screenHeight, screenWidth)
    private var map = GrassLandscapeMap(context, screenHeight, screenWidth)
    private var ground = BitmapGround(context, screenWidth, screenHeight)
    private var torch = Torch(context, screenHeight, screenWidth, -500, -500)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (gameStatus) {
            player.calkPoints()
            player.calkFire()
            if(player.fire <= 0.0F) { gameStatus = false; this.gameOver() }
            if (player.points % 300 == 0) {
                multiplication++
                possible = true
            }
            //draw background
            map.drawMap(
                canvas,
                0.4 + multiplication / 4,
                0.8 + multiplication / 3,
                1.2 + multiplication / 2
            )

            //draw tileset with obstacles
            tilesetQueue.drawTilesetsAndCheckCollisions(canvas, 10 + multiplication, player, ground)
            if (tilesetQueue.gameover) {
                gameStatus = false
                this.gameOver()
            }

            //Items Testing
            if(torch.x > 0){
                torch.draw(canvas, 10 + multiplication)
                torch.itemPickup(player, torch.activateEffect)

            }

            //check If a new Tileset needs to be inserted into the Queue
            val insertet = tilesetQueue.insertTilesetifneedTo(screenWidth, tilesetList, tilesetsCount)
            if (insertet) {
                if (possible) {
                    do {
                        newX = torch.randX()
                        newY = torch.randY()
                    } while (tilesetQueue.tilesetCollision(torch.bmp, newX, newY))
                    torch.initNewTorch(newX, newY)
                    possible = false
                }
            }
            //draw Char
            player.setJumpStats(tilesetQueue.collision)
            player.drawChar(canvas)
            player.drawFirebar(canvas)

            canvas.drawText("Punkte: " + player.points.toString(), 10F, 75F, paint)

            // refresh
            handler!!.postDelayed(runnable!!, updateMillis)

        } else {
            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.MULTIPLY)
            mp.stop()
        }

    }


    // Start GameOver Activity
    private fun gameOver() {
        val intent = Intent(context, GameOverActivity::class.java)
        intent.putExtra("POINTS", player.points)
        context.startActivity(intent)
    }

    // jump if action up, count for double jump, check action move for sneek
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (player.jumpCount < 2) player.groundjumping(context)
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





