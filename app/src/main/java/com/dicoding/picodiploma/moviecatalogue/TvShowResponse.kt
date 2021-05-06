package com.dicoding.picodiploma.moviecatalogue

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

	@field:SerializedName("results")
	val results: List<TvShowEntity>,

)

data class TvShowEntity(

	@field:SerializedName("name")
	val title: String,

	@field:SerializedName("poster_path")
	val poster: String,

	@field:SerializedName("vote_average")
	val studio: Double,

	@field:SerializedName("first_air_date")
	val genre: String,

	@field:SerializedName("overview")
	val description: String

)
