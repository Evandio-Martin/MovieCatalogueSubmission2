package com.dicoding.picodiploma.moviecatalogue.data

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.moviecatalogue.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.TvShowEntity

interface LocalDataSource {

    fun getPopularMovies(): LiveData<List<MovieEntity>>

    fun getPopularTvShows(): LiveData<List<TvShowEntity>>

}