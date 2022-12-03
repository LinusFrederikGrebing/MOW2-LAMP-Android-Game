package de.thm.lampgame.model.music

import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MusicInterface

class BackgroundMusicChristmasIsHere {

    companion object : MusicInterface {
        // all information about the associated musik, such as the name or status, is stored in the itemInfo attribute
        override var itemInfo = Database.musicChristmasInfo
        override val song =
            R.raw.backgroundmusic_christmas_is_here

        // Credits:
        // Christmas is here - MorningLightMusic
        // https://www.youtube.com/watch?v=mng4p8yWFjs
    }
}