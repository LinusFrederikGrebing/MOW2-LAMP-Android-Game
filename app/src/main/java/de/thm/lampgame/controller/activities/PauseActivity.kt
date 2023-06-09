package de.thm.lampgame.controller.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.controller.helper.LoadingScreenHelper

class PauseActivity : AppCompatActivity() {
    private val loadingScreenHelper = LoadingScreenHelper() // use the loadingScreenHelper to get possible tip texts

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pause)
    }

    fun buttonContinue(view: View?) {
        finish()
    }

    fun buttonHome(view: View?) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun buttonSettings(view: View?) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun buttonRestart(view: View?) {
        // for the loading screen
        setContentView(R.layout.loadingscreenlayout)
        val tipView: TextView = findViewById(R.id.textViewTip)
        tipView.text = loadingScreenHelper.getLoadingScreenText(this)

        // restart the game
        val intent = Intent(this, StartGameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }


}