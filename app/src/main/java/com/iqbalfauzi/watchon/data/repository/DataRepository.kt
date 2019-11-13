package com.iqbalfauzi.watchon.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iqbalfauzi.watchon.data.repository.local.LocalRepository
import com.iqbalfauzi.watchon.data.repository.remote.RemoteRepositoryJava
import com.iqbalfauzi.watchon.data.resource.DataSource
import timber.log.Timber

/**
 * Created by Iqbal Fauzi on 19:25 02/11/19
 */
open class DataRepository(private val localRepository: LocalRepository, private val RemoteRepositoryJava: RemoteRepositoryJava) : DataSource {

    override fun getTvShows(): LiveData<List<ItemListEntity>> {
        val tvShowList = MutableLiveData<List<ItemListEntity>>()
        RemoteRepositoryJava.getTvShows(object : RemoteRepositoryJava.GetTvShowsCallback {
            override fun onSuccess(tvShowsResponse: List<ItemListEntity>?) {
                tvShowList.postValue(tvShowsResponse)
            }

            override fun onError() {
                Timber.d("Error")
            }
        })
        return tvShowList
    }

    override fun getMovies(): LiveData<List<ItemListEntity>> {
        val movieList = MutableLiveData<List<ItemListEntity>>()
        RemoteRepositoryJava.getMovies(object : RemoteRepositoryJava.GetMovieCallback {
            override fun onSuccess(movieResponse: List<ItemListEntity>?) {
                movieList.postValue(movieResponse)
            }

            override fun onError() {
                Timber.d("Error")
            }
        })
        return movieList
    }

    override fun getMovieDetail(movieId: String): LiveData<ItemListEntity> {
        val movieDetail = MutableLiveData<ItemListEntity>()
        RemoteRepositoryJava.getMovieDetail(movieId, object : RemoteRepositoryJava.GetMovieDetailCallback {
            override fun onSuccess(movieResponse: ItemListEntity) {
                movieDetail.postValue(movieResponse)
            }

            override fun onError() {
                Timber.d("Error")
            }

        })

        return movieDetail
    }

    override fun getTvShowDetail(tvId: String): LiveData<ItemListEntity> {
        val tvShowDetail = MutableLiveData<ItemListEntity>()
        RemoteRepositoryJava.getTvShowDetail(tvId, object : RemoteRepositoryJava.GetTvShowDetailCallback {
            override fun onSuccess(tvShowsResponse: ItemListEntity) {
                tvShowDetail.postValue(tvShowsResponse)
            }

            override fun onError() {
                Timber.d("Error")
            }

        })
        return tvShowDetail
    }

    companion object {
        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(localRepository: LocalRepository, RemoteRepositoryJava: RemoteRepositoryJava): DataRepository? {
            if (INSTANCE == null) {
                synchronized(DataRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = DataRepository(localRepository, RemoteRepositoryJava)
                    }
                }
            }
            return INSTANCE
        }
    }

}