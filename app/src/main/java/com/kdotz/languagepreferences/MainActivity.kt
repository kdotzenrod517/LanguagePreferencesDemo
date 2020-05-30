package com.kdotz.languagepreferences

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreference: SharedPreferences
    lateinit var textView: TextView

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        return when (item.itemId) {
            R.id.english -> {
                setLanguage("English")
                true
            }
            R.id.spanish -> {
                setLanguage("Spanish")
                true
            }
            else -> false
        }
    }

    private fun setLanguage(language: String) {
        sharedPreference.edit().putString("language", language).apply()
        textView.text = language

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreference = getSharedPreferences("com.kdotz.languagepreferences", Context.MODE_PRIVATE)
        textView = findViewById(R.id.textView)

        val language = sharedPreference.getString("language", "Error")

        if (language == "Error") {
            AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_btn_speak_now)
                .setTitle("Choose a language")
                .setMessage("Which language would you like to use?")
                .setPositiveButton("English") { _, _ ->
                    setLanguage("English")
                }
                .setNegativeButton("Spanish") { _, _ ->
                    setLanguage("Spanish")
                }.show()
        } else {
            textView.text = language
        }
    }
}
