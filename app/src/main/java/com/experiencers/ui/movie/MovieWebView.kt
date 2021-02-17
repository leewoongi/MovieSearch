package com.experiencers.ui.movie

import android.os.Bundle
import com.experiencers.base.BaseActivity
import com.experiencers.ui.R
import com.experiencers.ui.databinding.ActivityMovieWebviewBinding

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