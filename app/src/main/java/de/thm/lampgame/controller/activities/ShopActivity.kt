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
import de.thm.lampgame.model.PlayerModel
import de.thm.lampgame.model.shop.DataItem
import de.thm.lampgame.model.shop.Database

class ShopActivity : AppCompatActivity(), ItemsAdapter.OnItemClickListener {
    private lateinit var binding: ShopActivityLayoutBinding
    private val adapterList by lazy { ItemsAdapter(this) }
    var shop: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ItemsAdapter.itemList = Database.getItemsMaps()
        inflateList()
        setPlayerTorchesTextView()
    }

    private fun inflateList() {
        binding = ShopActivityLayoutBinding.inflate(layoutInflater)

        binding.recyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@ShopActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterList
        }
        setContentView(binding.root)
    }


    fun mainMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    // puts the shop on maps
    fun shopMaps(view: View) {
        shop = 1
        ItemsAdapter.itemList = Database.getItemsMaps()
        inflateList()
        setPlayerTorchesTextView()
    }

    // sets the shop to skins
    fun shopSkins(view: View) {
        shop = 2
        ItemsAdapter.itemList = Database.getItemsSkins()
        inflateList()
        setPlayerTorchesTextView()
    }

    // puts the shop on music
    fun shopMusic(view: View) {
        shop = 3
        ItemsAdapter.itemList = Database.getItemsMusic()
        inflateList()
        setPlayerTorchesTextView()
    }

    // there are three different cases to handle an item click:
    // in the first case, the item that was clicked on is of the Locked type
    // in the second case, the item is of the type Unlocked
    // in the third case, the item is of type Active
    // depending on the type, a different case should occur
    override fun onItemClick(position: Int) {
        if (ItemsAdapter.itemList[position] is DataItem.Locked) {
            lockedCase(ItemsAdapter.itemList[position] as DataItem.Locked)
        } else if (ItemsAdapter.itemList[position] is DataItem.Unlocked) {
            unlockedCase(ItemsAdapter.itemList[position] as DataItem.Unlocked)
        } else {
            activeCase()
        }

        // at the end, the player's current currency needs to be updated
        setPlayerTorchesTextView()
    }

    private fun lockedCase(locked: DataItem.Locked) {
        // the variable locked represents the currently clicked item

        // depending on which shop is active, different item lists are run through
        when (shop) {
            1 -> Database.listOfMaps.forEach {
                // if the name of the item in the list position matches the name of the clicked item and the player has enough coins available, the item will be bought.
                // The MapIdentifier in combination with the attribute name results in the identifier for the SharedPreferences, in which the new status of the item is entered
                if (locked.text == it.itemInfo.name && PlayerModel.torches >= it.itemInfo.price.toInt()) {
                    PlayerModel.torches -= it.itemInfo.price.toInt()
                    it.itemInfo.buyStatus = true
                    putSharedPref(
                        (it.itemInfo.name).toString() + "BuyStatus",
                        it.itemInfo.buyStatus
                    )
                }
            }
            2 -> Database.listOfSkins.forEach {
                if (locked.text == it.itemInfo.name && PlayerModel.torches >= it.itemInfo.price.toInt()) {
                    PlayerModel.torches -= it.itemInfo.price.toInt()
                    it.itemInfo.buyStatus = true
                    putSharedPref(
                        (it.itemInfo.name).toString() + "BuyStatus",
                        it.itemInfo.buyStatus
                    )
                }
            }
            3 -> Database.listOfMusic.forEach {
                if (locked.text == it.itemInfo.name && PlayerModel.torches >= it.itemInfo.price.toInt()) {
                    PlayerModel.torches -= it.itemInfo.price.toInt()
                    it.itemInfo.buyStatus = true
                    putSharedPref(
                        (it.itemInfo.name).toString() + "BuyStatus",
                        it.itemInfo.buyStatus
                    )
                }
            }
        }
        // updates the item list and returns the list of the active shop
        getRightList()
    }

    private fun unlockedCase(unlocked: DataItem.Unlocked) {
        // the variable unlocked represents the currently clicked item
        when (shop) {
            // if the name of the clicked item matches one of the ListItems,
            // then active is set to true for this item, all others are set to false
            1 -> Database.listOfMaps.forEach {
                it.itemInfo.active = unlocked.text == it.itemInfo.name
                putSharedPref((it.itemInfo.name).toString() + "Active", it.itemInfo.active)
            }
            2 -> Database.listOfSkins.forEach {
                it.itemInfo.active = unlocked.text == it.itemInfo.name
                putSharedPref((it.itemInfo.name).toString() + "Active", it.itemInfo.active)
            }
            3 -> Database.listOfMusic.forEach {
                it.itemInfo.active = unlocked.text == it.itemInfo.name
                putSharedPref((it.itemInfo.name).toString() + "Active", it.itemInfo.active)
            }
        }
        // updates the item list and returns the list of the active shop
        getRightList()
    }

    private fun activeCase() {
        when (shop) {
            // creates a small notification that the clicked card is already active
            1 -> createToast(getString(R.string.mapAlreadyActive))
            2 -> createToast(getString(R.string.skinAlreadyActive))
            3 -> createToast(getString(R.string.musicAlreadyActive))
        }
    }

    // updates the item list and returns the list of the active shop
    private fun getRightList() {
        ItemsAdapter.itemList =
            if (shop == 1) Database.getItemsMaps() else if (shop == 2) Database.getItemsSkins() else Database.getItemsMusic()
        inflateList()
    }

    // template to issue a small notification
    private fun createToast(text: String) {
        Toast.makeText(
            this, text,
            Toast.LENGTH_LONG
        ).show()
    }

    // sets the player's current coins
    private fun setPlayerTorchesTextView() {
        val playerTorches = findViewById<TextView>(R.id.playertorchestv)
        playerTorches.text = PlayerModel.torches.toString()
    }

    // template to store the item attributes buyStatus and active in the SharedPreferences
    private fun putSharedPref(identifier: String, value: Boolean) {
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putBoolean(identifier, value)
        editor.apply()
    }

    override fun onPause() {
        super.onPause()
        // when the shop is exited, the player's new coins should be saved
        val settings = getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor = settings.edit()
        editor.putInt("coins", PlayerModel.torches)
        editor.apply()
    }
}