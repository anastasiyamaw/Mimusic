package com.example.mimusic

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.fragment_second.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val prefs = this.getSharedPreferences("PREFS", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val intent = Intent(this, MainActivity::class.java)
        return when (item.itemId) {
            R.id.logOut -> {
                editor.clear().apply()
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
}
    }
