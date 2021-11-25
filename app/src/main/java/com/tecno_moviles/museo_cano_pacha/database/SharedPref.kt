package com.tecno_moviles.museo_cano_pacha.database

import android.content.Context
import android.content.SharedPreferences

class SharedPref (context: Context) {
    val PREFS_NAME = "com.tecno_moviles.museo_cano_pacha.prefs"
    val USERNAME = "username"
    val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, 0)

    var username: String?
        get() = prefs.getString(USERNAME, "")
        set(value) = prefs.edit().putString(USERNAME, value).apply()

}