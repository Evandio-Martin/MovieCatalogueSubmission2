package com.dicoding.picodiploma.moviecatalogue.data

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
        callback.onPopularMoviesReceived(ApiConfig.instance.getPopularMovies())
    }

    interface LoadPopularMoviesCallback {
        fun onPopularMoviesReceived(movieResponses: List<MovieParcel>)
    }

    fun loadPopularTvShows(callback: LoadPopularTvShowsCallback){
        callback.onPopularTvShowsReceived(ApiConfig.instance.getPopularTvShow())
    }

    interface LoadPopularTvShowsCallback {
        fun onPopularTvShowsReceived(tvShowResponses: List<TvShowParcel>)
    }

}