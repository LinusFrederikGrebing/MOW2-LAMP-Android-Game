package de.thm.lampgame.controller.helper

import android.content.Context
import de.thm.lampgame.R

class LoadingScreenHelper {
    //  the LoadingScreenHelper can be used to get one of the possible tip texts
    fun getLoadingScreenText(context: Context) : String {
        return when ((1..4).random()) {
            // the tip text is read from the R.Strings file based on the language set
            1 -> context.getString(R.string.tip1)
            2 -> context.getString(R.string.tip2)
            3 -> context.getString(R.string.tip3)
            4 -> context.getString(R.string.tip4)
            else -> {
                context.getString(R.string.tipNotFound)
            }
        }
    }
}