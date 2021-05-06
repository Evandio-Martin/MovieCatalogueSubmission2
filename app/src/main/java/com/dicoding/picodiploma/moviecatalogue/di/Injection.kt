package com.dicoding.picodiploma.moviecatalogue.di

import android.content.Context
import com.dicoding.picodiploma.moviecatalogue.MovieRepository
import com.dicoding.picodiploma.moviecatalogue.data.RemoteDataSource

object Injection {
    fun provideCatalogRepository(): MovieRepository {
        val remoteDataSource = RemoteDataSource.getInstance()
        return MovieRepository.getInstance(remoteDataSource)
    }
}