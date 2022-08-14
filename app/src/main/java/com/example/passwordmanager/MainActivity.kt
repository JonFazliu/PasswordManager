package com.example.passwordmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var passwordsHolder: PasswordsHolder


    lateinit var saveButton: Button
    lateinit var passwordEditText: EditText
    lateinit var incorrectPassword: TextView

    var incorrectPasswordCountdown: Long = 1000 * 5
    var passwordCounter = 0

    fun createViews() {
        saveButton = findViewById(R.id.save)
        passwordEditText = findViewById(R.id.password)
        incorrectPassword = findViewById(R.id.incorrectPassword)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        passwordsHolder = PasswordsHolder(this.applicationContext)
        setContentView(R.layout.activity_main)
        createViews()

        if(passwordsHolder.doesMasterExist()) {
            saveButton.text = "Log In"
        } else {
            saveButton.text = "Save"
        }

        saveButton.setOnClickListener {
            val inputPassword = passwordEditText.text.toString()

            tryToLoginWith(inputPassword)

        }
    }

    private fun tryToLoginWith(inputPassword: String) {
        saveMasterIfDoesntExist(inputPassword)

        if (isPasswordCorrect(inputPassword)) {
            logIn()
        } else {
            incorrectPasswordGiven()
        }
    }

    private fun saveMasterIfDoesntExist(password: String) {
        if(!passwordsHolder.doesMasterExist()) {
            passwordsHolder.saveMaster(password)
        }
    }

    private fun isPasswordCorrect(inputPassword: String): Boolean {
        return inputPassword == passwordsHolder.getMasterPassword()
    }

    private fun incorrectPasswordGiven() {
        passwordCounter++
        incorrectPassword.visibility = View.VISIBLE

        if (passwordCounter == 3) {
            cooldown()
        }
    }

    fun cooldown() {
        saveButton.isEnabled = false

        startCountDownTimer(incorrectPasswordCountdown,
            { incorrectPassword.text = "Try again in ${it / 1000} seconds" },
            ::timerFinished
        )
    }

    fun timerFinished() {
        resetView()
        doubleTimeout()
    }

    fun resetView() {
        passwordCounter = 0
        incorrectPassword.visibility = View.GONE
        incorrectPassword.text = "Incorrect Password"
        saveButton.isEnabled = true
    }

    fun doubleTimeout() {
        incorrectPasswordCountdown *= 2
    }
}