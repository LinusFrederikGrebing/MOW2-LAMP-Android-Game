package de.thm.lampgame.model

import de.thm.lampgame.R
import de.thm.lampgame.controller.Skins.BlueLampSkin
import de.thm.lampgame.controller.Skins.LampSkin
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap
import de.thm.lampgame.controller.maps.MarsLandscapeMap

object Database {
    const val LOCKED_TYPE = 0
    const val UNLOCKED_TYPE = 1
    const val ACTIVE_TYPE = 2





    fun getItemsMaps(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()



        if (MountainLandscapeMap.active) {
            itemList.add(DataItem.Active(R.drawable.bergeicon, MountainLandscapeMap.name))
        } else {
            itemList.add(DataItem.Unlocked(R.drawable.bergeicon, MountainLandscapeMap.name))
        }

        if (!CemeteryLandscapeMap.buyStatus) {
            itemList.add(
                DataItem.Locked(
                    R.drawable.cemetery,
                    CemeteryLandscapeMap.name,
                    "35"
                )
            )
        } else {
            if (CemeteryLandscapeMap.active) {
                itemList.add(DataItem.Active(R.drawable.cemetery, CemeteryLandscapeMap.name))
            } else {
                itemList.add(DataItem.Unlocked(R.drawable.cemetery, CemeteryLandscapeMap.name))
            }
        }
        if (!MarsLandscapeMap.buyStatus) {
            itemList.add(
                DataItem.Locked(
                    R.drawable.cemetery,
                    MarsLandscapeMap.name,
                    "35"
                )
            )
        } else {
            if (MarsLandscapeMap.active) {
                itemList.add(DataItem.Active(R.drawable.cemetery, MarsLandscapeMap.name))
            } else {
                itemList.add(DataItem.Unlocked(R.drawable.cemetery, MarsLandscapeMap.name))
            }
        }
        itemList.add(DataItem.Locked(R.drawable.exit, "ExitMap", "70"))
        return itemList

    }

    fun getItemsSkins(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()

        if (LampSkin.active) {
            itemList.add(DataItem.Active(R.drawable.skin1, LampSkin.name))
        } else {
            itemList.add(DataItem.Unlocked(R.drawable.skin1, LampSkin.name))
        }
        if (!BlueLampSkin.buyStatus) {
            itemList.add(
                DataItem.Locked(
                    R.drawable.skin2,
                    BlueLampSkin.name,
                    "35"
                )
            )
        } else {
            if (BlueLampSkin.active) {
                itemList.add(DataItem.Active(R.drawable.skin2, BlueLampSkin.name))
            } else {
                itemList.add(DataItem.Unlocked(R.drawable.skin2, BlueLampSkin.name))
            }
        }

        return itemList
    }

    fun getItemsItems(): ArrayList<DataItem> {

        val itemList = arrayListOf<DataItem>()
        if (MountainLandscapeMap.active) {
            itemList.add(DataItem.Active(R.drawable.exit, MountainLandscapeMap.name))
        } else {
            itemList.add(DataItem.Unlocked(R.drawable.exit, MountainLandscapeMap.name))
        }

        if (!CemeteryLandscapeMap.buyStatus) {
            itemList.add(
                DataItem.Locked(
                    R.drawable.exit,
                    CemeteryLandscapeMap.name,
                    "70"
                )
            )
        } else {
            if (CemeteryLandscapeMap.active) {
                itemList.add(DataItem.Active(R.drawable.exit, CemeteryLandscapeMap.name))
            } else {
                itemList.add(DataItem.Unlocked(R.drawable.exit, CemeteryLandscapeMap.name))
            }
        }
        return itemList
    }
}





