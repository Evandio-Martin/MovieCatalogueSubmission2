package com.dicoding.picodiploma.moviecatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.Model

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {
    fun getMovie(): LiveData<List<Model>> = repository.getPopularMovies()
}



