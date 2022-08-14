package com.example.passwordmanager

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

class PasswordsHolder(applicationContext: Context) {

    val masterPasswordKey = "MASTER_PASSWORD_APP_DONT_CHANGE"

    val masterKey = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    val sharedPreferences = EncryptedSharedPreferences.create(
        "PreferencesFilename",
        masterKey,
        applicationContext,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveMaster(password: String) {
        save(Password("Master Password", password))
        save(Password(masterPasswordKey, "Master Password"))
    }

    fun save(password: Password) {
        sharedPreferences.edit()
            .putString(password.appName, password.pasword)
            .apply()
    }

    fun edit(id: String, password: Password) {
        val masterPasswordOldKey = sharedPreferences.getString(masterPasswordKey, null)
        if(id == masterPasswordOldKey) {
            sharedPreferences.edit()
                .remove(masterPasswordKey)
                .putString(masterPasswordKey, password.appName)
                .apply()
        }

        sharedPreferences.edit()
        .remove(id)
        .putString(password.appName, password.pasword)
        .apply()
    }

    fun remove(id: String) {
        sharedPreferences.edit()
            .remove(id)
            .apply()
    }

    fun get(): Map<String,String> {
        return sharedPreferences.all.map {
            it.key to it.value!!.toString()
        } .toMap().filter { it.key != masterPasswordKey }
    }

    fun getMasterPassword(): String {
        val masterPasswordOldKey = sharedPreferences.getString(masterPasswordKey, null)!!

        return sharedPreferences.getString(masterPasswordOldKey, null)!!
    }

    fun doesMasterExist(): Boolean {
        val masterPassword = sharedPreferences.getString(masterPasswordKey, null)

        return masterPassword != null
    }

    data class Password(val appName: String, val pasword: String)
}