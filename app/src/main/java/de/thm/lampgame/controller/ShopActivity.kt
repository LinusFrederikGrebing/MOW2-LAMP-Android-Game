package de.thm.lampgame.controller

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


