package de.thm.lampgame.model

import de.thm.lampgame.controller.Skins.BlueLampSkin
import de.thm.lampgame.controller.Skins.LampSkin
import de.thm.lampgame.controller.Skins.PurpleLampSkin
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap
import de.thm.lampgame.controller.maps.MarsLandscapeMap
import de.thm.lampgame.controller.music.BackgroundMusic_password_Infinity
import de.thm.lampgame.controller.music.BackgroundMusic_Island
import de.thm.lampgame.controller.music.BackgroundMusic_Christmas_is_here

object Database {
    const val LOCKED_TYPE = 0
    const val UNLOCKED_TYPE = 1
    const val ACTIVE_TYPE = 2

    val listOfMaps = listOf(MountainLandscapeMap,CemeteryLandscapeMap,MarsLandscapeMap)
    val listOfSkins = listOf(LampSkin,BlueLampSkin,PurpleLampSkin)
    val listOfMusic = listOf(BackgroundMusic_password_Infinity, BackgroundMusic_Island, BackgroundMusic_Christmas_is_here)

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
        if (BackgroundMusic_password_Infinity.active) {
            itemList.add(DataItem.Active(BackgroundMusic_password_Infinity.icon, BackgroundMusic_password_Infinity.name))
        } else {
            itemList.add(DataItem.Unlocked(BackgroundMusic_password_Infinity.icon, BackgroundMusic_password_Infinity.name))
        }

        if (!BackgroundMusic_Island.buyStatus) {
            itemList.add(
                DataItem.Locked(
                    BackgroundMusic_Island.icon,
                    BackgroundMusic_Island.name,
                    BackgroundMusic_Island.price
                )
            )
        } else {
            if (BackgroundMusic_Island.active) {
                itemList.add(DataItem.Active(BackgroundMusic_Island.icon, BackgroundMusic_Island.name))
            } else {
                itemList.add(DataItem.Unlocked(BackgroundMusic_Island.icon, BackgroundMusic_Island.name))
            }
        }

        if (!BackgroundMusic_Christmas_is_here.buyStatus) {
            itemList.add(
                DataItem.Locked(
                    BackgroundMusic_Christmas_is_here.icon,
                    BackgroundMusic_Christmas_is_here.name,
                    BackgroundMusic_Christmas_is_here.price
                )
            )
        } else {
            if (BackgroundMusic_Christmas_is_here.active) {
                itemList.add(DataItem.Active(BackgroundMusic_Christmas_is_here.icon, BackgroundMusic_Christmas_is_here.name))
            } else {
                itemList.add(DataItem.Unlocked(BackgroundMusic_Christmas_is_here.icon, BackgroundMusic_Christmas_is_here.name))
            }
        }
        return itemList
    }
}





