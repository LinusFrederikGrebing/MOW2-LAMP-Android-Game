package de.thm.lampgame

import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap

object Database {
    const val LOCKED_TYPE = 0
    const val UNLOCKED_TYPE = 1
    const val ACTIVE_TYPE = 2

    fun getItemsMaps() : ArrayList<DataItem>{
        val itemList = arrayListOf<DataItem>()

        if(MountainLandscapeMap.active){
            itemList.add(DataItem.Active(R.drawable.bergeicon, MountainLandscapeMap.name))
        } else {
            itemList.add(DataItem.Unlocked(R.drawable.bergeicon, MountainLandscapeMap.name))
        }

         if(!CemeteryLandscapeMap.buyStatus){
            itemList.add(DataItem.Locked(R.drawable.cemetery, CemeteryLandscapeMap.name, "35"))
        } else {
             if(CemeteryLandscapeMap.active){
                 itemList.add(DataItem.Active(R.drawable.cemetery, CemeteryLandscapeMap.name))
             } else {
                 itemList.add(DataItem.Unlocked(R.drawable.cemetery, CemeteryLandscapeMap.name))
             }
        }

        return itemList

    }

    fun getItemsSkins() : ArrayList<DataItem>{
        val itemList = arrayListOf<DataItem>()
        if(MountainLandscapeMap.active){
            itemList.add(DataItem.Active(R.drawable.bergeicon, MountainLandscapeMap.name))
        } else {
            itemList.add(DataItem.Unlocked(R.drawable.bergeicon, MountainLandscapeMap.name))
        }

        if(!CemeteryLandscapeMap.buyStatus){
            itemList.add(DataItem.Locked(R.drawable.cemetery, CemeteryLandscapeMap.name, "70"))
        } else {
            if(CemeteryLandscapeMap.active){
                itemList.add(DataItem.Active(R.drawable.cemetery, CemeteryLandscapeMap.name))
            } else {
                itemList.add(DataItem.Unlocked(R.drawable.cemetery, CemeteryLandscapeMap.name))
            }
        }
        return itemList
    }

    fun getItemsItems() : ArrayList<DataItem> {

        val itemList = arrayListOf<DataItem>()
        itemList.add(DataItem.Unlocked(R.drawable.bergeicon, MountainLandscapeMap.name))
        itemList.add(DataItem.Locked(R.drawable.cemetery, "statisch Locked", "50"))

        return itemList
    }

}