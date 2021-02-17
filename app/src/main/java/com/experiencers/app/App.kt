package com.experiencers.app

import android.app.Application
import com.experiencers.data.local.RecentlySearchSharedPreference

class App: Application() {
    companion object{
        lateinit var prefs: RecentlySearchSharedPreference
    }

    override fun onCreate() {
        super.onCreate()
        prefs = RecentlySearchSharedPreference(applicationContext)
    }
}