package de.thm.lampgame.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import de.thm.lampgame.controller.GameOverActivity
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.Tileset
import de.thm.lampgame.controller.terrain.BitmapGround
import de.thm.lampgame.controller.terrain.GrassLandscapeMap
import de.thm.lampgame.model.TilesetQueueModel


class GameView(context: Context) : View(context) {
    var screenWidth = 0
    var screenHeight = 0
    var runnable: Runnable? = null
    val UPDATE_MILLIS: Long = 1
    var gamestate = true
    var multiplikator = 0
    var collision = false
    var tilesetQueue = TilesetQueueModel()
    val paint = Paint()


    init {
        paint.setTextSize(75F)
        paint.setColor(Color.BLACK)

        val display = (getContext() as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        tilesetQueue.initQueue(Tileset(context, screenWidth, 0, screenWidth, screenHeight),Tileset(context,screenWidth*2, 0, screenWidth, screenHeight),screenWidth)
        runnable = Runnable { invalidate() }
    }

    var player = Player(context, screenHeight, screenWidth)
    var map =  GrassLandscapeMap(context, screenHeight, screenWidth)
    var ground = BitmapGround(context,screenWidth)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (gamestate) {

            player.calkPoints()

            if (player.points % 100 == 0) multiplikator++
            player.calkFire()


            //cloud background-fragment
            map.drawClouds(canvas, 0.4 + multiplikator/4);
            map.drawMountains(canvas, 2 + multiplikator/2);
            ground.draw(canvas, 10 + multiplikator)

            tilesetQueue.queue.first().drawTileset(10 + multiplikator)
            //Alternative Lösung für die for-Schleife mit ClassCastException
            tilesetQueue.queue.first().obstacles.forEach {
                it.draw(canvas, 10 + multiplikator)
            }

            tilesetQueue.queue.last().drawTileset(10 + multiplikator )
            //Alternative Lösung für die for-Schleife mit ClassCastException
            tilesetQueue.queue.last().obstacles.forEach {
                it.draw(canvas, 10 + multiplikator)
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

            for (i in 0 until tilesetQueue.queue.last().obstacles.size) {
                if (this.checkCollisions(
                        tilesetQueue.queue.last().obstacles[i].death,
                        tilesetQueue.queue.last().obstacles[i].bmp,
                        tilesetQueue.queue.last().obstacles[i].x,
                        tilesetQueue.queue.last().obstacles[i].y,
                        player.rechar[0]!!,
                        player.charX,
                        player.charY
                    )
                )
                    collision = true
            }

            //check If a new Tileset needs to be inserted into the Queue
            if (tilesetQueue.queue.first().startX <= -screenWidth) {
                var rest = -screenWidth - tilesetQueue.queue.first().startX
                tilesetQueue.recycleOldTileset()
                tilesetQueue.insertTileset(
                    screenWidth - rest,
                    Tileset(context, screenWidth - rest, 0, screenWidth, screenHeight)
                )
            }

            //draw Char
            player.setJumpStats(collision)
            player.drawChar(canvas)
            player.drawFirebar(canvas)

            canvas.drawText("Punkte: " + player.points.toString(), 10F, 75F, paint)



            // refresh
            handler!!.postDelayed(runnable!!, UPDATE_MILLIS)

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
        val action = event.action
        when (action)
        {
            MotionEvent.ACTION_DOWN -> {
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
                        gamestate = false
                        this.gameOver()
                }
          return false
        }

    private fun checkObstacleCollisions(playerHitbox: Rect, obstacleBitmap: Bitmap, obstacleX: Int, obstacleY: Int) : Boolean {
                val obstacleHitboxOben = Rect(obstacleX, obstacleY, (obstacleX + obstacleBitmap.width), (obstacleY + player.maxVelocity+5))
                val obstacleHitboxUnten = Rect(obstacleX, obstacleY + player.maxVelocity+5, (obstacleX + obstacleBitmap.width), (obstacleY + obstacleBitmap.height-player.maxVelocity+5))
                if (Rect.intersects(obstacleHitboxUnten, playerHitbox)) {
                    gamestate = false
                    this.gameOver()
                }
                else if (Rect.intersects(obstacleHitboxOben, playerHitbox)) {
                    return true
                }
           return false
        }

    }



