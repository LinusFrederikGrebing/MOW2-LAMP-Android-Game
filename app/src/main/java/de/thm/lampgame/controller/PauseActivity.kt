package de.thm.lampgame.controller


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R

class PauseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pause)
    }

    fun buttonContinue(view: View?){
        finish()
    }

    fun buttonHome(view: View?){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun buttonSettings(view: View?){
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun buttonRestart(view: View?){
        val intent = Intent(this, StartGameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}