package com.example.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class EditPasswordActivity : AppCompatActivity() {
    lateinit var passwordsHolder: PasswordsHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passwordsHolder = PasswordsHolder(this.applicationContext)
        setContentView(R.layout.activity_edit)

        val newAppNameEditText = findViewById<EditText>(R.id.createAppName)
        val newPassword = findViewById<EditText>(R.id.createPassword)

        val extraName = intent.getStringExtra("appName")!!
        val extraPassword = intent.getStringExtra("password")

        newAppNameEditText.setText(extraName)
        newPassword.setText(extraPassword)


        val backButton = findViewById<ImageView>(R.id.backButton)


        backButton.setOnClickListener {
            finish()
        }

        val createNewSaveButton = findViewById<Button>(R.id.createNewSaveButton)
        createNewSaveButton.setOnClickListener {

            val appName = newAppNameEditText.text.toString()
            val password = newPassword.text.toString()

            passwordsHolder.edit(extraName, PasswordsHolder.Password(appName, password))

            finish()
        }
    }
}