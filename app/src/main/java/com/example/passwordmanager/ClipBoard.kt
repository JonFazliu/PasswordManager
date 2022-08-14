package com.example.passwordmanager

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.core.content.ContextCompat

fun Context.addToClipBoard(text: String) {
    val clipboard = ContextCompat.getSystemService(this, ClipboardManager::class.java)
    val clip = ClipData.newPlainText("passwords", text)
    clipboard?.setPrimaryClip(clip)
}