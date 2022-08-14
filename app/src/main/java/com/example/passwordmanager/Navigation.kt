package com.example.passwordmanager

import android.content.Intent



fun MainActivity.logIn() {
    val intent = Intent(this, PasswordsActivity::class.java)
    startActivity(intent)
    finish()
}

fun PasswordsActivity.openEditPassword(appName: String,password: String){
    val intent = Intent( this, EditPasswordActivity::class.java)
    intent.putExtra("appName", appName)
    intent.putExtra("password", password)
    startActivity(intent)
}
fun PasswordsActivity.openCreatePassword(){
    val intent = Intent(this, CreatePasswordActivity::class.java)
    startActivity(intent)
}