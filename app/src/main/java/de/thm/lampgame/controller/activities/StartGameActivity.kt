package de.thm.lampgame.controller.activities

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.view.GameView


class StartGameActivity : AppCompatActivity() {
    private var gameView: GameView? = null
    private var mediaPlayer: MediaPlayer? = null
    private var length: Int? = 0
    private var activeMusic: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gameView = GameView(this)
        setContentView(gameView)
        Database.listOfMusic.forEach {
            if (it.itemInfo.active) {
                activeMusic = it.song
            }
        }
        mediaPlayer = MediaPlayer.create(this, activeMusic)
    }

    override fun onPause() {
        super.onPause()
        mediaPlayer?.pause()
        length = mediaPlayer?.currentPosition
        if (gameView!!.gameStatus) {
            val intent = Intent(this, PauseActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        length?.let { mediaPlayer?.seekTo(it) }
        mediaPlayer?.start()
        gameView!!.gameStatus = true
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.stop()
    }
}