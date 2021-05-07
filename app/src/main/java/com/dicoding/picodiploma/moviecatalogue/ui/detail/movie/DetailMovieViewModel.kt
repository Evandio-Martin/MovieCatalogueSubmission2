package com.dicoding.picodiploma.moviecatalogue.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.MovieEntity
import com.dicoding.picodiploma.moviecatalogue.MovieRepository

class DetailMovieViewModel(private val repository: MovieRepository) : ViewModel() {
    private lateinit var movieId: String

    fun setSelectedMovie(movieId: String) {
        this.movieId = movieId
    }

    fun getMovie(): LiveData<MovieEntity> = repository.getMovieWithModules(movieId)
}