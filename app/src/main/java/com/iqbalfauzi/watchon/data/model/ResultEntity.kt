package com.iqbalfauzi.watchon.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Iqbal Fauzi on 15:50 14/11/19
 */
@Parcelize
data class ResultEntity(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String?,
    val original_name: String?,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String?,
    val first_air_date: String?,
    val title: String?,
    val name: String?,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
) : Parcelable