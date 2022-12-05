package de.thm.lampgame.controller.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.R
import de.thm.lampgame.controller.helper.LoadingScreenHelper
import de.thm.lampgame.controller.helper.LocaleHelper
import de.thm.lampgame.model.PlayerModel
import de.thm.lampgame.model.shop.Database

class MainActivity : AppCompatActivity() {
    private val localHelper = LocaleHelper() // needed to change the language during the runtime
    private val loadingScreenHelper = LoadingScreenHelper()  // use the loadingScreenHelper to get possible tip texts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mainmenu)
        // overwrite everything with the saved data
        getAndSetPersistedTorchesAndHighscoreData()
        getAndSetPersistedShopItemData()

    }

    override fun onResume() {
        super.onResume()
        // sets the currently saved game language
        localHelper.getAndSetPersistedLanguageData(this)
    }

    private fun getAndSetPersistedTorchesAndHighscoreData() {
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val playerTorches = findViewById<TextView>(R.id.playertorchestv)
        val viewHighscore: TextView = findViewById(R.id.highscore)
        val highScore = settings.getInt("HIGH_SCORE", 0)
        PlayerModel.torches = settings.getInt("coins", PlayerModel.torches)


        viewHighscore.text = getString(R.string.highScoreValues, highScore)
        playerTorches.text = PlayerModel.torches.toString()
    }

    private fun getAndSetPersistedShopItemData() {
        // check which value for the attributes buyStatus and active is saved for the respective item in the shared preferences, if no value was saved, take the default value from the database
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        Database.listOfMusic.forEach {
            it.itemInfo.buyStatus = settings.getBoolean(
                (it.itemInfo.name).toString() + "BuyStatus",
                it.itemInfo.buyStatus
            )
            it.itemInfo.active =
                settings.getBoolean((it.itemInfo.name).toString() + "Active", it.itemInfo.active)
        }
        Database.listOfMaps.forEach {
            it.itemInfo.buyStatus = settings.getBoolean(
                (it.itemInfo.name).toString() + "BuyStatus",
                it.itemInfo.buyStatus
            )
            it.itemInfo.active =
                settings.getBoolean((it.itemInfo.name).toString() + "Active", it.itemInfo.active)
        }
        Database.listOfSkins.forEach {
            it.itemInfo.buyStatus = settings.getBoolean(
                (it.itemInfo.name).toString() + "BuyStatus",
                it.itemInfo.buyStatus
            )
            it.itemInfo.active =
                settings.getBoolean((it.itemInfo.name).toString() + "Active", it.itemInfo.active)
        }
    }



    fun startGame(view: View?) {
        // for the loading screen
        setContentView(R.layout.loadingscreenlayout)
        val tipView: TextView = findViewById(R.id.textViewTip)
        tipView.text = loadingScreenHelper.getLoadingScreenText(this)

        // start the game
        val intent = Intent(this, StartGameActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun buttonSettings(view: View?) {
        val intent = Intent(this, SettingsActivity::class.java)
        startActivity(intent)
    }

    fun buttonShop(view: View?) {
        val intent = Intent(this, ShopActivity::class.java)
        startActivity(intent)
        finish()
    }
}