package com.experiencers.data.remote.entity

import java.util.*

data class Movie (
    val title: String,
    val link: String,
    val image: String,
    val subTitle: String,
    val pupDate: Date,
    val director: String,
    val actor: String,
    val userRating: Integer
)