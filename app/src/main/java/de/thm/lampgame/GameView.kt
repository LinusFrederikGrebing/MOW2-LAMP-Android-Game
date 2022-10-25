package de.thm.lampgame

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View
import de.thm.lampgame.model.TilesetQueueModel
import de.thm.lampgame.controller.Player
import de.thm.lampgame.controller.terrain.GrassLandscapeMap
import de.thm.lampgame.controller.GameOverActivity
import de.thm.lampgame.controller.Tileset
import de.thm.lampgame.controller.terrain.BitmapGround

class GameView(context: Context) : View(context) {
    var screenWidth = 0
    var screenHeight = 0
    var runnable: Runnable? = null
    val UPDATE_MILLIS: Long = 5
    var points = 0
    var gamestate = true
    var collision = false
    var tilesetQueue = TilesetQueueModel()

    init {
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
        // End the Game after getting 1000 Points for testing
        points++
        player.calkPoints()
        if(points == 2000) this.gameOver()
        player.calkFire()



        //cloud background-fragment
        map.drawClouds(canvas, 0.4);
        map.drawMountains(canvas, 2);
        ground.draw(canvas,10)

        tilesetQueue.queue.first().drawTileset(10)
        //Alternative Lösung für die for-Schleife mit ClassCastException
        tilesetQueue.queue.first().obstacles.forEach {
            it.draw(canvas,10)
        }

        tilesetQueue.queue.last().drawTileset(10)
        //Alternative Lösung für die for-Schleife mit ClassCastException
        tilesetQueue.queue.last().obstacles.forEach {
            it.draw(canvas,10)
        }

        //check Map-Collision
        collision = this.checkCollisions(ground.death, ground.bmp, player.charX, ground.y, player.rechar[0]!!, player.charX, player.charY)

        //check Tileset Obstacles-Collision
        for(i in 0 until tilesetQueue.queue.first().obstacles.size) {
            if(this.checkCollisions(tilesetQueue.queue.first().obstacles[i].death, tilesetQueue.queue.first().obstacles[i].bmp, tilesetQueue.queue.first().obstacles[i].x, tilesetQueue.queue.first().obstacles[i].y, player.rechar[0]!!, player.charX, player.charY))
                collision = true
        }

        for(i in 0 until tilesetQueue.queue.last().obstacles.size) {
            if(this.checkCollisions(tilesetQueue.queue.last().obstacles[i].death, tilesetQueue.queue.last().obstacles[i].bmp, tilesetQueue.queue.last().obstacles[i].x, tilesetQueue.queue.last().obstacles[i].y, player.rechar[0]!!, player.charX, player.charY))
                collision = true
        }

        //check If a new Tileset needs to be inserted into the Queue
        if (tilesetQueue.queue.first().startX <= -screenWidth) {
            var rest = -screenWidth - tilesetQueue.queue.first().startX
            tilesetQueue.recycleOldTileset()
            tilesetQueue.insertTileset(screenWidth-rest,Tileset(context,screenWidth-rest, 0, screenWidth, screenHeight))
        }

        //draw Char
        player.setJumpStats(collision)
        player.drawChar(canvas)
        player.drawFirebar(canvas)

        // refresh
        handler!!.postDelayed(runnable!!, UPDATE_MILLIS)
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
            MotionEvent.ACTION_UP -> {
                player.sprung()
            }
            MotionEvent.ACTION_MOVE -> {
                player.birdsneek = true
                Log.i("start X" , event.getX().toString());
                Log.i("start Y" , event.getY().toString());
            }
        }
        if (action == MotionEvent.ACTION_UP){
            player.birdsneek = false
        }
        return true
    }

    private fun checkCollisions(death: Boolean, bitmap1: Bitmap, x1: Int, y1: Int, bitmap2: Bitmap, x2: Int, y2: Int) : Boolean {
        if(gamestate) {
            val hitboxRect1 = Rect(x1, y1, (x1 + bitmap1.width), (y1 + bitmap1.height))
            val hitboxRect2 = Rect(x2, y2, (x2 + bitmap2.width), (y2 + bitmap2.height))
            if (Rect.intersects(hitboxRect1, hitboxRect2)) {
                if(death){
                   gamestate = false
                   this.gameOver()
               } /*else if( y2 < y1 + bitmap1.height-40 && y2 > y1+40 || ((y2 + bitmap2.height < y1 + bitmap1.height-40) && (y2 + bitmap2.height > y1+40))) {

                          gamestate = false
                          this.gameOver()

                }*/
                return true
            }

        }
        return false
    }

}


