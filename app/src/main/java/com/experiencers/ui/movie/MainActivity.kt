package com.experiencers.ui.movie

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.experiencers.app.App
import com.experiencers.base.BaseActivity
import com.experiencers.data.repository.Repository
import com.experiencers.ui.R
import com.experiencers.ui.adapter.MovieAdapter
import com.experiencers.ui.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun layoutRes(): Int = R.layout.activity_main
    private val repository = Repository()
    private var recentlyMovie: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialized()

        binding.btSearch.setOnClickListener {
            val movieTitle: String = binding.tvSearchMovie.text.toString()

            CoroutineScope(Dispatchers.Main + Job()).launch {
                val movieList = repository.getMovieList(movieTitle)

                if (movieList.isNotEmpty()) {
                    with(binding.rvMovieContent.adapter as MovieAdapter) {
                        addMovies(movieList)
                        refresh()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "영화를 검색해 주세요", Toast.LENGTH_SHORT).show()
                }
            }

            val check: Boolean = recentlyMovie.contains(movieTitle)
            if (check) {
                recentlyMovie.remove(movieTitle)
            }
            recentlyMovie.add(movieTitle)

            if (recentlyMovie.size > 10) {
                recentlyMovie.removeFirst()
            }
        }

        binding.btRecentlySearch.setOnClickListener{
            App.prefs.setString("movie", recentlyMovie)
            val intent = Intent(this@MainActivity, RecentlySearchActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initialized() {

        recentlyMovie = App.prefs.getString("movie")
        binding.rvMovieContent.apply {
            adapter = MovieAdapter()
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }
    }
}