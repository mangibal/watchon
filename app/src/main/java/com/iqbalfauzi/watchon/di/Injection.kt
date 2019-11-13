package com.iqbalfauzi.watchon.di

import com.iqbalfauzi.watchon.data.repository.DataRepository
import com.iqbalfauzi.watchon.data.repository.local.LocalRepository
import com.iqbalfauzi.watchon.data.repository.remote.RemoteRepositoryJava
import com.iqbalfauzi.watchon.helper.ApiClient

/**
 * Created by Iqbal Fauzi on 19:41 02/11/19
 */
object Injection {
    fun movieRepository(): DataRepository? {
        val localRepository = LocalRepository()
        val remoteRepository = RemoteRepositoryJava.getInstance(ApiClient)
        return DataRepository.getInstance(localRepository, remoteRepository)
    }
}