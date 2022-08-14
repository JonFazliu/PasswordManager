package com.example.passwordmanager

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView

class ListItem(inflater: LayoutInflater) {

    val view = inflater.inflate(R.layout.list_item, null)
    val textView = view.findViewById<TextView>(R.id.appName)
    val trashCan = view.findViewById<ImageView>(R.id.deletePassword)
    val copyPassword = view.findViewById<ImageView>(R.id.copyPassword)

}