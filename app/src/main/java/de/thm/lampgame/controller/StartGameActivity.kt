package de.thm.lampgame.controller

import android.app.Activity
import android.os.Bundle
import de.thm.lampgame.view.GameView
import android.media.MediaPlayer

class StartGameActivity : Activity() {
    private var gameView: GameView? = null
    var mp: MediaPlayer? = null
    var length : Int? = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameView = GameView(this)
        setContentView(gameView)
        mp = MediaPlayer.create(this, de.thm.lampgame.R.raw.background)
    }

    override fun onPause() {
        super.onPause()
            mp?.pause()
            length = mp?.currentPosition
    }

    override fun onResume() {
        super.onResume()
        length?.let { mp?.seekTo(it) }
        mp?.start()
        gameView!!.gameStatus = true
    }

    override fun onDestroy() {
        super.onDestroy()
        mp?.stop()
    }
}