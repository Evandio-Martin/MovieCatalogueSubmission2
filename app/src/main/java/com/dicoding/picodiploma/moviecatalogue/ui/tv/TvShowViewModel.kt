package com.dicoding.picodiploma.moviecatalogue.ui.tv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.TvShowEntity

class TvShowViewModel(private val repository: MovieRepository) : ViewModel() {
    fun getTvShow(): LiveData<List<TvShowEntity>> = repository.getPopularTvShows()
}
