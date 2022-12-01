package de.thm.lampgame.controller.activities

import android.content.Context
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


class  SettingsActivity : AppCompatActivity() {
    private var mp: MediaPlayer? = null
    private var music: Int = 0
    val localHelper = LocaleHelper()
    var buttonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)
        getPersistedLanguageData()
        musikVolumeTest()
    }

    fun musikVolumeTest(){
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
            if (it.itemInfo.active) {
                music = it.song
            }
        }

        buttonTest.setOnClickListener {
            buttonClicked = if(!buttonClicked) {  mp = MediaPlayer.create(this, music); mp?.start() ; true
            } else { mp?.stop() ; false }
        }

    }

    fun mainMenu(view: View) {
        mp?.stop()
        finish()
    }

    fun getPersistedLanguageData() {
        val settings = getSharedPreferences("NEW-DATA", Context.MODE_PRIVATE)
        println((settings.getString("SELECTED_LANGUAGE", "en")))
        localHelper.setLocale(this, (settings.getString("SELECTED_LANGUAGE", "en")))
    }

    fun changeLang(view: View) {
        saveNewLanguage("en")
        localHelper.setLocale(this, "en")
        this.recreate()
    }

    fun changeLangDe(view: View) {
        saveNewLanguage("de")
        localHelper.setLocale(this, "de")
        this.recreate()
    }

    fun saveNewLanguage(language: String){
        val settings = getSharedPreferences("NEW-DATA", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putString("SELECTED_LANGUAGE", language)
        editor.apply()
    }

}