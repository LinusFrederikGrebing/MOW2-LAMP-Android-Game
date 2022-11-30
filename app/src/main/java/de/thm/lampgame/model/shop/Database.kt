package de.thm.lampgame.model.shop

import de.thm.lampgame.R
import de.thm.lampgame.controller.skins.BlueLampSkin
import de.thm.lampgame.controller.skins.LampSkin
import de.thm.lampgame.controller.skins.PurpleLampSkin
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.ChristmasLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap
import de.thm.lampgame.controller.maps.MarsLandscapeMap
import de.thm.lampgame.model.music.BackgroundMusicPasswordInfinity
import de.thm.lampgame.model.music.BackgroundMusicIsland
import de.thm.lampgame.model.music.BackgroundMusicChristmasIsHere
import de.thm.lampgame.controller.skins.ChristmasLampSkin

object Database {
    // the type of data item decides which ViewHolder is used and which design has to be loaded in the shop
    const val LOCKED_TYPE = 0
    const val UNLOCKED_TYPE = 1
    const val ACTIVE_TYPE = 2

    // init all ShopItemInfos
    var musicChristmasInfo = ShopItemInfo("Christmas is here", false, false, "50",  R.drawable.shop_music_christmas)
    var musicPasswordInfinityInfo = ShopItemInfo("Password Infinity",true,true, "0", R.drawable.shop_music_passwordinfinity)
    var musicIslandInfo = ShopItemInfo("Island",false,false, "30", R.drawable.shop_music_island)

    var mapMountainLandscape = ShopItemInfo("MountainLandscapeMap", false, false, "50",   R.drawable.bergeicon)
    var mapCemeteryLandscape = ShopItemInfo("CemeteryLandscapeMap", true, true, "0",   R.drawable.cemetery)
    var mapMarsLandscape = ShopItemInfo("MarsLandscapeMap",false,false, "100", R.drawable.map_mars)
    var mapChristmasLandscape = ShopItemInfo("ChristmasLandscapeMap",false,false, "50", R.drawable.christmasmap)

    var skinLamp = ShopItemInfo("LampSkin", true, true, "0",   R.drawable.skin_standard)
    var skinBlueLamp = ShopItemInfo("BlueLampSkin", false, false, "50",   R.drawable.skin_blau)
    var skinPurpleLamp = ShopItemInfo("PurpleLampSkin",false,false, "100", R.drawable.skin_lila)
    var skinChristmasLamp = ShopItemInfo("ChristmasLampSkin",false,false, "50", R.drawable.skin_christmas)

    // initialize a list of all maps, skins and music
    val listOfMaps = listOf(CemeteryLandscapeMap, MountainLandscapeMap, MarsLandscapeMap, ChristmasLandscapeMap)
    val listOfSkins = listOf(LampSkin, BlueLampSkin, ChristmasLampSkin, PurpleLampSkin)
    val listOfMusic = listOf(BackgroundMusicPasswordInfinity, BackgroundMusicIsland, BackgroundMusicChristmasIsHere)

    // the lists are filled based on the status
    fun getItemsMaps(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()
        listOfMaps.forEach {
            if (!it.itemInfo.buyStatus) itemList.add(DataItem.Locked(it.itemInfo.icon, it.itemInfo.name, it.itemInfo.price))
            else if (it.itemInfo.active) itemList.add(DataItem.Active(it.itemInfo.icon, it.itemInfo.name))
            else itemList.add(DataItem.Unlocked(it.itemInfo.icon, it.itemInfo.name))
        }

        return itemList
    }

    fun getItemsSkins(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()
        listOfSkins.forEach {
            if (!it.itemInfo.buyStatus) itemList.add(DataItem.Locked(it.itemInfo.icon, it.itemInfo.name, it.itemInfo.price))
            else if (it.itemInfo.active) itemList.add(DataItem.Active(it.itemInfo.icon, it.itemInfo.name))
                 else itemList.add(DataItem.Unlocked(it.itemInfo.icon, it.itemInfo.name))
        }

        return itemList
    }

    fun getItemsMusic(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()
        listOfMusic.forEach {
            if (!it.itemInfo.buyStatus) itemList.add(DataItem.Locked(it.itemInfo.icon, it.itemInfo.name, it.itemInfo.price))
            else if (it.itemInfo.active) itemList.add(DataItem.Active(it.itemInfo.icon, it.itemInfo.name))
            else itemList.add(DataItem.Unlocked(it.itemInfo.icon, it.itemInfo.name))
        }

        return itemList
    }
}





