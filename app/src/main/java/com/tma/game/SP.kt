package com.tma.game

import android.content.Context
import android.content.SharedPreferences

object SP {
    private const val APP = "MyPrefAppWH"
    private const val LAST_ACTIVITY_IS_APP = "last_activity_is_app"

    fun saveIsApp(context: Context, isGame: Boolean) {
        val preferences = context.getSharedPreferences(APP, Context.MODE_PRIVATE)
        val myEditor: SharedPreferences.Editor = preferences.edit()
        myEditor.putString(LAST_ACTIVITY_IS_APP, isGame.toString())
        myEditor.apply()
    }

    fun getIsApp(context: Context): String? {
        val preferences = context.getSharedPreferences(APP, Context.MODE_PRIVATE)
        return preferences.getString(LAST_ACTIVITY_IS_APP, null)
    }
}