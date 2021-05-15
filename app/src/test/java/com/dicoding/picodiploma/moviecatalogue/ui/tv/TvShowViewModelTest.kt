package com.dicoding.picodiploma.moviecatalogue.ui.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.picodiploma.moviecatalogue.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.Model
import com.dicoding.picodiploma.moviecatalogue.utils.DataDummy
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
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tvShowRepository: MovieRepository

    @Mock
    private lateinit var observer: Observer<List<Model>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvShowRepository)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = DataDummy.generateTvShowDummy()
        val tvShow = MutableLiveData<List<Model>>()
        tvShow.value = dummyTvShow

        `when`(tvShowRepository.getPopularTvShows()).thenReturn(tvShow)

        val dataListTvShow = viewModel.getTvShow().value
        verify(tvShowRepository).getPopularTvShows()
        assertNotNull(dataListTvShow)

        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }
}