package com.dicoding.picodiploma.moviecatalogue.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val httpClient = OkHttpClient.Builder().apply {
    }.build()

    private val retrofit: Retrofit.Builder by lazy {
        Retrofit.Builder().apply {
            client(httpClient)
            baseUrl("https://api.themoviedb.org/3/")
            addConverterFactory(GsonConverterFactory.create())
        }
    }

    val instance: ApiService by lazy {
        retrofit
            .build()
            .create(ApiService::class.java)
    }


}