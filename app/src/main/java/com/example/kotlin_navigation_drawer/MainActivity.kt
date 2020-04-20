package com.example.kotlin_navigation_drawer


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar, 0, 0
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.local_english -> {
                setNewLocale(this, LocaleManager.ENGLISH)
                return true
            }
            R.id.local_hindi -> {
                setNewLocale(this, LocaleManager.HINDI)
                return true
            }

            R.id.local_spanish -> {
                setNewLocale(this, LocaleManager.SPANISH)
                return true
            }
            R.id.local_japan -> {
                setNewLocale(this, LocaleManager.JAPAN)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setNewLocale(mContext: AppCompatActivity, @LocaleManager.LocaleDef language: String) {
        LocaleManager.setNewLocale(this, language)
        val intent = mContext.intent
        startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK))
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_camera -> {
                Toast.makeText(this, "Camera clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_gallery -> {
                Toast.makeText(this, "Gallery clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_slideshow -> {
                Toast.makeText(this, "Slideshow clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_manage -> {
                Toast.makeText(this, "Manage clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_share -> {
                Toast.makeText(this, "Share clicked", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_send -> {
                Toast.makeText(this, "Send clicked", Toast.LENGTH_SHORT).show()
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}