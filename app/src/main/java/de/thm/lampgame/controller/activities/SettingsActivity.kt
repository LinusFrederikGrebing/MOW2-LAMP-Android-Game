package de.thm.lampgame.controller.activities

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database


class SettingsActivity : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    var music: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        //Lautstärkeregler
        val audioManager = getSystemService(AUDIO_SERVICE) as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        val curVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        val volControl = findViewById<View>(R.id.volbar) as SeekBar
        volControl.max = maxVolume
        volControl.progress = curVolume
        volControl.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(arg0: SeekBar) {}
            override fun onStartTrackingTouch(arg0: SeekBar) {}
            override fun onProgressChanged(arg0: SeekBar, arg1: Int, arg2: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0)
            }
        })

        //Testmusik zum Testen der Lautstärke
        val buttonTest: Button = findViewById<View>(R.id.playButton) as Button
        Database.listOfMusic.forEach {
            if (it.mapInfo.active) {
                music = it.song
            }
        }
        mp = MediaPlayer.create(this, music)
        buttonTest.setOnClickListener { mp?.start() }

    }

    fun mainMenu(view: View) {
        mp?.stop()
        finish()
    }
}