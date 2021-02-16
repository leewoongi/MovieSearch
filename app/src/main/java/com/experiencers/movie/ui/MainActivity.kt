package com.experiencers.movie.ui

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.experiencers.base.BaseActivity
import com.experiencers.movie.R
import com.experiencers.movie.adapter.MovieAdapter
import com.experiencers.movie.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun layoutRes(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialized()
    }

    private fun initialized() {

        binding.rvMovieContent.apply{
            adapter = MovieAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false )
        }
    }
}