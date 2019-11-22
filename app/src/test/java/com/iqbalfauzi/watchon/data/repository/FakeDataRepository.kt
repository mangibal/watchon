package com.iqbalfauzi.watchon.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.iqbalfauzi.watchon.data.model.ResultEntity
import com.iqbalfauzi.watchon.data.repository.local.LocalRepository
import com.iqbalfauzi.watchon.data.repository.remote.RemoteRepository
import com.iqbalfauzi.watchon.data.repository.remote.RemoteRepositoryJava
import com.iqbalfauzi.watchon.data.resource.DataSource
import timber.log.Timber

/**
 * Created by Iqbal Fauzi on 13:54 10/11/19
 */
open class FakeDataRepository(private val localRepository: LocalRepository, private val remoteRepository: RemoteRepositoryJava) : DataSource {

    override fun getTvShows(): LiveData<List<ResultEntity>> {
        val tvShowList = MutableLiveData<List<ResultEntity>>()
        remoteRepository.getTvShows(object : RemoteRepositoryJava.GetTvShowsCallback {
            override fun onSuccess(tvShowsResponse: List<ResultEntity>?) {
                tvShowList.postValue(tvShowsResponse)
            }

            override fun onError() {
                Timber.d("Error")
            }
        })
        return tvShowList
    }

    override fun getMovies(): LiveData<List<ResultEntity>> {
        val movieList = MutableLiveData<List<ResultEntity>>()
        remoteRepository.getMovies(object : RemoteRepositoryJava.GetMovieCallback {
            override fun onSuccess(movieResponse: List<ResultEntity>?) {
                movieList.postValue(movieResponse)
            }

            override fun onError() {
                Timber.d("Error")
            }
        })
        return movieList
    }

    override fun getMovieDetail(movieId: String): LiveData<ResultEntity> {
        val movieDetail = MutableLiveData<ResultEntity>()
        remoteRepository.getMovieDetail(movieId, object : RemoteRepositoryJava.GetMovieDetailCallback {
            override fun onSuccess(movieResponse: ResultEntity) {
                movieDetail.postValue(movieResponse)
            }

            override fun onError() {
                Timber.d("Error")
            }

        })

        return movieDetail
    }

    override fun getTvShowDetail(tvId: String): LiveData<ResultEntity> {
        val tvShowDetail = MutableLiveData<ResultEntity>()
        remoteRepository.getTvShowDetail(tvId, object : RemoteRepositoryJava.GetTvShowDetailCallback {
            override fun onSuccess(tvShowsResponse: ResultEntity) {
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

        fun getInstance(localRepository: LocalRepository, remoteRepository: RemoteRepositoryJava): DataRepository? {
            if (INSTANCE == null) {
                synchronized(DataRepository::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = DataRepository(remoteRepository)
                    }
                }
            }
            return INSTANCE
        }
    }
}