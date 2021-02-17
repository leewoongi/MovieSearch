package com.experiencers.ui.movie

import android.os.Bundle
import android.webkit.WebViewClient
import com.experiencers.base.BaseActivity
import com.experiencers.ui.R
import com.experiencers.ui.databinding.ActivityMovieWebviewBinding

class MovieWebView : BaseActivity<ActivityMovieWebviewBinding>() {
    override fun layoutRes(): Int = R.layout.activity_movie_webview

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.wv.webViewClient = WebViewClient()
        binding.wv.settings.javaScriptEnabled = true

        intent.getStringExtra("url")?.let {
            binding.wv.loadUrl(it)
        }
    }
}