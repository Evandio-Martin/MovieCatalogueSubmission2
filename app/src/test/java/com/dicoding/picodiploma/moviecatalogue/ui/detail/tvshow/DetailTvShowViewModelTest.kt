package com.dicoding.picodiploma.moviecatalogue.ui.detail.tvshow

import com.dicoding.picodiploma.moviecatalogue.utils.DataTvShow
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel
    private val tvShow = DataTvShow.generateTvShow()[0]
    private val tvShowName = tvShow.title

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
        viewModel.setSelectedTvShow(tvShowName)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(tvShow.title)
        val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(tvShow.title, tvShowEntity.title)
        assertEquals(tvShow.studio, tvShowEntity.studio)
        assertEquals(tvShow.genre, tvShowEntity.genre)
        assertEquals(tvShow.poster, tvShowEntity.poster)
        assertEquals(tvShow.description, tvShowEntity.description)
    }
}