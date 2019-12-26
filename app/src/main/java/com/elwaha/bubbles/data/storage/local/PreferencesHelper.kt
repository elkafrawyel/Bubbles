package com.elwaha.bubbles.data.storage.local

import android.content.Context
import com.elwaha.MyApp

class PreferencesHelper {

    companion object {
        private const val PREF_NAME = "bubbles"
        private const val IS_LOGGED_IN = "isLoggedIn"
        private const val USER = "userModel"
        private const val LANGUAGE = "language"
        private const val USER_TYPE = "userType"
    }

    private val preference = MyApp.instance.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var isLoggedIn = preference.getBoolean(IS_LOGGED_IN, false)
        set(value) = preference.edit().putBoolean(IS_LOGGED_IN, value).apply()

    var userType = preference.getInt(USER_TYPE, -1)
        set(value) = preference.edit().putInt(USER_TYPE, value).apply()


    var user = preference.getString(USER, null)
        set(value) = preference.edit().putString(USER, value).apply()


    var language = preference.getString(LANGUAGE, null)
        //    var language = preference.getString(LANGUAGE, Constants.Language.ARABIC.value)
        set(value) = preference.edit().putString(LANGUAGE, value).apply()

    fun clear() {
        val lang = language
        preference.edit().clear().apply()
        language = lang
    }
}