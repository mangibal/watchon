package com.iqbalfauzi.watchon.data.repository

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Iqbal Fauzi on 18:24 02/11/19
 */
@Parcelize
data class ItemListEntity(
        @SerializedName("poster_path")
        val posterPath: String?,

        @SerializedName("overview")
        val overview: String?,

        @SerializedName("release_date")
        val releaseDate: String?,

        @SerializedName("id")
        val id: Int,

        @SerializedName("original_title")
        var originalTitle: String?,

        @SerializedName("original_language")
        var originalLanguage: String?,

        @SerializedName("title")
        val title: String?,

        @SerializedName("backdrop_path")
        val backdropPath: String?,

        @SerializedName("vote_average")
        val voteAverage: Double?,

        @SerializedName("name")
        val name: String?,

        @SerializedName("first_air_date")
        val firstAirDate: String?
) : Parcelable