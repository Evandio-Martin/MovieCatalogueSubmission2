package com.dicoding.picodiploma.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.moviecatalogue.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.Model
import com.dicoding.picodiploma.moviecatalogue.data.response.Movie
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest {

    private val dummyMovie = DataDummy.generateMovieDummy()[0]
    private val movieId = dummyMovie.title

    private lateinit var viewModel: DetailMovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Model>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(movieRepository)
    }

    @Test
    fun getMovie() {
        val movies = MutableLiveData<Model>()
        movies.value = dummyMovie

        `when`(movieRepository.getMovieWithModules(movieId)).thenReturn(movies)
        val movieEntity = viewModel.getMovie(movieId).value as Model
        verify(movieRepository).getMovieWithModules(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.poster, movieEntity.poster)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.description, movieEntity.description)

        viewModel.getMovie(movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}