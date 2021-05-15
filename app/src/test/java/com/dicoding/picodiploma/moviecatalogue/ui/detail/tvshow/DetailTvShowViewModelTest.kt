package com.dicoding.picodiploma.moviecatalogue.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.moviecatalogue.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.Model
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {

    private val dummyTvShow = DataDummy.generateTvShowDummy()[0]
    private val tvShowId = dummyTvShow.title

    private lateinit var viewModel: DetailTvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<Model>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<Model>()
        tvShow.value = dummyTvShow

        `when`(tvShowRepository.getTvShowWithModules(tvShowId)).thenReturn(tvShow)
        val tvShowEntity = viewModel.getTvShow(tvShowId).value as Model
        verify(tvShowRepository).getTvShowWithModules(tvShowId)
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.poster, tvShowEntity.poster)
        assertEquals(dummyTvShow.genre, tvShowEntity.genre)
        assertEquals(dummyTvShow.description, tvShowEntity.description)

        viewModel.getTvShow(tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}