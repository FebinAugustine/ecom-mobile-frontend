package com.ecom.core.data.local

import android.content.SharedPreferences

interface AppPreferences {
    fun saveToken(token: String)
    fun getToken(): String?
}

class AppPreferencesImpl(private val prefs: SharedPreferences) : AppPreferences {
    companion object {
        private const val KEY_TOKEN = "token"
    }

    override fun saveToken(token: String) {
        prefs.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String? {
        return prefs.getString(KEY_TOKEN, null)
    }
}
