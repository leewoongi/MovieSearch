package com.experiencers.ui.movie

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
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
    private val REQUEST_CODE: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialized()

        binding.btSearch.setOnClickListener {
            onClickSearchButton()
        }

        binding.btRecentlySearch.setOnClickListener {
            App.prefs.setString("movie", recentlyMovie)
            val intent = Intent(this@MainActivity, RecentlySearchActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
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

    private fun onClickSearchButton() {
        val movieTitle: String = binding.tvSearchMovie.text.toString()

        CoroutineScope(Dispatchers.Main + Job()).launch {
            val movieList = repository.getMovieList(movieTitle)

            if (movieList.isNotEmpty()) {
                with(binding.rvMovieContent.adapter as MovieAdapter) {
                    addMovies(movieList)
                    refresh()
                }
            } else {
                Toast.makeText(this@MainActivity, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE) {
            if (resultCode != Activity.RESULT_OK) {
                return
            }

            binding.tvSearchMovie.text = Editable.Factory.getInstance().newEditable(data?.getStringExtra("movieName") ?: "")
            onClickSearchButton()
        }
    }
}