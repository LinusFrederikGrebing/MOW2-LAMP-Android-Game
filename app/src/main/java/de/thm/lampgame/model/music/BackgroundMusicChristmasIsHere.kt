package de.thm.lampgame.model.music

import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.ShopItemInfo
import de.thm.lampgame.model.shop.MusicInterface

class BackgroundMusicChristmasIsHere {

    companion object : MusicInterface {
        override var mapInfo = ShopItemInfo("Christmas is here", false, false, "50")
        override val icon = R.drawable.shop_music_christmas
        override val song = R.raw.backgroundmusic_christmas_is_here
        //Source: Christmas is here - Unknown (https://www.youtube.com/watch?v=mng4p8yWFjs)
    }
}