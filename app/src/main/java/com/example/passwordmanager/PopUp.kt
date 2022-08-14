package com.example.passwordmanager

import android.app.AlertDialog
import android.content.Context


fun Context.showAlertDialog(appName: String, onDelete: () -> Unit ) {
    AlertDialog.Builder(this)
        .setTitle("Delete $appName Password")
        .setMessage("Are you sure you want to delete the password for $appName?")
        .setPositiveButton("Delete") { dialog, which ->
            onDelete()
        }
        .setNegativeButton("No", null)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .show()
}