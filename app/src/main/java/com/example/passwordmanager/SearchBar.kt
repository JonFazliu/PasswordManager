package com.example.passwordmanager

import android.widget.SearchView

fun onSearch(onQuery:(searchQuery: String) -> Boolean) : SearchView.OnQueryTextListener {
    return object: SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(searchQuery: String): Boolean {
            return onQuery(searchQuery)
        }
        override fun onQueryTextChange(searchQuery: String): Boolean {
            return onQuery(searchQuery)
        }
    }
}