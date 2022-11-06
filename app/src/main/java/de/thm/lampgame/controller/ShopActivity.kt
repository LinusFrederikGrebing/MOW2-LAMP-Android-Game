package de.thm.lampgame.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import de.thm.lampgame.ItemAdapter
import de.thm.lampgame.ItemList
import de.thm.lampgame.R
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap


class ShopActivity : AppCompatActivity() , OnItemClickListener{
    private var listView:ListView? = null
    private var itemAdapter: ItemAdapter ? = null
    private var arrayList:ArrayList<ItemList>? = null
    var listItem:ArrayList<ItemList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_layout)

        listView = findViewById(R.id.cardview_List_view)
        arrayList = ArrayList()
        arrayList = setDataItem()
        itemAdapter = ItemAdapter(applicationContext, arrayList!!)
        listView?.adapter = itemAdapter
        listView?.onItemClickListener = this
    }

    private fun setDataItem() : ArrayList<ItemList>{

        listItem.add(ItemList(R.drawable.bergeicon, MountainLandscapeMap.name, ""))
        listItem.add(ItemList(R.drawable.cemetery, CemeteryLandscapeMap.name,  ""))


        return listItem
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        val items: ItemList = arrayList?.get(position)!!
        if(items.title == CemeteryLandscapeMap.name) {
            CemeteryLandscapeMap.active = true
            MountainLandscapeMap.active = false
        }
        else if(items.title == MountainLandscapeMap.name){
            MountainLandscapeMap.active = true
            CemeteryLandscapeMap.active = false
        }
    }

    fun mainMenu(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

}