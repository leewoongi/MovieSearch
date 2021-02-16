package com.experiencers.movie.ui

import com.experiencers.data.remote.entity.Item

interface SuccessRetrofit {
    fun onSuccess(movieList: ArrayList<Item>)
}