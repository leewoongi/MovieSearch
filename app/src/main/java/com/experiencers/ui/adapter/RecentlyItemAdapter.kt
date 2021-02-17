package com.experiencers.ui.adapter

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.experiencers.ui.R
import com.experiencers.ui.databinding.RecentlyItemBinding
import com.experiencers.ui.movie.MainActivity
import com.experiencers.ui.movie.RecentlySearchActivity

class RecentlyItemAdapter : RecyclerView.Adapter<RecentlyItemAdapter.MovieViewHolder>() {

    private val recentlyItemList = arrayListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            DataBindingUtil.inflate(
                parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater,
                R.layout.recently_item, parent, false
            )
        )


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(recentlyItemList[position])
    }

    override fun getItemCount(): Int {
        return recentlyItemList.size
    }

    fun addMovies(homeItemList: ArrayList<String>) {
        recentlyItemList.addAll(homeItemList)
    }

    fun refresh() {
        notifyDataSetChanged()
    }

    class MovieViewHolder(private val binding: RecentlyItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: String) {
            binding.tvSearchWord.text = item

            binding.tvSearchWord.setOnClickListener {
                val intent = Intent(binding.root.context, MainActivity::class.java)
                intent.putExtra("movieName", binding.tvSearchWord.text.toString())
                (binding.root.context as? RecentlySearchActivity)?.let {
                    it.setResult(RESULT_OK, intent)
                    it.finish()
                }
            }
        }
    }
}