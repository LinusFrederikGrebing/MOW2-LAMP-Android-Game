package de.thm.lampgame.controller.music

import de.thm.lampgame.R
import de.thm.lampgame.model.MusicInterface

class BackgroundMusic_Christmas_is_here {
    companion object : MusicInterface {
        override var active = false
        override var name = "Christmas is here"
        override var buyStatus = false
        override val price = "50"
        override val icon = R.drawable.shop_music_christmas
        override val song = R.raw.backgroundmusic_christmas_is_here
        //Source: Christmas is here - Unknown (https://www.youtube.com/watch?v=mng4p8yWFjs)
    }
}