package com.example.kotlin_multiple_language_support

import android.app.Application
import android.content.Context
import android.content.res.Configuration

class MultiLanguageApp : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.setLocale(base))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleManager.setLocale(this)
    }
}
