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
        // listofmusic represents a list of all possible music objects
        Database.listOfMusic.forEach {
            // load the music whose status is active
            if (it.itemInfo.active) {
                activeMusic = it.song
            }
        }
        // create the music player with the active music object
        mediaPlayer = MediaPlayer.create(this, activeMusic)
    }


    override fun onResume() {
        super.onResume()
        // check if the music player is currently stopped at a point, if so go ahead to that point
        length?.let { mediaPlayer?.seekTo(it) }

        // start the music player and set the game status to ture
        mediaPlayer?.start()
        gameView?.gameController?.gameStatus = true
    }

    override fun onPause() {
        super.onPause()
        // pause the music player and remember its current position
        mediaPlayer?.pause()
        length = mediaPlayer?.currentPosition

        // if the game is paused without setting the gameState to false, start the PauseActivity
        if (gameView?.gameController?.gameStatus == true) {
            val intent = Intent(this, PauseActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onDestroy() {
        // stop the music player
        super.onDestroy()
        mediaPlayer?.stop()
    }
}