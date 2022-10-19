package de.thm.lampgame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.view.MotionEvent
import android.view.View

class GameView(context: Context) : View(context) {
    var screenWidth = 0
    var screenHeight = 0
    var runnable: Runnable? = null
    val UPDATE_MILLIS: Long = 30
    var points = 0
    var gamestate = true
    var collision = false

    init {
        val display = (getContext() as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
        runnable = Runnable { invalidate() }
    }

    var player = Player(context, screenHeight, screenWidth)
    var map =  GrassLandscapeMap(context, screenHeight, screenWidth)
    var tubes =  BitmapObstacles(context, 700, screenWidth)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // End the Game after getting 1000 Points for testing
        points++
        if(points == 2000) this.gameover()


        // static background
        //map.drawSky(canvas)

        //cloud background-fragment
        map.drawClouds(canvas, 0.4);

        // mountain background-fragmentS
        map.drawMountains(canvas, 2);

        // grass background-fragment
        map.drawGrass(canvas, 50);

        // draw the player on right position with right animation

        tubes.drawTubes(canvas, 10)

        //check Map-Collision
        collision = this.checkCollisions(map.grass, player.charX.toFloat(), map.grassY, player.rechar[0]!!, player.charX.toFloat(), player.charY.toFloat())

        //check Tube-Collision
        if(!collision) collision =  this.checkCollisions(tubes.bottomtube!!, tubes.tubeX.toFloat(), tubes.TubeHeight.toFloat(), player.rechar[0]!!, player.charX.toFloat(), player.charY.toFloat())

        //draw Char
        player.setjumpStats(collision)
        player.drawChar(canvas)

        // refresh
        handler!!.postDelayed(runnable!!, UPDATE_MILLIS)
    }

    // Start GameOver Activity
    private fun gameover() {
        val intent = Intent(context, GameOver::class.java)
        intent.putExtra("POINTS", points)
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

    fun checkCollisions(bitmap1: Bitmap, x1: Float, y1: Float, bitmap2: Bitmap, x2: Float, y2: Float) : Boolean {
        if(gamestate == true) {
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


