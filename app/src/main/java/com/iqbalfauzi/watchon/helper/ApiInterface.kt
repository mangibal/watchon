package com.iqbalfauzi.watchon.helper

import com.iqbalfauzi.watchon.data.model.DataResponse
import com.iqbalfauzi.watchon.data.model.ResultEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Iqbal Fauzi on 18:42 02/11/19
 */
interface ApiInterface {

    @GET("movie/now_playing")
    fun getMovies() : Call<DataResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String?): Call<ResultEntity>

    @GET("tv/popular")
    fun getTvShows() : Call<DataResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(@Path("tv_id") tvId: String?): Call<ResultEntity>

//    @GET("tv/{tv_id}")
//    fun getTvShowDetails(@Path("tv_id") tvId: String?,
//                         @Query("api_key") apiKey: String?
//    ) : Call<TvShowsDetail>

}