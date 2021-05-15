package com.dicoding.picodiploma.moviecatalogue.data.api

import com.dicoding.picodiploma.moviecatalogue.data.response.Movie
import com.dicoding.picodiploma.moviecatalogue.data.response.Response
import com.dicoding.picodiploma.moviecatalogue.data.response.TvShow
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "03c50742dfea81e23849bc58f6543080"
    ): Call<Response<Movie>>

    @GET("tv/popular")
    fun getPopularTvShow(
        @Query("api_key") apiKey: String = "03c50742dfea81e23849bc58f6543080"
    ): Call<Response<TvShow>>
}