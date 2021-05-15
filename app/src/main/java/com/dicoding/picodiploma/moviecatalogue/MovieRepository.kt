package com.dicoding.picodiploma.moviecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.moviecatalogue.data.Model
import com.dicoding.picodiploma.moviecatalogue.data.local.LocalDataSource
import com.dicoding.picodiploma.moviecatalogue.data.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue.data.response.Movie
import com.dicoding.picodiploma.moviecatalogue.data.response.TvShow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    LocalDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData).apply { instance = this }
            }
    }

    override fun getPopularMovies(): LiveData<List<Model>> {
        val movieResults = MutableLiveData<List<Model>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback {
                override fun onPopularMoviesReceived(movieResponses: List<Movie>) {
                    val movieList = ArrayList<Model>()
                    for (response in movieResponses) {
                        val movie = Model(
                            response.title,
                            response.poster,
                            response.studio,
                            response.genre,
                            response.description
                        )
                        movieList.add(movie)
                    }
                    movieResults.postValue(movieList)
                }
            })
        }
        return movieResults
    }

    override fun getPopularTvShows(): LiveData<List<Model>> {
        val tvShowResults = MutableLiveData<List<Model>>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularTvShows(object :
                RemoteDataSource.LoadPopularTvShowsCallback {
                override fun onPopularTvShowsReceived(tvShowResponses: List<TvShow>) {
                    val tvShowList = ArrayList<Model>()
                    for (response in tvShowResponses) {
                        val tvShow = Model(
                            response.title,
                            response.poster,
                            response.studio,
                            response.genre,
                            response.description
                        )
                        tvShowList.add(tvShow)
                    }
                    tvShowResults.postValue(tvShowList)
                }
            })
        }
        return tvShowResults
    }

    fun getMovieWithModules(movieId: String): LiveData<Model> {
        val movieResult = MutableLiveData<Model>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback {
                override fun onPopularMoviesReceived(movieResponses: List<Movie>) {
                    lateinit var movie: Model
                    for (response in movieResponses) {
                        if (response.title == movieId) {
                            movie = Model(
                                response.title,
                                response.poster,
                                response.studio,
                                response.genre,
                                response.description
                            )
                        }
                    }
                    movieResult.postValue(movie)
                }
            })
        }
        return movieResult
    }

    fun getTvShowWithModules(tvShowId: String): LiveData<Model> {
        val tvShowResult = MutableLiveData<Model>()
        CoroutineScope(Dispatchers.IO).launch {
            remoteDataSource.getPopularTvShows(object :
                RemoteDataSource.LoadPopularTvShowsCallback {
                override fun onPopularTvShowsReceived(tvShowResponses: List<TvShow>) {
                    lateinit var tvShow: Model
                    for (response in tvShowResponses) {
                        if (response.title == tvShowId) {
                            tvShow = Model(
                                response.title,
                                response.poster,
                                response.studio,
                                response.genre,
                                response.description
                            )
                        }
                    }
                    tvShowResult.postValue(tvShow)
                }
            })
        }
        return tvShowResult
    }

}