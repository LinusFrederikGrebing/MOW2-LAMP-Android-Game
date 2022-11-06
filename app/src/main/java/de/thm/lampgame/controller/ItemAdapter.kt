package de.thm.lampgame.controller

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import de.thm.lampgame.R
import de.thm.lampgame.model.ItemList

class ItemAdapter(var context : Context, var arrayList: ArrayList<ItemList>) : BaseAdapter() {
    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Any {
       return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
       return position.toLong()
    }

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val view:View = View.inflate(context, R.layout.shop_view_item_layout, null)

        val icons:ImageView = view.findViewById(R.id.map_list)
        val title:TextView = view.findViewById(R.id.title_text_view)
        val detail:TextView = view.findViewById(R.id.detail_text_view)

        val items: ItemList = arrayList[position]

        icons.setImageResource(items.icons!!)
        title.text = items.title
        detail.text = items.detail

        return view
    }
}