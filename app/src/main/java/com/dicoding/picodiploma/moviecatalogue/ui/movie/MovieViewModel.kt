package com.dicoding.picodiploma.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.MovieRepository

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    fun getMovie(): LiveData<List<MovieEntity>> = repository.getPopularMovies()
}



