package de.thm.lampgame.controller.music

import de.thm.lampgame.R
import de.thm.lampgame.model.MusicInterface

class BackgroundMusic_password_Infinity {
    companion object : MusicInterface {
        override var active = true
        override var name = "Password Infinity"
        override var buyStatus = true
        override val price = "0"
        override val icon = R.drawable.shop_music_icon
        override val song = R.raw.backgroundmusic_password_infinity
        //Source: Password Infinity - EvgenyBardyuzha (https://www.youtube.com/watch?v=dM6gzuwtFbY)
    }
}