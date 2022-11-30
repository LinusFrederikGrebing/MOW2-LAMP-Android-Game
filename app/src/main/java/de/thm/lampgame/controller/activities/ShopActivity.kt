package de.thm.lampgame.controller.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.thm.lampgame.R
import de.thm.lampgame.controller.ItemsAdapter
import de.thm.lampgame.databinding.ShopActivityLayoutBinding
import de.thm.lampgame.model.shop.DataItem
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.PlayerModel


class ShopActivity : AppCompatActivity(), ItemsAdapter.OnItemClickListener {
    private lateinit var binding: ShopActivityLayoutBinding
    private val adapterList by lazy { ItemsAdapter(this) }
    var shop: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ItemsAdapter.itemList = Database.getItemsMaps()
        inflateList()
        setPlayerCoinsTextView()
    }

    private fun inflateList() {
        binding = ShopActivityLayoutBinding.inflate(layoutInflater)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ShopActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterList
        }
        setContentView(binding.root)
    }


    fun mainMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    fun shopMaps(view: View) {
        shop = 1
        ItemsAdapter.itemList = Database.getItemsMaps()
        inflateList()
        setPlayerCoinsTextView()
    }

    fun shopSkins(view: View) {
        shop = 2
        ItemsAdapter.itemList = Database.getItemsSkins()
        inflateList()
        setPlayerCoinsTextView()
    }

    fun shopMusic(view: View) {
        shop = 3
        ItemsAdapter.itemList = Database.getItemsMusic()
        inflateList()
        setPlayerCoinsTextView()
    }

    override fun onItemClick(position: Int) {
        if (ItemsAdapter.itemList[position] is DataItem.Locked) {
            lockedCase(ItemsAdapter.itemList[position] as DataItem.Locked)
        } else if (ItemsAdapter.itemList[position] is DataItem.Unlocked) {
            unlockedCase(ItemsAdapter.itemList[position] as DataItem.Unlocked)
        } else {
            activeCase(ItemsAdapter.itemList[position] as DataItem.Active)
        }

        setPlayerCoinsTextView()
    }

    private fun lockedCase(locked: DataItem.Locked) {

        when (shop) {
            1 -> Database.listOfMaps.forEach {
                if (locked.text == it.itemInfo.name && PlayerModel.coins >= it.itemInfo.price.toInt()) {
                    PlayerModel.coins -= it.itemInfo.price.toInt()
                    it.itemInfo.buyStatus = true
                    putSharedPref((it.itemInfo.name).toString() + "BuyStatus", it.itemInfo.buyStatus)
                }
            }
            2 -> Database.listOfSkins.forEach {
                if (locked.text == it.itemInfo.name && PlayerModel.coins >= it.itemInfo.price.toInt()) {
                    PlayerModel.coins -= it.itemInfo.price.toInt()
                    it.itemInfo.buyStatus = true
                    putSharedPref((it.itemInfo.name).toString() + "BuyStatus", it.itemInfo.buyStatus)
                }
            }
            3 -> Database.listOfMusic.forEach {
                if (locked.text == it.itemInfo.name && PlayerModel.coins >= it.itemInfo.price.toInt()) {
                    PlayerModel.coins -= it.itemInfo.price.toInt()
                    it.itemInfo.buyStatus = true
                    putSharedPref((it.itemInfo.name).toString() + "BuyStatus", it.itemInfo.buyStatus)
                }
            }
        }
        getRightList()
    }

    private fun unlockedCase(unlocked: DataItem.Unlocked) {
        when(shop) {
            1 -> Database.listOfMaps.forEach {
                it.itemInfo.active = unlocked.text == it.itemInfo.name
                putSharedPref((it.itemInfo.name).toString() + "Active",it.itemInfo.active)
            }
            2 -> Database.listOfSkins.forEach {
                it.itemInfo.active = unlocked.text == it.itemInfo.name
                putSharedPref((it.itemInfo.name).toString() + "Active",it.itemInfo.active)
            }
            3 -> Database.listOfMusic.forEach {
                it.itemInfo.active = unlocked.text == it.itemInfo.name
                putSharedPref((it.itemInfo.name).toString() + "Active",it.itemInfo.active)
            }
        }
        getRightList()
    }

    private fun activeCase(active: DataItem.Active) {
        toast(getString(R.string.alreadyActive, "${active.text}"))
    }

    private fun getRightList(){
        ItemsAdapter.itemList = if (shop == 1) Database.getItemsMaps() else if (shop == 2) Database.getItemsSkins() else Database.getItemsMusic()
        inflateList()
    }

    private fun toast(text: String){
        Toast.makeText(
            this, text,
            Toast.LENGTH_LONG
        ).show()
    }

    private fun setPlayerCoinsTextView() {
        val playerCoins = findViewById<TextView>(R.id.playercoinstv)
        playerCoins.text = PlayerModel.coins.toString()
    }

    private fun putSharedPref(identifier: String, value: Boolean) {
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putBoolean(identifier,value)
        editor.apply()
    }

    override fun onPause() {
        super.onPause()
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("coins", PlayerModel.coins)
        editor.apply()
    }
}