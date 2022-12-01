package de.thm.lampgame.controller.activities

import android.annotation.SuppressLint
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
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainmenu)



        val playerCoins = findViewById<TextView>(R.id.playercoinstv)

        val viewHighscore: TextView = findViewById(R.id.highscore)
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val highScore = settings.getInt("HIGH_SCORE", 0)
        viewHighscore.text = getString(R.string.highScoreValues, highScore)


        println("create" + settings.getInt("coins", PlayerModel.coins))
        PlayerModel.coins = settings.getInt("coins", PlayerModel.coins)
        println("create" + settings.getInt("coins", PlayerModel.coins))
        playerCoins.text = PlayerModel.coins.toString()
        // check which value for the attributes buystatus and active is saved for the respective item in the shared preferences, if no value was saved, take the default value from the database
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
        getPersistedLanguageData()
    }

    fun getPersistedLanguageData() {
        val settings = getSharedPreferences("NEW-DATA", Context.MODE_PRIVATE)
        println((settings.getString("SELECTED_LANGUAGE", "en")))
        localHelper.setLocale(this, (settings.getString("SELECTED_LANGUAGE", "en")))
    }


    fun startGame(view: View?) {
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