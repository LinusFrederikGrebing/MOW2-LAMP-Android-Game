package de.thm.lampgame.controller.Activitys

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.view.GameView


class StartGameActivity : AppCompatActivity() {
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
        if (gameView!!.gameStatus) {
            val intent = Intent(this, PauseActivity::class.java)
            startActivity(intent)
        }
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