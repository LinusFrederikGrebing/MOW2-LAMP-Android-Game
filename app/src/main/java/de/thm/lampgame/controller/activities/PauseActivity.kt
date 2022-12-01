package de.thm.lampgame.controller.activities


import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
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
        setloadingScreen()
        val intent = Intent(this, StartGameActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    fun setloadingScreen(){
        setContentView(R.layout.loadingscreenlayout)
        val tippView: TextView = findViewById(R.id.textViewTipp)
        val text = when ((1 .. 4).random()) {
            1 -> getString(R.string.tip1)
            2 -> getString(R.string.tip2)
            3 -> getString(R.string.tip3)
            4 -> getString(R.string.tip4)
            else -> { getString(R.string.tipNotFound) }
        }
        tippView.text = text
    }
}