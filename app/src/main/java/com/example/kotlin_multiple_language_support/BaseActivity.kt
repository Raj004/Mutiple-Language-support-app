package com.example.kotlin_multiple_language_support

import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        resetTitles()
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleManager.setLocale(newBase))
    }

    private fun resetTitles() {
        try {
            val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
            if (info.labelRes !=0){
                setTitle(info.labelRes)
            }
        }catch (e :PackageManager.NameNotFoundException){
            e.printStackTrace()
        }
    }
}




