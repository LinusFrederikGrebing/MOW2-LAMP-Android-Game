package de.thm.lampgame.controller.helper

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class LocaleHelper : AppCompatActivity() {
    private var settings: SharedPreferences? = null

    // If a new language is to be set, the language must be saved in the SharedPreferences
    // and stored in the configuration
    fun setLocale(context: Context, language: String?) {
        saveNewLanguage(context, language)
        updateResources(context, language)
    }

    // sets the language to the one stored in the sharedPreferences
    fun getAndSetPersistedLanguageData(context: Context) {
        settings = context.getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        updateResources(context, (settings!!.getString("SELECTED_LANGUAGE", "en")))
    }

    // stores the current language in the sharedPreferences
    private fun saveNewLanguage(context: Context, language: String?) {
        settings = context.getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings!!.edit()
        editor.putString("SELECTED_LANGUAGE", language)
        editor.apply()
    }

    // updates the language in the system configuration
    @Suppress("DEPRECATION")
    private fun updateResources(context: Context, language: String?) {
        val locale = language?.let { Locale(it) }
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}