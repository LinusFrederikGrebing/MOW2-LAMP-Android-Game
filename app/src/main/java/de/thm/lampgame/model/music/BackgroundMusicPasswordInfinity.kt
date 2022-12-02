package de.thm.lampgame.model.music

import de.thm.lampgame.R

import de.thm.lampgame.model.shop.Database
import de.thm.lampgame.model.shop.MusicInterface

class BackgroundMusicPasswordInfinity {
    companion object : MusicInterface {
        override var itemInfo = Database.musicPasswordInfinityInfo
        override val song =
            R.raw.backgroundmusic_password_infinity //Source: Password Infinity - EvgenyBardyuzha (https://www.youtube.com/watch?v=dM6gzuwtFbY)
    }
}