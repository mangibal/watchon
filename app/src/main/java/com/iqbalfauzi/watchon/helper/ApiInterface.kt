package com.iqbalfauzi.watchon.helper

import com.iqbalfauzi.watchon.data.repository.ItemListEntity
import com.iqbalfauzi.watchon.data.repository.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Iqbal Fauzi on 18:42 02/11/19
 */
interface ApiInterface {

    @GET("movie/now_playing")
    fun getMovies() : Call<ItemResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetails(@Path("movie_id") movieId: String?): Call<ItemListEntity>

    @GET("tv/popular")
    fun getTvShows() : Call<ItemResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(@Path("tv_id") tvId: String?): Call<ItemListEntity>

//    @GET("tv/{tv_id}")
//    fun getTvShowDetails(@Path("tv_id") tvId: String?,
//                         @Query("api_key") apiKey: String?
//    ) : Call<TvShowsDetail>

}