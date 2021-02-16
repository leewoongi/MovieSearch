package com.experiencers.data.repository

import android.util.Log
import com.experiencers.data.remote.datasource.Retrofit
import com.experiencers.data.remote.datasource.webservice.WebService
import com.experiencers.data.remote.entity.Item
import com.experiencers.data.remote.entity.Movie
import com.experiencers.movie.ui.MainActivity
import retrofit2.Call
import retrofit2.Response


class Repository() {

    private val webservice: WebService = Retrofit.getInstance().create(WebService::class.java)
    private val clientId: String = "NLn10C0mViEdKgbIzr_M"
    private val clientSecret: String ="QGavQEkSPH"

    fun getMovieList(movieTitle: String, mainActivity: MainActivity){
        webservice.getMovies(clientId, clientSecret, movieTitle).enqueue(object: retrofit2.Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    var movieList = ArrayList<Item>()

                    if(response.body()?.items?.size != null){
                        val size: Int = response.body()!!.items.size

                        for(i in (0 until size)){
                            movieList.add(response.body()!!.items[i])
                        }
                    }
                    mainActivity.onSuccess(movieList)
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.d("error", "error", t)
            }
        })
    }
}