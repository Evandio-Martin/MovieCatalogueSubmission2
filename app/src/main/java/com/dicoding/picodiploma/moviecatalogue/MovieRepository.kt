package com.dicoding.picodiploma.moviecatalogue

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.picodiploma.moviecatalogue.data.LocalDataSource
import com.dicoding.picodiploma.moviecatalogue.data.MovieParcel
import com.dicoding.picodiploma.moviecatalogue.data.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue.data.TvShowParcel
import retrofit2.Call

class MovieRepository private constructor(private val remoteDataSource: RemoteDataSource) : LocalDataSource {

    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(remoteData).apply { instance = this }
            }
    }

    override fun getPopularMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.loadPopularMovies(object : RemoteDataSource.LoadPopularMoviesCallback{
            override fun onPopularMoviesReceived(movieResponses: List<MovieParcel>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(response.title,
                            response.poster,
                            response.studio,
                            response.genre,
                            response.description)
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getPopularTvShows(): LiveData<List<TvShowEntity>> {
        val tvShowResults = MutableLiveData<List<TvShowEntity>>()
        remoteDataSource.loadPopularTvShows(object : RemoteDataSource.LoadPopularTvShowsCallback{
            override fun onPopularTvShowsReceived(tvShowResponses: List<TvShowParcel>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in tvShowResponses) {
                    val tvShow = TvShowEntity(response.title,
                            response.poster,
                            response.studio,
                            response.genre,
                            response.description)
                    tvShowList.add(tvShow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })
        return tvShowResults
    }


}