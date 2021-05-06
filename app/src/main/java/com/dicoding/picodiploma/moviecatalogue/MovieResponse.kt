package com.dicoding.picodiploma.moviecatalogue

import com.google.gson.annotations.SerializedName

data class MovieResponse(

	@field:SerializedName("results")
	val results: List<MovieEntity>,
)

data class MovieEntity(

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("poster_path")
	val poster: String,

	@field:SerializedName("vote_average")
	val studio: Double,

	@field:SerializedName("release_date")
	val genre: String,

	@field:SerializedName("overview")
	val description: String

)
