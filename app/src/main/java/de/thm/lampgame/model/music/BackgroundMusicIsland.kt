package de.thm.lampgame.model.music

import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MusicInterface

class BackgroundMusicIsland {
    
    companion object : MusicInterface {
        override var itemInfo = Database.musicIslandInfo
        override val song = R.raw.backgroundmusic_island //Source: Island - Luke Bergs (https://www.chosic.com/download-audio/42076/)
    }
}