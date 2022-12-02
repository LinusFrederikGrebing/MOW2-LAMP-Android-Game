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
import de.thm.lampgame.controller.helper.LocaleHelper
import de.thm.lampgame.model.shop.Database


class SettingsActivity : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null
    private var music: Int = 0
    private val localHelper = LocaleHelper()
    private var buttonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        localHelper.getAndSetPersistedLanguageData(this)
        musicVolume()
        volumeTest()
    }

    fun musicVolume() {
        //volume slider
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
    }

    private fun volumeTest() {
        //button plays music to test volume
        val buttonTest: Button = findViewById<View>(R.id.playButton) as Button
        Database.listOfMusic.forEach {
            if (it.itemInfo.active) {
                music = it.song
            }
        }

        buttonTest.setOnClickListener {
            buttonClicked = if (!buttonClicked) {
                mediaPlayer = MediaPlayer.create(this, music); mediaPlayer?.start(); true
            } else {
                mediaPlayer?.stop(); false
            }
        }
    }

    fun mainMenu(view: View) {
        mediaPlayer?.stop()
        finish()
    }


    fun changeLang(view: View) {
        localHelper.setLocale(this, "en")
        this.recreate()
    }

    fun changeLangDe(view: View) {
        localHelper.setLocale(this, "de")
        this.recreate()
    }



}