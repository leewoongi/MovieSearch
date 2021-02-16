package com.experiencers.movie.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.experiencers.data.remote.entity.Item
import com.experiencers.movie.R
import com.experiencers.movie.databinding.MovieItemBinding
import com.experiencers.movie.ui.MovieWebView
import kotlin.collections.ArrayList

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val movieList = ArrayList<Item>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(DataBindingUtil.inflate(
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
            R.layout.movie_item, parent, false)
        )

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(movieList[position])
    }

    override fun getItemCount(): Int = movieList.size

    fun addMovies(homeItemList: ArrayList<Item>){
        movieList.clear()
        for(item in homeItemList){
            this.movieList.add(item)
        }
    }

    fun refresh(){
        notifyDataSetChanged()
    }

    class MovieViewHolder(private val binding: MovieItemBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun onBind(item: Item){
            binding.tvMovieTitle.text = "제목: " + Html.fromHtml(item.title).toString()
            binding.tvMovieYear.text = "출시: " + item.pupDate
            binding.tvMovieScore.text = "평점: " + item.userRating.toString()
            if(item.image != null){
                Glide.with(binding.root.context).load(item.image).override(100,100).into(binding.ivMovie)
            }

            binding.ivMovie.setOnClickListener {
                val intent = Intent(binding.root.context, MovieWebView::class.java)
                intent.putExtra("url", item.link)
                binding.root.context.startActivity(intent)
            }
        }
    }
}