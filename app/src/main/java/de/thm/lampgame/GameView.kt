package de.thm.lampgame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.MotionEvent
import android.view.View
import de.thm.lampgame.controller.terrain.BitmapObstacles
import de.thm.lampgame.controller.GameOverActivity
import de.thm.lampgame.controller.terrain.GrassLandscapeMap
import de.thm.lampgame.controller.Player


class GameView(context: Context) : View(context) {
    private var screenWidth = 0
    private var screenHeight = 0
    private var runnable: Runnable? = null
    private val UPDATE_MILLIS: Long = 5
    private var gamestate = true
    private var collision = false
    private var tubesCount = 50
    private var multiplier = 0
    private var objList = ArrayList<BitmapObstacles>()
    init {
        val display = (getContext() as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        runnable = Runnable { invalidate() }
        for (i in 0 until tubesCount){
            objList.add(BitmapObstacles(context, 700, screenWidth*i))
        }

    }

    private var player = Player(context, screenHeight, screenWidth)
    private var map =  GrassLandscapeMap(context, screenHeight, screenWidth)
    private var tubes =  BitmapObstacles(context, 700, screenWidth)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if(player.points%20 == 0) multiplier++
        player.calkFire()
        player.calkPoints()

        // static background
        //map.drawSky(canvas)

        //cloud background-fragment
        map.drawClouds(canvas, 0.4 + multiplier)

        // mountain background-fragmentS
        map.drawMountains(canvas, 2 + multiplier)

        // grass background-fragment
        map.drawGrass(canvas, 20 + multiplier)

        // draw the player on right position with right animation
        for (i in 0 until objList.size){
            objList[i].drawTubes(canvas, 20 + multiplier)
        }
        collision = this.checkTubeCollisions(objList, player.rechar[0]!!, player.charX.toFloat(), player.charY.toFloat())
        if(!collision) collision = this.checkCollisions(map.grass, player.charX.toFloat(), map.grassY, player.rechar[0]!!, player.charX.toFloat(), player.charY.toFloat())
        //check Tube-Collision

        //draw Char
        player.setJumpStats(collision)
        player.drawChar(canvas)
        player.drawFirebar(canvas)

        // refresh
        handler!!.postDelayed(runnable!!, UPDATE_MILLIS)
    }

    // Start GameOver Activity
    private fun gameover() {
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
            }
        }
        if (action == MotionEvent.ACTION_UP){
            player.birdsneek = false
        }
             return true
       }

    fun checkTubeCollisions(objList: ArrayList<BitmapObstacles>, bitmap2: Bitmap, x2: Float, y2: Float) : Boolean {
        if(gamestate) {
            for(i in 0 until objList.size){
                val hitboxRect1 = Rect(objList[i].tubeX, objList[i].TubeHeight, (objList[i].tubeX + tubes.bottomtube!!.width),
                     (objList[i].TubeHeight + tubes.bottomtube!!.width)
                 )
                val hitboxRect2 = Rect(x2.toInt(), y2.toInt(), (x2 + bitmap2.width).toInt(),
                     (y2 + bitmap2.height).toInt()
                 )
            if (Rect.intersects(hitboxRect1, hitboxRect2)) {
                if (y2 > objList[i].TubeHeight-50){
                    gamestate = false
                    this.gameover()
                    return false
                }

                return true
            }
        }
        }
        return false
    }

    fun checkCollisions(bitmap1: Bitmap, x1: Float, y1: Float, bitmap2: Bitmap, x2: Float, y2: Float) : Boolean {
        if(gamestate) {
            val hitboxRect1 = Rect(x1.toInt(), y1.toInt(), (x1 + bitmap1.width).toInt(),
                (y1 + bitmap1.height).toInt()
            )
            val hitboxRect2 = Rect(x2.toInt(), y2.toInt(), (x2 + bitmap2.width).toInt(),
                (y2 + bitmap2.height).toInt()
            )
            if (Rect.intersects(hitboxRect1, hitboxRect2)) {
                return true
            }

        }
        return false
    }

}


