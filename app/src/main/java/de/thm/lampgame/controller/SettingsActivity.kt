package de.thm.lampgame.controller

import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.net.Uri

import de.thm.lampgame.R

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        val seek = findViewById<SeekBar>(R.id.volbar)
        val audioManager: AudioManager

    }
}