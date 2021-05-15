package com.dicoding.picodiploma.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import com.dicoding.picodiploma.moviecatalogue.data.Model

interface LocalDataSource {

    fun getPopularMovies(): LiveData<List<Model>>

    fun getPopularTvShows(): LiveData<List<Model>>

}