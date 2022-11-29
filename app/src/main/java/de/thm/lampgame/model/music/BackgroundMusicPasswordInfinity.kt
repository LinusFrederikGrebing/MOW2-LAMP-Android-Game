package de.thm.lampgame.model.music

import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MusicInterface
import de.thm.lampgame.model.shop.ShopItemInfo

class BackgroundMusicPasswordInfinity {

    companion object : MusicInterface {
        override var mapInfo = ShopItemInfo("Password Infinity",true,true, "0")
        override val icon = R.drawable.shop_music_icon
        override val song = R.raw.backgroundmusic_password_infinity
        //Source: Password Infinity - EvgenyBardyuzha (https://www.youtube.com/watch?v=dM6gzuwtFbY)
    }
}