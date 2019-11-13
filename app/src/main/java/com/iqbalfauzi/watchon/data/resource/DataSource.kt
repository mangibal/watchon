package com.iqbalfauzi.watchon.data.resource

import androidx.lifecycle.LiveData
import com.iqbalfauzi.watchon.data.repository.ItemListEntity

/**
 * Created by Iqbal Fauzi on 18:23 02/11/19
 */
interface DataSource {
    fun getMovies(): LiveData<List<ItemListEntity>>
    fun getMovieDetail(movieId: String): LiveData<ItemListEntity>
    fun getTvShows(): LiveData<List<ItemListEntity>>
    fun getTvShowDetail(tvId: String): LiveData<ItemListEntity>
}