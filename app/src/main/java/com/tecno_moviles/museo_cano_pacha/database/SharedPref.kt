package com.tecno_moviles.museo_cano_pacha.database

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    val prefs: SharedPreferences =
        context.getSharedPreferences("com.tecno_moviles.museo_cano_pacha.prefs", 0)

    var username: String?
        get() = prefs.getString("username", "")
        set(value) = prefs.edit().putString("username", value).apply()

    var userEmail: String?
        get() = prefs.getString("userEmail", "")
        set(value) = prefs.edit().putString("userEmail", value).apply()
}