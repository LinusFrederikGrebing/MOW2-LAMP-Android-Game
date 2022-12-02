package de.thm.lampgame.controller.activities

import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.controller.helper.LoadingScreenHelper
import de.thm.lampgame.model.PlayerModel

class GameOverActivity : AppCompatActivity() {
    private val loadingScreenHelper = LoadingScreenHelper() // use the loadingScreenHelper to get possible tip texts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        playDeathSound()
        setPointsAndHighScore()
    }

    private fun setPointsAndHighScore(){
        val viewPoints: TextView = findViewById(R.id.points)
        val viewHighscore: TextView = findViewById(R.id.highscore)
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)

        if(intent != null){
            if(intent.extras != null){
                val points = intent.extras!!.getInt("POINTS", 0)

                // set the currently achieved points
                viewPoints.text = getString(R.string.pointsValuesDecimal, points)

                // if the current score is higher than the saved high score, overwrite the old high score
                if (points > (settings.getInt("HIGH_SCORE", 0))) {
                    val editor = settings.edit()
                    editor.putInt("HIGH_SCORE", points)
                    editor.apply()
                }
                // set the value saved under HighScore
                viewHighscore.text = getString(R.string.highScoreValues, settings.getInt("HIGH_SCORE", 0))
            }
        }
    }

    // play the short gameover music sequence
    private fun playDeathSound(){
        val mp: MediaPlayer = MediaPlayer.create(this, R.raw.death_sound)
        mp.start()
    }

    fun restart(view: View) {
        // for the loading screen
        setContentView(R.layout.loadingscreenlayout)
        val tipView: TextView = findViewById(R.id.textViewTipp)
        tipView.text = loadingScreenHelper.getLoadingScreenText(this)

        // restart the game
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
        // save the player conis when leaving the gameover activity
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("coins", PlayerModel.torches)
        editor.apply()
    }
}