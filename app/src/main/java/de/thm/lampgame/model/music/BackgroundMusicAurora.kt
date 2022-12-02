package de.thm.lampgame.model.music

import de.thm.lampgame.R
import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MusicInterface

class BackgroundMusicAurora {

    companion object : MusicInterface {
        override var itemInfo = Database.musicAuroraInfo
        override val song =
            R.raw.backgroundmusic_aurora

        // Credits:
        // Aurora by Luke Bergs | https://soundcloud.com/bergscloud/
        // Music promoted by https://www.chosic.com/free-music/all/
        // Creative Commons CC BY-SA 3.0
        // https://creativecommons.org/licenses/by-sa/3.0/

    }
}