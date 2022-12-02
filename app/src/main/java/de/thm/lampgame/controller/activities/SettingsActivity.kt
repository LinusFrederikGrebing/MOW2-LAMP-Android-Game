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
    private val localHelper = LocaleHelper() // needed to change the language during the runtime
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        localHelper.getAndSetPersistedLanguageData(this)
        musicVolume()
        volumeTest()
    }

    private fun musicVolume() {
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
        var music = 0
        var buttonClicked = false

        // choose the current music
        Database.listOfMusic.forEach {
            if (it.itemInfo.active) {
                music = it.song
            }
        }

        // if the buttonClicked is true, start the test music, otherwise stop the test music
        val buttonTest: Button = findViewById<View>(R.id.playButton) as Button
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

    // use the helper to set the game language to english and restart the SettingsActivity
    fun changeLang(view: View) {
        localHelper.setLocale(this, "en")
        this.recreate()
    }

    // use the helper to set the game language to english and restart the SettingsActivity
    fun changeLangDe(view: View) {
        localHelper.setLocale(this, "de")
        this.recreate()
    }



}