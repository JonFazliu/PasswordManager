package com.example.passwordmanager

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class PasswordsActivity : AppCompatActivity() {
    lateinit var passwordsHolder: PasswordsHolder
    lateinit var passwordsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passwordsHolder = PasswordsHolder(this.applicationContext)

        setContentView(R.layout.activity_passwords)
        passwordsContainer = findViewById(R.id.passwordsContainer)

        val searchView = findViewById<SearchView>(R.id.search)
        searchView.setOnQueryTextListener(onSearch(::searchItem))

        val addPassword = findViewById<Button>(R.id.addPassword)
        addPassword.setOnClickListener {
           openCreatePassword()
        }
    }

    override fun onResume() {
        super.onResume()
        showPasswords(passwordsHolder.get())
    }


    fun showPasswords(passwords: Map<String, String>) {
        passwordsContainer.removeAllViews()

        passwords.forEach { (appName, password) ->

           ListItem(layoutInflater).apply {
               textView.text = appName

               textView.setOnClickListener {
                   openEditPassword(appName, password)
               }

               trashCan.setOnClickListener {
                   showAlertDialog(appName) {
                       passwordsHolder.remove(appName)
                       passwordsContainer.removeView(view)
                   }
               }

               copyPassword.setOnClickListener {
                   addToClipBoard(password)
               }

               passwordsContainer.addView(view)
           }
        }
    }


    fun searchItem(searchQuery: String): Boolean{
        val filteredPassword = passwordsHolder.get().filter { it.key.toLowerCase().contains(searchQuery.toLowerCase()) }
        showPasswords(filteredPassword)
        return true
    }
}