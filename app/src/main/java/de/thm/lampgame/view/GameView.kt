package de.thm.lampgame.view

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.*
import android.media.MediaPlayer
import android.provider.Settings.Global
import android.view.MotionEvent
import android.view.View
import de.thm.lampgame.R
import de.thm.lampgame.controller.GameOverActivity
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.StartGameActivity
import de.thm.lampgame.controller.Tileset
import de.thm.lampgame.controller.items.Torch
import de.thm.lampgame.controller.terrain.BitmapGround
import de.thm.lampgame.controller.terrain.GrassLandscapeMap
import de.thm.lampgame.model.TilesetQueueModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameView(context: Context) : View(context) {
    private var screenWidth = 0
    private var screenHeight = 0
    private var runnable: Runnable? = null
    private val updateMillis: Long = 5
    private var gameStatus = true
    private var multiplication = 0
    private var collision = false
    private var tilesetQueue = TilesetQueueModel()
    private val paint = Paint()
    private val mp: MediaPlayer

    private val feedingArray = ArrayDeque<Tileset>()
    init {
        paint.textSize = 75F
        paint.color = Color.BLACK
        screenWidth = Resources.getSystem().displayMetrics.widthPixels
        screenHeight = Resources.getSystem().displayMetrics.heightPixels


            for (i in 0..30) {
                feedingArray.add(Tileset(context, screenWidth, 0, screenWidth, screenHeight))
            }


        tilesetQueue.initQueue(Tileset(context, screenWidth, 0, screenWidth, screenHeight),Tileset(context,screenWidth*2, 0, screenWidth, screenHeight),screenWidth)
        runnable = Runnable { invalidate() }
        mp = MediaPlayer.create(context, R.raw.background)
        mp.start()
    }

    private var player = Player(context, screenHeight, screenWidth)
    private var map =  GrassLandscapeMap(context, screenHeight, screenWidth)
    private var ground = BitmapGround(context,screenWidth, screenHeight)
    private var torch = Torch(context,screenHeight,screenWidth,2800,200)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (gameStatus) {

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

            //check Tileset Obstacles-Collision
            for (i in 0 until tilesetQueue.queue.first().obstacles.size) {
                if (this.checkCollisions(
                        tilesetQueue.queue.first().obstacles[i].death,
                        tilesetQueue.queue.first().obstacles[i].bmp,
                        tilesetQueue.queue.first().obstacles[i].x,
                        tilesetQueue.queue.first().obstacles[i].y,
                        player.rechar[0]!!,
                        player.charX,
                        player.charY
                    )
                )
                    collision = true
            }

            //Items Testing
            torch.draw(canvas,10+multiplication)
            torch.itemPickup(player,torch.activateEffect)

            //check If a new Tileset needs to be inserted into the Queue
            if (tilesetQueue.queue.first().startX <= -screenWidth) {
                var rest = -screenWidth - tilesetQueue.queue.first().startX
                tilesetQueue.recycleOldTileset()
                tilesetQueue.insertTileset(
                        screenWidth - rest, feedingArray.first())
                tilesetQueue.queue.last().startX  -= rest
                feedingArray.removeFirst()
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
    // Start GameOver Activity
    private fun gameOver() {
        feedingArray.clear()
        val intent = Intent(context, GameOverActivity::class.java)
        intent.putExtra("POINTS", player.points)
        context.startActivity(intent)
    }

    // jump if action up, count for double jump, check action move for sneek
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action)
        {
            MotionEvent.ACTION_DOWN -> {
                if(player.jumpCount < 2) player.groundjumping(context)
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



