package com.dicoding.picodiploma.moviecatalogue.ui.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.picodiploma.moviecatalogue.data.remote.RemoteDataSource
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
import com.dicoding.picodiploma.moviecatalogue.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock


class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeRepository(remote)

    private val listMovieResponse = DataDummy.generateMovieRepo()
    private val movieId = listMovieResponse[0].title
    private val listTvShowResponse = DataDummy.generateTvShowRepo()
    private val tvShowId = listTvShowResponse[0].title
    private val movieResponse = DataDummy.generateMovieDummy()[0]
    private val tvShowResponse = DataDummy.generateTvShowDummy()[0]

    @Test
    fun getPopularMovies() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularMoviesCallback).onPopularMoviesReceived(
                    listMovieResponse
                )
                null
            }.`when`(remote).getPopularMovies(any())
        }
        val data = LiveDataTestUtil.getValue(movieRepository.getPopularMovies())

        runBlocking {
            verify(remote).getPopularMovies(any())
        }

        assertNotNull(data)
        assertEquals(listMovieResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getPopularTvShows() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularTvShowsCallback).onPopularTvShowsReceived(
                    listTvShowResponse
                )
                null
            }.`when`(remote).getPopularTvShows(any())
        }
        val data = LiveDataTestUtil.getValue(movieRepository.getPopularTvShows())

        runBlocking {
            verify(remote).getPopularTvShows(any())
        }

        assertNotNull(data)
        assertEquals(listMovieResponse.size.toLong(), data.size.toLong())
    }

    @Test
    fun getMovieWithModules() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularMoviesCallback).onPopularMoviesReceived(
                    listMovieResponse
                )
                null
            }.`when`(remote).getPopularMovies(any())
        }
        val data = LiveDataTestUtil.getValue(movieRepository.getMovieWithModules(movieId))
        runBlocking {
            verify(remote).getPopularMovies(any())
        }
        assertNotNull(data)
        assertEquals(movieResponse.title, data.title)

    }

    @Test
    fun getTvShowWithModules() {
        runBlocking {
            doAnswer { invocation ->
                (invocation.arguments[0] as RemoteDataSource.LoadPopularTvShowsCallback).onPopularTvShowsReceived(
                    listTvShowResponse
                )
                null
            }.`when`(remote).getPopularTvShows(any())
        }
        val data = LiveDataTestUtil.getValue(movieRepository.getTvShowWithModules(tvShowId))
        runBlocking {
            verify(remote).getPopularTvShows(any())
        }
        assertNotNull(data)
        assertEquals(tvShowResponse.title, data.title)
    }
}