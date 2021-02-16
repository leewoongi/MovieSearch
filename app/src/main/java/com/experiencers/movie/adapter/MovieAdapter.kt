package com.experiencers.movie.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.experiencers.data.remote.entity.Movie
import com.experiencers.movie.R
import com.experiencers.movie.databinding.MovieItemBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(DataBindingUtil.inflate(
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            R.layout.movie_item, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun addMovies(homeMovieList: ArrayList<Movie>){
        val size: Int = homeMovieList.size
        for(item in (0 until size)){
            movieList.add(homeMovieList[item])
        }
    }

    class MovieViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(item: Movie){
            binding.tvMovieScore.text = item.userRating.toString()
            binding.tvMovieTitle.text = item.title
            binding.tvMovieYear.text = item.pupDate.toString()
        }
    }
}