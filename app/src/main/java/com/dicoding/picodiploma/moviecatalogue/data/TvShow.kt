package com.dicoding.picodiploma.moviecatalogue.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShowParcel(
        var poster: String,
        var title: String,
        var studio: Double,
        var genre: String,
        var description: String
) : Parcelable
