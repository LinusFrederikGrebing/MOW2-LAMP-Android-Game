package de.thm.lampgame.controller.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.controller.helper.LocaleHelper
import de.thm.lampgame.model.PlayerModel
import de.thm.lampgame.model.shop.Database

class MainActivity : AppCompatActivity() {
    val localHelper = LocaleHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainmenu)

        // overwrite everything with the saved data
        getAndSetPersistedCoinsAndHighscoreData()
        getAndSetPersistedShopItemData()
        getAndSetPersistedLanguageData()
    }

    fun getAndSetPersistedCoinsAndHighscoreData(){
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val playerCoins = findViewById<TextView>(R.id.playercoinstv)
        val viewHighscore: TextView = findViewById(R.id.highscore)

        val highScore = settings.getInt("HIGH_SCORE", 0)
        PlayerModel.coins = settings.getInt("coins", PlayerModel.coins)

        viewHighscore.text = getString(R.string.highScoreValues, highScore)
        playerCoins.text = PlayerModel.coins.toString()
    }

    fun getAndSetPersistedShopItemData() {
        // check which value for the attributes buystatus and active is saved for the respective item in the shared preferences, if no value was saved, take the default value from the database
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        Database.listOfMusic.forEach {
            it.itemInfo.buyStatus = settings.getBoolean((it.itemInfo.name).toString() + "BuyStatus",it.itemInfo.buyStatus)
            it.itemInfo.active = settings.getBoolean((it.itemInfo.name).toString() + "Active",it.itemInfo.active)
        }
        Database.listOfMaps.forEach {
            it.itemInfo.buyStatus = settings.getBoolean((it.itemInfo.name).toString() + "BuyStatus",it.itemInfo.buyStatus)
            it.itemInfo.active = settings.getBoolean((it.itemInfo.name).toString() + "Active",it.itemInfo.active)
        }
        Database.listOfSkins.forEach {
            it.itemInfo.buyStatus = settings.getBoolean((it.itemInfo.name).toString() + "BuyStatus",it.itemInfo.buyStatus)
            it.itemInfo.active = settings.getBoolean((it.itemInfo.name).toString() + "Active",it.itemInfo.active)
        }
    }

    fun getAndSetPersistedLanguageData() {
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        println((settings.getString("SELECTED_LANGUAGE", "en")))
        localHelper.setLocale(this, (settings.getString("SELECTED_LANGUAGE", "en")))
    }

    fun setloadingScreen(){
        setContentView(R.layout.loadingscreenlayout)
        val tippView: TextView = findViewById(R.id.textViewTipp)
        val text =  when ((1 .. 4).random()) {
            1 -> getString(R.string.tipp1)
            2 -> getString(R.string.tipp2)
            3 -> getString(R.string.tipp3)
            4 -> getString(R.string.tipp4)
           else -> { getString(R.string.tippnotfound) }
        }
        tippView.text = text
    }

    fun startGame(view: View?) {
        setloadingScreen()
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

    override fun onPause() {
        super.onPause()
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("coins", PlayerModel.coins)
        editor.apply()
    }
}