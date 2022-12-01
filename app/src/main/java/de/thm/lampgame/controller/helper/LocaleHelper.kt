package de.thm.lampgame.controller.helper

import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class LocaleHelper : AppCompatActivity() {
    fun setLocale(context: Context, language: String?) {
        updateResources(context, language)
    }

    private fun updateResources(context: Context, language: String?) {
        val locale = language?.let { Locale(it) }
        val resources: Resources = context.resources
        val configuration: Configuration = resources.configuration

        configuration.locale = locale

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }
}