package com.experiencers.data.local

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray

class RecentlySearchSharedPreference(context: Context) {

    private val preference: SharedPreferences =
        context.getSharedPreferences("prefs_name", Context.MODE_PRIVATE)

    fun getString(key: String): ArrayList<String> {

        val json: String? = preference.getString(key, null)
        val movieList: ArrayList<String> = arrayListOf()

        if (json != null) {
            val jsonArray = JSONArray(json)
            val size: Int = jsonArray.length()
            for (i in (0 until size)) {
                movieList.add(jsonArray.optString(i))
            }
        }
        return movieList
    }

    fun setString(key: String, movieList: ArrayList<String>) {
        val jsonArray = JSONArray()
        val size: Int = movieList.size
        for (i in (0 until size)) {
            jsonArray.put(movieList[i])
        }

        if (movieList.isNotEmpty()) {
            preference.edit().putString(key, jsonArray.toString()).apply()
        } else {
            preference.edit().putString(key, null).apply()
        }
    }
}