package de.thm.lampgame.controller.Activitys

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import de.thm.lampgame.R
import de.thm.lampgame.controller.ItemsAdapter
import de.thm.lampgame.controller.Skins.BlueLampSkin
import de.thm.lampgame.controller.Skins.LampSkin
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MarsLandscapeMap
import de.thm.lampgame.databinding.ShopActivityLayoutBinding
import de.thm.lampgame.model.DataItem
import de.thm.lampgame.model.Database
import de.thm.lampgame.model.PlayerModel


class ShopActivity : AppCompatActivity(), ItemsAdapter.OnItemClickListener {
    private lateinit var binding: ShopActivityLayoutBinding
    private val adapterList by lazy { ItemsAdapter(this) }
    var shop: Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    fun shopMusik(view: View) {
        shop = 3
        ItemsAdapter.itemList = Database.getItemsMusik()
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

    fun lockedCase(locked: DataItem.Locked) {
        if (locked.text == CemeteryLandscapeMap.name) {
            if (PlayerModel.coins >= locked.price.toInt()) {
                PlayerModel.coins -= locked.price.toInt()
                CemeteryLandscapeMap.buyStatus = true
                getRightList()
                toast("Gekauft!")
            } else {
                toast("Nicht genügend Coins vorhanden!")
            }
        } else if(locked.text == BlueLampSkin.name) {
            if (PlayerModel.coins >= locked.price.toInt()) {
                PlayerModel.coins -= locked.price.toInt()
                BlueLampSkin.buyStatus = true
                getRightList()
                toast("Gekauft!")
            }
            else  {
                toast("Nicht genügend Coins vorhanden!")
            }
        }

        else if(locked.text == MarsLandscapeMap.name) {
            if (PlayerModel.coins >= locked.price.toInt()) {
                PlayerModel.coins -= locked.price.toInt()
                MarsLandscapeMap.buyStatus = true
                getRightList()
                toast("Gekauft!")
            }
        }

        else {
            toast("Nicht zu kaufen!")
        }
    }

    fun unlockedCase(unlocked: DataItem.Unlocked) {
        Database.listOfMaps.forEach {
            it.active = unlocked.text == it.name
        }
        getRightList()
        toast("${unlocked.text} ist jetzt aktiv!")

        if(unlocked.text == BlueLampSkin.name) {
            LampSkin.active = false
            BlueLampSkin.active = true
            getRightList()
            toast("${unlocked.text} ist jetzt aktiv!")
            } else if (unlocked.text == LampSkin.name ) {
            LampSkin.active = true
            BlueLampSkin.active = false
            getRightList()
            toast("${unlocked.text} ist jetzt aktiv!")
        }


    }

    fun activeCase(active: DataItem.Active) {
        toast("${active.text} schon aktiv!")
    }

    fun getRightList(){
        ItemsAdapter.itemList = if (shop == 1) Database.getItemsMaps() else if (shop == 2) Database.getItemsSkins() else Database.getItemsMusik()
        inflateList()
    }

    fun toast(text: String){
        Toast.makeText(
            this, text,
            Toast.LENGTH_LONG
        ).show()
    }


    private fun setPlayerCoinsTextView() {
        val playerCoins = findViewById<TextView>(R.id.playercoinstv)
        playerCoins.text = PlayerModel.coins.toString()
    }
}