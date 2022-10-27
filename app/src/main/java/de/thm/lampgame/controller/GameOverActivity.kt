package de.thm.lampgame.controller

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import de.thm.lampgame.R

class GameOverActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        val points = intent.extras!!.getInt("POINTS")
        val viewPoints: TextView = findViewById(R.id.points) as TextView
        viewPoints.setText("Punkte:  " + points)
        val mp: MediaPlayer = MediaPlayer.create(this, R.raw.oohgameover)
        mp.start()
    }

    fun restart(view: View){
        val intent = Intent(this, StartGameActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun mainMenu(view : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}