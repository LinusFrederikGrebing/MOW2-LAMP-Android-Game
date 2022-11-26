package de.thm.lampgame.model.music

import de.thm.lampgame.R
import de.thm.lampgame.model.shop.MusicInterface

class BackgroundMusicIsland {
    companion object : MusicInterface {
        override var active = false
        override var name = "Island"
        override var buyStatus = false
        override val price = "30"
        override val icon = R.drawable.shop_music_island
        override val song = R.raw.backgroundmusic_island
        //Source: Island - Luke Bergs (https://www.chosic.com/download-audio/42076/)
    }
}