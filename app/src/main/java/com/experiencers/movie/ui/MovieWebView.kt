package com.experiencers.movie.ui

import android.content.Intent
import android.os.Bundle
import com.experiencers.base.BaseActivity
import com.experiencers.movie.R
import com.experiencers.movie.databinding.ActivityMovieWebviewBinding

class MovieWebView: BaseActivity<ActivityMovieWebviewBinding>() {
    override fun layoutRes(): Int = R.layout.activity_movie_webview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val webViewUrl: String? = intent.getStringExtra("url")
        if (webViewUrl != null) {
            binding.wv.loadUrl(webViewUrl)
        }

    }
}