package com.experiencers.movie.ui

import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.experiencers.base.BaseActivity
import com.experiencers.data.remote.entity.Item
import com.experiencers.data.repository.Repository
import com.experiencers.movie.R
import com.experiencers.movie.adapter.MovieAdapter
import com.experiencers.movie.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(), SuccessRetrofit {
    override fun layoutRes(): Int = R.layout.activity_main
    private val repository = Repository()
    private var adapter = MovieAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialized()

        binding.btSearch.setOnClickListener {
            val movieTitle: String? = binding.tvSearchMovie.text.toString()
            if(movieTitle != null){
                repository.getMovieList(movieTitle!!, this@MainActivity)
            }else{
                Toast.makeText(this@MainActivity,"영화를 검색해 주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initialized() {

        binding.rvMovieContent.apply{
            layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false )
        }
    }

    override fun onSuccess(movieList: ArrayList<Item>) {
        binding.rvMovieContent.adapter = adapter
        adapter.addMovies(movieList)
        adapter.refresh()
    }
}