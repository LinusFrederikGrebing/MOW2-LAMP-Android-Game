package de.thm.lampgame

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView

class GameOver : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        val points = intent.extras!!.getInt("POINTS")
        val viewPoints: TextView = findViewById(R.id.points) as TextView
        viewPoints.setText("Punkte:  " + points)
    }

    fun restart(view: View){
        val intent = Intent(this, StartGame::class.java)
        startActivity(intent)
        finish()
    }

    fun mainMenu(view : View){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }
}