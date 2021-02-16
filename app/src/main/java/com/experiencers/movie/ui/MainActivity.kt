package com.experiencers.movie.ui

import android.os.Bundle
import com.experiencers.base.BaseActivity
import com.experiencers.movie.R
import com.experiencers.movie.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun layoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}