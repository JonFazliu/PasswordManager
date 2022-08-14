package com.example.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class CreatePasswordActivity : AppCompatActivity() {
    lateinit var passwordsHolder: PasswordsHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passwordsHolder = PasswordsHolder(this.applicationContext)
        setContentView(R.layout.activity_create_password)

        val backButton = findViewById<ImageView>(R.id.backButton)

        backButton.setOnClickListener {
            finish()
        }

        val createNewSaveButton = findViewById<Button>(R.id.createNewSaveButton)
        createNewSaveButton.setOnClickListener {
            val newAppName = findViewById<EditText>(R.id.createAppName)
            val newPassword = findViewById<EditText>(R.id.createPassword)

            val appName = newAppName.text.toString()
            val password = newPassword.text.toString()

            passwordsHolder.save(PasswordsHolder.Password(appName, password))

            finish()
        }
    }
}
