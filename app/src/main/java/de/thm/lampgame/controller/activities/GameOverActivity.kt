package de.thm.lampgame.controller.activities

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.model.PlayerModel

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        val points = intent.extras!!.getInt("POINTS")
        val viewPoints: TextView = findViewById(R.id.points)
        val viewHighscore: TextView = findViewById(R.id.highscore)

        viewPoints.text = getString(R.string.pointsValues, points)
        val mp: MediaPlayer = MediaPlayer.create(this, R.raw.death_sound)
        mp.start()

        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val highScore = settings.getInt("HIGH_SCORE", 0)

        if (points > highScore) {
            viewHighscore.text = getString(R.string.highScoreValues, points)
            val editor = settings.edit()
            editor.putInt("HIGH_SCORE", points)
            editor.apply()
        } else {
            viewHighscore.text = getString(R.string.highScoreValues, highScore)
        }

    }

    fun restart(view: View) {
        val intent = Intent(this, StartGameActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun mainMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onPause() {
        super.onPause()
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("coins", PlayerModel.coins)
        editor.apply()
    }
}