package com.iqbalfauzi.watchon.data.repository.remote

import android.os.Handler
import com.iqbalfauzi.watchon.data.model.DataResponse
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.iqbalfauzi.watchon.helper.ApiClient
import com.iqbalfauzi.watchon.utils.EspressoIdlingResourceJava
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
        EspressoIdlingResourceJava.increment()
        responseHandler.postDelayed({
            request.getMovies().enqueue(object : Callback<DataResponse> {
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Timber.d("Error $t")
                }

                override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                    getMovieCallback.onSuccess(response.body()?.results)
                }
            })
        }, SERVICE_LATENCY)
    }

    fun getMovieDetail(movieId: String, getMovieDetailCallback: GetMovieDetailCallback) {
        EspressoIdlingResourceJava.increment()
        val responseHandler = Handler()
        responseHandler.postDelayed({
            request.getMovieDetails(movieId).enqueue(object : Callback<ResultEntity> {
                override fun onFailure(call: Call<ResultEntity>, t: Throwable) {
                    Timber.d("Error $t")
                }

                override fun onResponse(call: Call<ResultEntity>, response: Response<ResultEntity>) {
                    response.body()?.let { getMovieDetailCallback.onSuccess(it) }
                }

            })
        }, SERVICE_LATENCY)
    }

    fun getTvShowDetail(tvId: String, getTvShowDetailCallback: GetTvShowDetailCallback) {
        EspressoIdlingResourceJava.increment()
        responseHandler.postDelayed({
            request.getTvShowDetail(tvId).enqueue(object : Callback<ResultEntity> {
                override fun onFailure(call: Call<ResultEntity>, t: Throwable) {
                    Timber.d("Error $t")
                }

                override fun onResponse(call: Call<ResultEntity>, response: Response<ResultEntity>) {
                    response.body()?.let { getTvShowDetailCallback.onSuccess(it) }
                }

            })
        }, SERVICE_LATENCY)
    }

    fun getTvShows(getTvShowsCallback: GetTvShowsCallback) {
        EspressoIdlingResourceJava.increment()
        responseHandler.postDelayed({
            request.getTvShows().enqueue(object : Callback<DataResponse> {
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Timber.d("Error $t")
                }

                override fun onResponse(call: Call<DataResponse>, response: Response<DataResponse>) {
                    getTvShowsCallback.onSuccess(response.body()?.results)
                }
            })
        }, SERVICE_LATENCY)
    }

    interface GetMovieCallback {
        fun onSuccess(movieResponse: List<ResultEntity>?)
        fun onError()
    }

    interface GetMovieDetailCallback {
        fun onSuccess(movieResponse: ResultEntity)
        fun onError()
    }

    interface GetTvShowsCallback {
        fun onSuccess(tvShowsResponse: List<ResultEntity>?)
        fun onError()
    }

    interface GetTvShowDetailCallback {
        fun onSuccess(tvShowsResponse: ResultEntity)
        fun onError()
    }

    companion object {
        private const val SERVICE_LATENCY: Long = 2000
        private var INSTANCE: RemoteRepositoryJava? = null

        fun getInstance(apiClient: ApiClient): RemoteRepositoryJava {
            if (INSTANCE == null) {
                INSTANCE = RemoteRepositoryJava(apiClient)
            }
            return INSTANCE!!
        }
    }
}