package de.thm.lampgame.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.View
import de.thm.lampgame.controller.GameController

class GameView(context: Context) : View(context) {
    private var runnable: Runnable? = null
    private val updateMillis: Long = 1
    var gameController = GameController(context)

    init {
        runnable = Runnable { invalidate() }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        gameController.game(canvas)
        // refresh
        handler!!.postDelayed(runnable!!, updateMillis)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                gameController.onClick(event.x, event.y)
                return true
            }
        }
        return false
    }
}
