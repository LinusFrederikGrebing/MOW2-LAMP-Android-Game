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
import de.thm.lampgame.controller.maps.MountainLandscapeMap
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

    fun shop1(view: View) {
        shop = 1
        ItemsAdapter.itemList = Database.getItemsMaps()
        inflateList()
        setPlayerCoinsTextView()
    }

    fun shop2(view: View) {
        shop = 2
        ItemsAdapter.itemList = Database.getItemsSkins()
        inflateList()
        setPlayerCoinsTextView()
    }

    fun shop3(view: View) {
        shop = 3
        ItemsAdapter.itemList = Database.getItemsItems()
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
        ItemsAdapter.itemList = if (shop == 1) Database.getItemsMaps() else if (shop == 2) Database.getItemsSkins() else Database.getItemsItems()
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


/*
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import de.thm.lampgame.model.Items
import de.thm.lampgame.R
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap



class ShopActivity : AppCompatActivity(), ItemsAdapter.OnItemClickListener {
    private var exampleList = generateDummyList()
    private var exampleList2 = generateDummyList2()
    private var exampleList3 = generateDummyList3()
    private val adapter = ItemsAdapter(exampleList, this)
    private val adapter2 = ItemsAdapter(exampleList2, this)
    private val adapter3 = ItemsAdapter(exampleList3, this)
    var actualList = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_activity_layout)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
    }


    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = if(actualList == 1) exampleList[position] else if(actualList == 2) exampleList2[position] else exampleList3[position]
        val adapter = if(actualList == 1) adapter else if(actualList == 2) adapter2 else adapter3
        if(clickedItem.itemName == CemeteryLandscapeMap.name) {
            clickedItem.itemName = "Clicked"
            CemeteryLandscapeMap.active = true
            MountainLandscapeMap.active = false
        }
        else if(clickedItem.itemName == MountainLandscapeMap.name){
            clickedItem.itemName = "Clicked"
            MountainLandscapeMap.active = true
            CemeteryLandscapeMap.active = false
        }
        adapter.notifyItemChanged(position)
    }

    private fun generateDummyList(): ArrayList<Items> {

        val list = ArrayList<Items>()

        list.add(Items(R.drawable.bergeicon, MountainLandscapeMap.name, true))
        list.add(Items(R.drawable.cemetery, CemeteryLandscapeMap.name, false))
        list.add(Items(R.drawable.bergeicon, MountainLandscapeMap.name, true))
        list.add(Items(R.drawable.cemetery, CemeteryLandscapeMap.name, false))

        return list
    }

    private fun generateDummyList2(): ArrayList<Items> {

        val list = ArrayList<Items>()

        list.add(Items(R.drawable.bergeicon, MountainLandscapeMap.name, true))
        list.add(Items(R.drawable.bergeicon, MountainLandscapeMap.name, false))
        list.add(Items(R.drawable.bergeicon, MountainLandscapeMap.name, true))
        list.add(Items(R.drawable.bergeicon, MountainLandscapeMap.name, false))

        return list
    }

    private fun generateDummyList3(): ArrayList<Items> {

        val list = ArrayList<Items>()

        list.add(Items(R.drawable.cemetery, CemeteryLandscapeMap.name, true))
        list.add(Items(R.drawable.cemetery, CemeteryLandscapeMap.name, false))
        list.add(Items(R.drawable.cemetery, CemeteryLandscapeMap.name, true))
        list.add(Items(R.drawable.cemetery, CemeteryLandscapeMap.name, false))
        list.add(Items(R.drawable.cemetery, CemeteryLandscapeMap.name, false))
        list.add(Items(R.drawable.cemetery, CemeteryLandscapeMap.name, false))

        return list
    }

    fun mainMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun shop1(view: View) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        actualList = 1
    }
    fun shop2(view: View) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter2
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        actualList = 2
    }
    fun shop3(view: View) {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter3
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        actualList = 3
    }
}
*/

