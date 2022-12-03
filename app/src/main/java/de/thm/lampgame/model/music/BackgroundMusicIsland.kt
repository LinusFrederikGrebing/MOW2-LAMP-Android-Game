package de.thm.lampgame.model.music

import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MusicInterface

class BackgroundMusicIsland {

    companion object : MusicInterface {
        // all information about the associated musik, such as the name or status, is stored in the itemInfo attribute
        override var itemInfo = Database.musicIslandInfo
        override val song =
            R.raw.backgroundmusic_island

        // Credits:
        // Aurora by Luke Bergs | https://soundcloud.com/bergscloud/
        // Music promoted by https://www.chosic.com/free-music/all/
        // Creative Commons CC BY-SA 3.0
        // https://creativecommons.org/licenses/by-sa/3.0/
    }
}