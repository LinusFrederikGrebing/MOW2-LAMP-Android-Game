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
    val tilesetsCount = 5

    init {
        paint.textSize = 75F
        paint.color = Color.BLACK
        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels
        for (i in 1 .. tilesetsCount){
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
    private var map =  GrassLandscapeMap(context, screenHeight, screenWidth)
    private var ground = BitmapGround(context,screenWidth, screenHeight)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (gameStatus) {
            if (player.dblPtsDur > 0) player.dblPtsDur--
            player.calkPoints()

            if (player.points % 300 == 0) multiplication++
            player.calkFire()


            //cloud background-fragment
            map.drawMap(canvas,  0.1 + multiplication/4, 0.2 + multiplication/3, 0.3 + multiplication/2 )


            //draw tileset with obstacless
            tilesetQueue.drawTilesetsAndCheckCollisions(canvas, 15 + multiplication, player, ground)
            if (tilesetQueue.gameover || player.fire <= 0F) {
                gameStatus = false
                this.gameOver()
            }

            //check If a new Tileset needs to be inserted into the Queue
            tilesetQueue.insertTilesetifneedTo(screenWidth, tilesetList, tilesetsCount)

            //draw Char
            player.setJumpStats(tilesetQueue.collision)
            player.drawChar(canvas)
            player.drawFirebar(canvas)

            canvas.drawText("Punkte: " + player.points.toString(), 10F, 75F, paint)


            tilesetQueue.iterations++
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