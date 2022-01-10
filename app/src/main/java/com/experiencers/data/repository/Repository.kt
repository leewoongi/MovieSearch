package com.experiencers.data.repository

import com.experiencers.data.remote.datasource.Retrofit
import com.experiencers.data.remote.datasource.webservice.WebService
import com.experiencers.data.remote.entity.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext


class Repository {
    private val webservice: WebService = Retrofit.getInstance().create(WebService::class.java)
    private val clientId: String = "NLn10C0mViEdKgbIzr_M"
    private val clientSecret: String ="QGavQEkSPH"
    private val coroutineContext: CoroutineContext = Dispatchers.IO + Job()

    // 테스트 주석 비트라이즈
    // 태그 업
    suspend fun getMovieList(movieTitle: String): List<Item> =
        withContext(coroutineContext) {
            webservice.getMovies(clientId, clientSecret, movieTitle).body()?.items?: listOf()
        }
}