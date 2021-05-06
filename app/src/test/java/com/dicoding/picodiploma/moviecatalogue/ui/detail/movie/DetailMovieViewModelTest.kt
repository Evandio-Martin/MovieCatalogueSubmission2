package com.dicoding.picodiploma.moviecatalogue.ui.detail.movie

import com.dicoding.picodiploma.moviecatalogue.utils.DataMovie
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class DetailMovieViewModelTest {

    private lateinit var viewModel: DetailMovieViewModel
    private val movie = DataMovie.generateMovies()[0]
    private val movieName = movie.title

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieName)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(movie.title)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(movie.title, movieEntity.title)
        assertEquals(movie.studio, movieEntity.studio)
        assertEquals(movie.genre, movieEntity.genre)
        assertEquals(movie.poster, movieEntity.poster)
        assertEquals(movie.description, movieEntity.description)
    }
}