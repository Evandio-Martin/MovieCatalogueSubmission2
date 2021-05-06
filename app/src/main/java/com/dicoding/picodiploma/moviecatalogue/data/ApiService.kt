package com.dicoding.picodiploma.moviecatalogue.data

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("https://api.themoviedb.org/3/movie/popular?api_key=03c50742dfea81e23849bc58f6543080")
    fun getPopularMovies() : Call<ArrayList<MovieParcel>>

    @GET("https://api.themoviedb.org/3/tv/popular?api_key=03c50742dfea81e23849bc58f6543080")
    fun getPopularTvShow() : Call<ArrayList<TvShowParcel>>
}