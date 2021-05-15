package com.dicoding.picodiploma.moviecatalogue.data.remote

import com.dicoding.picodiploma.moviecatalogue.data.api.ApiConfig
import com.dicoding.picodiploma.moviecatalogue.data.response.Movie
import com.dicoding.picodiploma.moviecatalogue.data.response.TvShow
import com.dicoding.picodiploma.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.await

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    suspend fun getPopularMovies(callback: LoadPopularMoviesCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getPopularMovies().await().result?.let {
            callback.onPopularMoviesReceived(
                it
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadPopularMoviesCallback {
        fun onPopularMoviesReceived(movieResponses: List<Movie>)
    }

    suspend fun getPopularTvShows(callback: LoadPopularTvShowsCallback) {
        EspressoIdlingResource.increment()
        ApiConfig.instance.getPopularTvShow().await().result?.let {
            callback.onPopularTvShowsReceived(
                it
            )
            EspressoIdlingResource.decrement()
        }
    }

    interface LoadPopularTvShowsCallback {
        fun onPopularTvShowsReceived(tvShowResponses: List<TvShow>)
    }

}