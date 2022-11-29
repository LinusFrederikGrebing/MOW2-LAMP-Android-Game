package de.thm.lampgame.model.music

import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MusicInterface
import de.thm.lampgame.model.shop.ShopItemInfo

class BackgroundMusicIsland {
    
    companion object : MusicInterface {
        override var mapInfo = ShopItemInfo("Island",false,false, "30")
        override val icon = R.drawable.shop_music_island
        override val song = R.raw.backgroundmusic_island
        //Source: Island - Luke Bergs (https://www.chosic.com/download-audio/42076/)
    }
}