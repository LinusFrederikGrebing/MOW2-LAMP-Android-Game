package de.thm.lampgame

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Point
import android.view.MotionEvent
import android.view.View


class GameView(context: Context) : View(context) {
    var screenWidth = 0
    var screenHeight = 0
    var runnable: Runnable? = null
    val UPDATE_MILLIS: Long = 30
    var points = 0

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

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // End the Game after getting 1000 Points for testing
        points++
        if(points == 1000) this.gameover()

        // static background
        //map.drawSky(canvas)

        //cloud background-fragment
        map.drawClouds(canvas, 0.4);

        // mountain background-fragment
        map.drawMountains(canvas, 2);

        // grass background-fragment
        map.drawGrass(canvas, 15);

        // draw the player on right position with right animation
        player.setjumpStats()
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
}


