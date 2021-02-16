package com.experiencers.data.remote.entity

data class Movie(
    val lastBuildDate: String,
    var total: Int = 0,
    var start: Int = 0,
    var display: Int = 0,
    var items: List<Item>
)