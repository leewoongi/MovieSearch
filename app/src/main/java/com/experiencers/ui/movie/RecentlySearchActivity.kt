package com.experiencers.ui.movie

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.experiencers.app.App
import com.experiencers.base.BaseActivity
import com.experiencers.ui.R
import com.experiencers.ui.adapter.RecentlyItemAdapter
import com.experiencers.ui.databinding.ActivityRecentlySearchBinding

class RecentlySearchActivity: BaseActivity<ActivityRecentlySearchBinding>() {
    override fun layoutRes(): Int = R.layout.activity_recently_search
    private var recentlyItem: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initialized()

        with (binding.rvRecentlyItem.adapter as RecentlyItemAdapter){
            addMovies(recentlyItem)
            refresh()
        }

    }

    private fun initialized() {
        recentlyItem = App.prefs.getString("movie")
        binding.rvRecentlyItem.apply {
            adapter = RecentlyItemAdapter()
            layoutManager =
                GridLayoutManager(this@RecentlySearchActivity, 2)
        }
    }
}