package com.dicoding.picodiploma.moviecatalogue.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RemoteDataSource {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource()
            }
    }

    fun loadPopularMovies(callback: LoadPopularMoviesCallback){
        CoroutineScope(Dispatchers.IO).launch {
            callback.onPopularMoviesReceived(ApiConfig.instance.getPopularMovies())
        }
    }

    interface LoadPopularMoviesCallback {
        fun onPopularMoviesReceived(movieResponses: List<MovieParcel>)
    }

    fun loadPopularTvShows(callback: LoadPopularTvShowsCallback){
        CoroutineScope(Dispatchers.IO).launch {
            callback.onPopularTvShowsReceived(ApiConfig.instance.getPopularTvShow())
        }
    }

    interface LoadPopularTvShowsCallback {
        fun onPopularTvShowsReceived(tvShowResponses: List<TvShowParcel>)
    }

}