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
import de.thm.lampgame.controller.items.DoublePoints
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
    val torchSpawnrate = 5
    var torchSpawncounter = 2 // 2 anstatt 0 wegen den 2 initial-Tilesets

    private val feedingArray = ArrayDeque<Tileset>()
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

            if (player.points % 200 == 0) multiplication++
            player.calkFire()


            //cloud background-fragment
            map.drawClouds(canvas, 0.4 + multiplication/4)
            map.drawMountains(canvas, 2 + multiplication/2)
            ground.draw(canvas, 10 + multiplication)

            tilesetQueue.queue.first().drawTileset(10 + multiplication)

            //Alternative Lösung für die for-Schleife mit ClassCastException
            tilesetQueue.queue.first().obstacles.forEach {
                it.draw(canvas, 10 + multiplication)
            }
            if (tilesetQueue.queue.first().hasItem) tilesetQueue.queue.first().item.draw(canvas,10+multiplication)
            tilesetQueue.queue.last().drawTileset(10 + multiplication )

            //Alternative Lösung für die for-Schleife mit ClassCastException
            tilesetQueue.queue.last().obstacles.forEach {
                it.draw(canvas, 10 + multiplication)
            }

            //check Map-Collision
            collision = this.checkCollisions(
                ground.death,
                ground.bmp,
                player.charX,
                ground.y,
                player.rechar[0]!!,
                player.charX,
                player.charY
            )

            //draw tileset with obstacles
            tilesetQueue.drawTilesetsAndCheckCollisions(canvas, 10 + multiplication, player, ground)
            if (tilesetQueue.gameover) {
                gameStatus = false
                this.gameOver()
            }

            if (tilesetQueue.queue.first().hasItem) tilesetQueue.queue.first().item.itemPickup(player,tilesetQueue.queue.first().item.activateEffect)
            if (tilesetQueue.queue.last().hasItem) tilesetQueue.queue.last().item.itemPickup(player,tilesetQueue.queue.last().item.activateEffect)

            //check If a new Tileset needs to be inserted into the Queue
            if (tilesetQueue.queue.first().startX <= -screenWidth) {
                    val rest = -screenWidth - tilesetQueue.queue.first().startX
                    val tileset =  getpossibleTileset()
                    tileset.placeTileset(screenWidth - rest)
                    tilesetQueue.insertTileset(
                        screenWidth - rest, tileset)
                    torchSpawncounter++
                    if (torchSpawncounter % torchSpawnrate == 0) tilesetQueue.queue.last().randomItemSpawn(true)
                    else tilesetQueue.queue.last().randomItemSpawn(false)
            }

            //draw Char
            player.setJumpStats(collision)
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

    var random = 0
    fun getpossibleTileset(): Tileset {
        do {
            random = (0 until 2).random()
        } while (tilesetQueue.queue.last()==tilesetList[random])
        return tilesetList[random]
    }

    // Start GameOver Activity
    private fun gameOver() {
        feedingArray.clear()
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


    private fun checkCollisions(death: Boolean, obstacleBitmap: Bitmap, obstacleX: Int, obstacleY: Int, playerBitmap: Bitmap, playerX: Int, playerY: Int) : Boolean {
        val playerHitbox = Rect(playerX, playerY, (playerX + playerBitmap.width), (playerY + playerBitmap.height))
        return if(death) checkDeathCollisions(playerHitbox, obstacleBitmap, obstacleX, obstacleY)
        else checkObstacleCollisions(playerHitbox, obstacleBitmap, obstacleX, obstacleY)
    }


    private fun checkDeathCollisions(playerHitbox: Rect, obstacleBitmap: Bitmap, obstacleX: Int, obstacleY: Int) : Boolean {
                val obstacleHitbox = Rect(obstacleX, obstacleY, (obstacleX + obstacleBitmap.width), (obstacleY + obstacleBitmap.height))
                if (Rect.intersects(obstacleHitbox, playerHitbox)) {
                        gameStatus = false
                        this.gameOver()
                }
          return false
        }

    private fun checkObstacleCollisions(playerHitbox: Rect, obstacleBitmap: Bitmap, obstacleX: Int, obstacleY: Int) : Boolean {
                val obstacleHitboxOben = Rect(obstacleX, obstacleY, (obstacleX + obstacleBitmap.width), (obstacleY + player.maxVelocity+5))
                val obstacleHitboxUnten = Rect(obstacleX, obstacleY + player.maxVelocity+5, (obstacleX + obstacleBitmap.width), (obstacleY + obstacleBitmap.height-player.maxVelocity+5))
                if (Rect.intersects(obstacleHitboxUnten, playerHitbox)) {
                    gameStatus = false
                    this.gameOver()
                }
                else if (Rect.intersects(obstacleHitboxOben, playerHitbox)) {

                    return true
                }
           return false
        }

    }



