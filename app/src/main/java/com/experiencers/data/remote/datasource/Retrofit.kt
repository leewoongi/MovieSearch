package com.experiencers.data.remote.datasource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private var instance: Retrofit? = null
    private val baseUrl: String = "https://openapi.naver.com"

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return instance!!
    }
}