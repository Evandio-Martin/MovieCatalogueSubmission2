package com.dicoding.picodiploma.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue.data.Movie
import com.dicoding.picodiploma.moviecatalogue.data.TvShow
import com.dicoding.picodiploma.moviecatalogue.utils.DataMovie
import com.dicoding.picodiploma.moviecatalogue.utils.DataTvShow

class TvShowViewModel(private val repository: MovieRepository) : ViewModel() {
    fun getTvShow(): LiveData<List<TvShowEntity>> = repository.getPopularTvShows()
}
