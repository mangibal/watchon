package com.iqbalfauzi.watchon.data.repository.remote

import android.os.Handler
import com.iqbalfauzi.watchon.data.repository.ItemListEntity
import com.iqbalfauzi.watchon.data.repository.ItemResponse
import com.iqbalfauzi.watchon.helper.ApiClient
import com.iqbalfauzi.watchon.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * Created by Iqbal Fauzi on 18:47 02/11/19
 */
open class RemoteRepository(private val apiClient: ApiClient) {
    private val request = ApiClient.create()
    private val responseHandler = Handler()

    fun getMovies(getMovieCallback: GetMovieCallback) {
        EspressoIdlingResource.increment()
        responseHandler.postDelayed({
            request.getMovies().enqueue(object : Callback<ItemResponse> {
                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                    Timber.d("Error $t")
                }

                override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                    getMovieCallback.onSuccess(response.body()?.results)
                }
            })
        }, SERVICE_LATENCY)
    }

    fun getMovieDetail(movieId: String, getMovieDetailCallback: GetMovieDetailCallback) {
        EspressoIdlingResource.increment()
        val responseHandler = Handler()
        responseHandler.postDelayed({
            request.getMovieDetails(movieId).enqueue(object : Callback<ItemListEntity> {
                override fun onFailure(call: Call<ItemListEntity>, t: Throwable) {
                    Timber.d("Error $t")
                }

                override fun onResponse(call: Call<ItemListEntity>, response: Response<ItemListEntity>) {
                    response.body()?.let { getMovieDetailCallback.onSuccess(it) }
                }

            })
        }, SERVICE_LATENCY)
    }

    fun getTvShowDetail(tvId: String, getTvShowDetailCallback: GetTvShowDetailCallback) {
        EspressoIdlingResource.increment()
        responseHandler.postDelayed({
            request.getTvShowDetail(tvId).enqueue(object : Callback<ItemListEntity> {
                override fun onFailure(call: Call<ItemListEntity>, t: Throwable) {
                    Timber.d("Error $t")
                }

                override fun onResponse(call: Call<ItemListEntity>, response: Response<ItemListEntity>) {
                    response.body()?.let { getTvShowDetailCallback.onSuccess(it) }
                }

            })
        }, SERVICE_LATENCY)
    }

    fun getTvShows(getTvShowsCallback: GetTvShowsCallback) {
        EspressoIdlingResource.increment()
        responseHandler.postDelayed({
            request.getTvShows().enqueue(object : Callback<ItemResponse> {
                override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                    Timber.d("Error $t")
                }

                override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                    getTvShowsCallback.onSuccess(response.body()?.results)
                }
            })
        }, SERVICE_LATENCY)
    }

    interface GetMovieCallback {
        fun onSuccess(movieResponse: List<ItemListEntity>?)
        fun onError()
    }

    interface GetMovieDetailCallback {
        fun onSuccess(movieResponse: ItemListEntity)
        fun onError()
    }

    interface GetTvShowsCallback {
        fun onSuccess(tvShowsResponse: List<ItemListEntity>?)
        fun onError()
    }

    interface GetTvShowDetailCallback {
        fun onSuccess(tvShowsResponse: ItemListEntity)
        fun onError()
    }

    companion object {
        private const val SERVICE_LATENCY: Long = 2000
        private var INSTANCE: RemoteRepository? = null

        fun getInstance(apiClient: ApiClient): RemoteRepository {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepository(apiClient)
            }
            return INSTANCE!!
        }
    }
}