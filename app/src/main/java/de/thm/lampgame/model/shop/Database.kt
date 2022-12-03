package de.thm.lampgame.model.shop

import de.thm.lampgame.R
import de.thm.lampgame.controller.maps.CemeteryLandscapeMap
import de.thm.lampgame.controller.maps.ChristmasLandscapeMap
import de.thm.lampgame.controller.maps.MarsLandscapeMap
import de.thm.lampgame.controller.maps.MountainLandscapeMap
import de.thm.lampgame.controller.skins.BlueLampSkin
import de.thm.lampgame.controller.skins.ChristmasLampSkin
import de.thm.lampgame.controller.skins.OriginalLampSkin
import de.thm.lampgame.controller.skins.PurpleLampSkin
import de.thm.lampgame.model.music.BackgroundMusicAurora
import de.thm.lampgame.model.music.BackgroundMusicChristmasIsHere
import de.thm.lampgame.model.music.BackgroundMusicIsland
import de.thm.lampgame.model.music.BackgroundMusicPasswordInfinity

@Suppress("BooleanLiteralArgument")
object Database {
    // the type of data item decides which ViewHolder is used and which design has to be loaded in the shop
    const val LOCKED_TYPE = 0
    const val UNLOCKED_TYPE = 1
    const val ACTIVE_TYPE = 2

    // init all ShopItemInfo's
    var musicChristmasInfo = ShopItemInfo(
        R.string.christmasIsHereMusic,
        false,
        false,
        "5",
        R.drawable.shop_music_christmas
    )
    var musicPasswordInfinityInfo = ShopItemInfo(
        R.string.passwordInfinityMusic,
        true,
        true,
        "0",
        R.drawable.shop_music_passwordinfinity
    )
    var musicIslandInfo = ShopItemInfo(
        R.string.islandMusic,
        false,
        false,
        "30",
        R.drawable.shop_music_island
    )
    var musicAuroraInfo = ShopItemInfo(
        R.string.auroraMusic,
        false,
        false,
        "70",
        R.drawable.shop_music_aurora
    )

    var mapMountainLandscape = ShopItemInfo(
        R.string.mountainMap,
        false,
        false,
        "5",
        R.drawable.shop_map_mountain
    )
    var mapCemeteryLandscape = ShopItemInfo(
        R.string.cemeteryMap,
        true,
        true,
        "0",
        R.drawable.shop_map_cemetery
    )
    var mapMarsLandscape = ShopItemInfo(
        R.string.marsMap,
        false,
        false,
        "100",
        R.drawable.shop_map_mars
    )
    var mapChristmasLandscape = ShopItemInfo(
        R.string.christmasMap,
        false,
        false,
        "150",
        R.drawable.shop_map_christmas
    )

    var skinLamp = ShopItemInfo(
        R.string.lampSkin,
        true,
        true,
        "0",
        R.drawable.shop_skin_standard
    )
    var skinBlueLamp = ShopItemInfo(
        R.string.blueLampSkin,
        false,
        false,
        "5",
        R.drawable.shop_skin_blau
    )
    var skinPurpleLamp = ShopItemInfo(
        R.string.purpleLampSkin,
        false,
        false,
        "50",
        R.drawable.shop_skin_lila
    )
    var skinChristmasLamp = ShopItemInfo(
        R.string.christmasLampSkin,
        false,
        false,
        "70",
        R.drawable.shop_skin_christmas
    )

    // initialize a list of all maps, skins and music
    val listOfMaps = listOf(
        CemeteryLandscapeMap,
        MountainLandscapeMap,
        MarsLandscapeMap,
        ChristmasLandscapeMap
    )
    val listOfSkins = listOf(
        OriginalLampSkin,
        BlueLampSkin,
        ChristmasLampSkin,
        PurpleLampSkin
    )
    val listOfMusic = listOf(
        BackgroundMusicPasswordInfinity,
        BackgroundMusicIsland,
        BackgroundMusicChristmasIsHere,
        BackgroundMusicAurora
    )

    // the lists are filled based on the status
    fun getItemsMaps(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()
        listOfMaps.forEach {
            if (!it.itemInfo.buyStatus) itemList.add(
                DataItem.Locked(
                    it.itemInfo.icon,
                    it.itemInfo.name,
                    it.itemInfo.price
                )
            )
            else if (it.itemInfo.active) itemList.add(
                DataItem.Active(
                    it.itemInfo.icon,
                    it.itemInfo.name
                )
            )
            else itemList.add(DataItem.Unlocked(it.itemInfo.icon, it.itemInfo.name))
        }
        return itemList
    }

    fun getItemsSkins(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()
        listOfSkins.forEach {
            if (!it.itemInfo.buyStatus) itemList.add(
                DataItem.Locked(
                    it.itemInfo.icon,
                    it.itemInfo.name,
                    it.itemInfo.price
                )
            )
            else if (it.itemInfo.active) itemList.add(
                DataItem.Active(
                    it.itemInfo.icon,
                    it.itemInfo.name
                )
            )
            else itemList.add(DataItem.Unlocked(it.itemInfo.icon, it.itemInfo.name))
        }
        return itemList
    }

    fun getItemsMusic(): ArrayList<DataItem> {
        val itemList = arrayListOf<DataItem>()
        listOfMusic.forEach {
            if (!it.itemInfo.buyStatus) itemList.add(
                DataItem.Locked(
                    it.itemInfo.icon,
                    it.itemInfo.name,
                    it.itemInfo.price
                )
            )
            else if (it.itemInfo.active) itemList.add(
                DataItem.Active(
                    it.itemInfo.icon,
                    it.itemInfo.name
                )
            )
            else itemList.add(DataItem.Unlocked(it.itemInfo.icon, it.itemInfo.name))
        }
        return itemList
    }
}