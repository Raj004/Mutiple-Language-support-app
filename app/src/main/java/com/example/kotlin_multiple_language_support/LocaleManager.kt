package com.example.kotlin_multiple_language_support

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.os.Build
import androidx.annotation.StringDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.util.*

object LocaleManager {

    internal const val ENGLISH = "en"
    internal const val HINDI = "hi"
    internal const val SPANISH = "es"
    internal const val JAPAN = "ja"


    /**
     * SharedPreferences Key
     */
    private val LANGUAGE_KEY = "language_key"

    @Retention(RetentionPolicy.SOURCE)
    @StringDef(ENGLISH, HINDI, SPANISH, JAPAN)
    annotation class LocaleDef {
        companion object {
            val SUPPORTED_LOCALES = arrayOf(ENGLISH, HINDI, SPANISH, JAPAN)
        }
    }

    /**
     * set current pref locale
     */
    fun setLocale(mContext: Context): Context {
        return updateResources(mContext, getLanguagePref(mContext))
    }

    /**
     * Set new Locale with context
     */
    fun setNewLocale(mContext: Context, @LocaleDef language: String): Context {
        setLanguagePref(mContext, language)
        return updateResources(mContext, language)
    }

    /**
     * Get saved Locale from SharedPreferences
     *
     * @param mContext current context
     * @return current locale key by default return english locale
     */
    fun getLanguagePref(mContext: Context): String {
        val mPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(mContext)
        return mPreferences.getString(LANGUAGE_KEY, ENGLISH).toString()
    }

    fun getDefaultSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            getDefaultSharedPreferencesName(context),
            getDefaultSharedPreferencesMode()
        )
    }

    private fun getDefaultSharedPreferencesName(context: Context): String {
        return context.packageName + "_preferences"
    }

    private fun getDefaultSharedPreferencesMode(): Int {
        return Context.MODE_PRIVATE
    }

    /**
     * set pref key
     */
    private fun setLanguagePref(mContext: Context, localeKey: String) {
        val mPreferences = androidx.preference.PreferenceManager.getDefaultSharedPreferences(mContext)
        mPreferences.edit().putString(LANGUAGE_KEY, localeKey).apply()
    }

    /**
     * update resource
     */
    private fun updateResources(context: Context, language: String): Context {
        var context = context
        val locale = Locale(language)
        Locale.setDefault(locale)
        val res = context.resources
        val config = Configuration(res.configuration)
        if (Build.VERSION.SDK_INT >= 17) {
            config.setLocale(locale)
            context = context.createConfigurationContext(config)
        } else {
            config.locale = locale
            res.updateConfiguration(config, res.displayMetrics)
        }
        return context
    }


}
