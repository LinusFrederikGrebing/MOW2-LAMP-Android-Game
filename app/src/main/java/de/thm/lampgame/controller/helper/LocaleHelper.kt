package de.thm.lampgame.controller.helper

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class LocaleHelper : AppCompatActivity() {
    private var settings: SharedPreferences? = null
    fun setLocale(context: Context, language: String?) {
        saveNewLanguage(context, language)
        updateResources(context, language)
    }

    fun getAndSetPersistedLanguageData(context: Context) {
        settings = context.getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        setLocale(context, (settings!!.getString("SELECTED_LANGUAGE", "en")))
    }

    fun saveNewLanguage(context: Context, language: String?) {
        settings = context.getSharedPreferences("GAME_DATA", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = settings!!.edit()
        editor.putString("SELECTED_LANGUAGE", language)
        editor.apply()
    }

    private fun updateResources(context: Context, language: String?) {
        val locale = language?.let { Locale(it) }
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration
        //TODO maybe fix/update deprecated stuff
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}