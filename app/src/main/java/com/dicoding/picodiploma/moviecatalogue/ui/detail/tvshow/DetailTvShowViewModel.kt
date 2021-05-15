package com.dicoding.picodiploma.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.picodiploma.moviecatalogue.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.Model

class DetailTvShowViewModel(private val repository: MovieRepository) : ViewModel() {
    private lateinit var tvShowId: String

    fun setSelectedTvShow(tvShowId: String) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(tvShowId: String): LiveData<Model> = repository.getTvShowWithModules(tvShowId)
}