package de.thm.lampgame.model

import de.thm.lampgame.R
import de.thm.lampgame.controller.Skins.BlueLampSkin
import de.thm.lampgame.controller.Skins.LampSkin
import de.thm.lampgame.controller.Skins.PurpleLampSkin
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap
import de.thm.lampgame.controller.maps.MarsLandscapeMap

object Database {
    const val LOCKED_TYPE = 0
    const val UNLOCKED_TYPE = 1
    const val ACTIVE_TYPE = 2

    val listOfMaps = listOf(MountainLandscapeMap,CemeteryLandscapeMap,MarsLandscapeMap)
    val listOfSkins = listOf(LampSkin,BlueLampSkin,PurpleLampSkin)

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

    fun getItemsMusic(): ArrayList<DataItem> { //TODO wenn/falls kaufbare Musik implementiert wird

        val itemList = arrayListOf<DataItem>()
        if (MountainLandscapeMap.active) {
            itemList.add(DataItem.Active(R.drawable.exit_icon, MountainLandscapeMap.name))
        } else {
            itemList.add(DataItem.Unlocked(R.drawable.exit_icon, MountainLandscapeMap.name))
        }

        if (!CemeteryLandscapeMap.buyStatus) {
            itemList.add(
                DataItem.Locked(
                    R.drawable.exit_icon,
                    CemeteryLandscapeMap.name,
                    "70"
                )
            )
        } else {
            if (CemeteryLandscapeMap.active) {
                itemList.add(DataItem.Active(R.drawable.exit_icon, CemeteryLandscapeMap.name))
            } else {
                itemList.add(DataItem.Unlocked(R.drawable.exit_icon, CemeteryLandscapeMap.name))
            }
        }
        return itemList
    }
}





