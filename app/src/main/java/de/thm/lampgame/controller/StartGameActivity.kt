package de.thm.lampgame.controller

import android.R
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import de.thm.lampgame.view.GameView


class StartGameActivity : Activity() {
    var gameView: GameView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameView = GameView(this)
        setContentView(gameView)
    }

    override fun onPause() {
        if (gameView!!.gameStatus && !gameView!!.pauseCalled) {
            super.onPause()
            intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            gameView!!.pauseCalled = true
        }
        else onResume()
    }

    override fun onResume() {
        super.onResume()
        gameView!!.pauseCalled = false
    }
}