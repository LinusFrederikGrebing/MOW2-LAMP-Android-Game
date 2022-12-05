package de.thm.lampgame.model.music

import de.thm.lampgame.R

import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MusicInterface

class BackgroundMusicPasswordInfinity {
    companion object : MusicInterface {
        // all information about the associated music, such as the name or status, is stored in the itemInfo attribute
        override var itemInfo = Database.musicPasswordInfinityInfo
        override val song =
            R.raw.backgroundmusic_password_infinity

        // Credits:
        // Password Infinity - EvgenyBardyuzha
        // https://pixabay.com/de/music/schlagt-password-infinity-123276/
    }
}