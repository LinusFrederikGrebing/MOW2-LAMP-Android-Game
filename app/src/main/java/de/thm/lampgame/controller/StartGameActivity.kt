package de.thm.lampgame.controller

import android.app.Activity
import android.os.Bundle
import de.thm.lampgame.view.GameView

class StartGameActivity : Activity() {
    var gameView: GameView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameView = GameView(this)
        setContentView(gameView)
    }
}