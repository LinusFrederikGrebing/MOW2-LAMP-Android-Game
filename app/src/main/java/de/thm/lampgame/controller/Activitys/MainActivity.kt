package de.thm.lampgame.controller.Activitys

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.model.PlayerModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainmenu)
        val playerCoins = findViewById<TextView>(R.id.playercoinstv)
        playerCoins.text = PlayerModel.coins.toString()
        val viewHighscore: TextView = findViewById<TextView>(R.id.highscore)
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val highScore = settings.getInt("HIGH_SCORE", 0)
        viewHighscore.text = "High Score: $highScore"
    }

    fun startGame(view: View?) {
        val intent = Intent(this, StartGameActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun buttonSettings(view: View?) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun shop(view: View?) {
        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
        finish()
    }
}