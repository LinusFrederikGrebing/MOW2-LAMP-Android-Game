package de.thm.lampgame.controller.helper

import android.content.Context
import androidx.annotation.StringRes

// the UiTextHelper class is used to ensure reading from the R.Strings file in the view based on the language set
sealed class UiTextHelper {
    data class DynamicString(val value: String) : UiTextHelper()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ) : UiTextHelper()

    fun asString(context: Context): String {
        return when (this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }
}