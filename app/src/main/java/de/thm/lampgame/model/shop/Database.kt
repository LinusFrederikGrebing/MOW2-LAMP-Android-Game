package de.thm.lampgame.model.shop

import de.thm.lampgame.controller.skins.BlueLampSkin
import de.thm.lampgame.controller.skins.LampSkin
import de.thm.lampgame.controller.skins.PurpleLampSkin
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap
import de.thm.lampgame.controller.maps.MarsLandscapeMap
import de.thm.lampgame.controller.music.BackgroundMusic_password_Infinity
import de.thm.lampgame.controller.music.BackgroundMusic_Island
import de.thm.lampgame.controller.music.BackgroundMusic_Christmas_is_here

object Database {
    // the type of data item decides which ViewHolder is used and which design has to be loaded in the shop
    const val LOCKED_TYPE = 0
    const val UNLOCKED_TYPE = 1
    const val ACTIVE_TYPE = 2

    // initialize a list of all maps, skins and music
    val listOfMaps = listOf(MountainLandscapeMap,CemeteryLandscapeMap,MarsLandscapeMap)
    val listOfSkins = listOf(LampSkin,BlueLampSkin,PurpleLampSkin)
    val listOfMusic = listOf(BackgroundMusic_password_Infinity, BackgroundMusic_Island, BackgroundMusic_Christmas_is_here)

    // the lists are filled based on the status
    fun getItemsMaps(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()

        listOfMaps.forEach {
            if (!it.buyStatus) itemList.add(DataItem.Locked(it.icon, it.name, it.price))
            else if (it.active) itemList.add(DataItem.Active(it.icon, it.name))
            else itemList.add(DataItem.Unlocked(it.icon, it.name))
        }

        return itemList
    }

    fun getItemsSkins(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()

        listOfSkins.forEach {
            if (!it.buyStatus) itemList.add(DataItem.Locked(it.icon, it.name, it.price))
            else if (it.active) itemList.add(DataItem.Active(it.icon, it.name))
                 else itemList.add(DataItem.Unlocked(it.icon, it.name))
        }

        return itemList
    }

    fun getItemsMusic(): ArrayList<DataItem> {

        val itemList = arrayListOf<DataItem>()

        listOfMusic.forEach {
            if (!it.buyStatus) itemList.add(DataItem.Locked(it.icon, it.name, it.price))
            else if (it.active) itemList.add(DataItem.Active(it.icon, it.name))
            else itemList.add(DataItem.Unlocked(it.icon, it.name))
        }

        return itemList
    }
}





