package com.iqbalfauzi.watchon.data.resource

import androidx.lifecycle.LiveData
import com.iqbalfauzi.watchon.data.model.ItemListEntity
import com.iqbalfauzi.watchon.data.model.ResultEntity

/**
 * Created by Iqbal Fauzi on 18:23 02/11/19
 */
interface DataSource {
    fun getMovies(): LiveData<List<ResultEntity>>
    fun getMovieDetail(movieId: String): LiveData<ResultEntity>
    fun getTvShows(): LiveData<List<ResultEntity>>
    fun getTvShowDetail(tvId: String): LiveData<ResultEntity>
}